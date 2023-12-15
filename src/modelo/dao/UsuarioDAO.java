package modelo.dao;

import javax.persistence.TypedQuery;

import modelo.dominio.Usuario;

public class UsuarioDAO extends JpaDAO<Usuario> {
	
	public Usuario obter(String login) {
		
		String jpql = "from Usuario u  where u.login=:login  order by u.login";
		
		TypedQuery<Usuario> comando = this.getEntityManager().createQuery(jpql, Usuario.class);
		
		comando.setParameter("login", login);
		
		return comando.getSingleResult();
	}

}
