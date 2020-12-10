package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class AdminLivrosBean {

	@Inject
	private Livro livro;
	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	
	private List<Integer> autoresId = new ArrayList<>();

	@Transactional
	public String salvar() {
		for (Integer autorId: autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}
		livroDao.salvar(livro);
		System.out.println("Livro Cadastrado: " + livro);
		
		return "/livros/lista?faces-redirect=true";
	}

	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}
}
