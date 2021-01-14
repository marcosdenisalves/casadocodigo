package br.com.casadocodigo.loja.service;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.infra.MailSender;
import br.com.casadocodigo.loja.models.Compra;

@Path("/pagamento")
public class PagamentoService {

	@Inject
	private CompraDao dao;
	@Inject
	private MailSender mailSender;
	@Context
	private ServletContext context;

	private static ExecutorService executor = Executors.newFixedThreadPool(50);

	@POST
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid) {
		Compra compra = dao.buscaPorUuid(uuid);
		String contextPath = context.getContextPath();

		executor.submit(() -> {
			try {
				PagamentoGateway.pagar(compra.getTotal());

				URI responseUri = UriBuilder
						.fromPath("http://localhost:8080" + contextPath + "/index.xhtml")
						.queryParam("msg", "Compra Realizada com Sucesso").build();

				ar.resume(Response.seeOther(responseUri).build());

				String messageBody = "Sua compra foi realizada com sucesso!"
						+ "\n número de pedido: " + compra.getUuid();
				
				mailSender.send("compras@casadocodigo.com.br",
						compra.getUsuario().getEmail(), 
						"Nova compra na CDC", messageBody);
				
			} catch (Exception e) {
				ar.resume(new WebApplicationException(e));
			}
		});
	}
}
