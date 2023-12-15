package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Editora;

public class EditoraDAO extends JpaDAO<Editora> {
	
	public List<Editora> listar(String nome) {

		String conector = " where ";
		String jpql = "from Editora e";

		if (nome != null)
		{
			jpql = jpql + conector + " e.nome like :nome";
			conector = " and ";
		}

		jpql = jpql + "  order by e.nome";

		TypedQuery<Editora> comando = this.getEntityManager().createQuery(jpql, Editora.class);

		if (nome != null)
			comando.setParameter("nome", "%" + nome + "%");

		return comando.getResultList();
	}
}
