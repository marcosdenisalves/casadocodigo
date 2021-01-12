package br.com.casadocodigo.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class HomeBean {

	@Inject
	private LivroDao dao;
	
	public List<Livro> ultimosLancamentos() {
		System.out.println("Ultimos lançamentos!!");
		return dao.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros() {
		System.out.println("Demais Livros!!");
		return dao.demaisLivros();
	}
	
}










