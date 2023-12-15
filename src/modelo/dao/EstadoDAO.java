package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Estado;

public class EstadoDAO extends JpaDAO<Estado>{

	public List<Estado> listar(String nome) {

		String conector = " where ";
		String jpql = "from Estado e";

		if (nome != null)
		{
			jpql = jpql + conector + " e.nome like :nome";
			conector = " and ";
		}

		jpql = jpql + "  order by e.nome";

		TypedQuery<Estado> comando = this.getEntityManager().createQuery(jpql, Estado.class);

		if (nome != null)
			comando.setParameter("nome", "%" + nome + "%");

		return comando.getResultList();
	}
	
}
