package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioDao {

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}
}
