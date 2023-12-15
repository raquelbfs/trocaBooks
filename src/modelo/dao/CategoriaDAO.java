package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Categoria;

public class CategoriaDAO extends JpaDAO<Categoria> {
	
	public List<Categoria> listar(String nome) {

		String conector = " where ";
		String jpql = "from Categoria c";

		if (nome != null)
		{
			jpql = jpql + conector + " c.nome like :nome";
			conector = " and ";
		}

		jpql = jpql + "  order by c.nome";

		TypedQuery<Categoria> comando = this.getEntityManager().createQuery(jpql, Categoria.class);

		if (nome != null)
			comando.setParameter("nome", "%" + nome + "%");

		return comando.getResultList();
	}
}
