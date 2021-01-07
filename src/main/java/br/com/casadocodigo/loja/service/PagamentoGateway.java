package br.com.casadocodigo.loja.service;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import br.com.casadocodigo.loja.models.Pagamento;

public class PagamentoGateway {

	public static String pagar(BigDecimal total) {
		Pagamento pagamento = new Pagamento(total);
		String target = "http://book-payment.herokuapp.com/payment";
		Client client = ClientBuilder.newClient();
		return client.target(target).request()
				.post(Entity.json(pagamento), String.class);
	}

}
