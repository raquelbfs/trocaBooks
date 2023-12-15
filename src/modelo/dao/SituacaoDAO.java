package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Situacao;

public class SituacaoDAO extends JpaDAO<Situacao> {
	
	public List<Situacao> listar(String nome) {

		String conector = " where ";
		String jpql = "from Situacao s";

		if (nome != null)
		{
			jpql = jpql + conector + " s.nome like :nome";
			conector = " and ";
		}

		jpql = jpql + "  order by s.nome";

		TypedQuery<Situacao> comando = this.getEntityManager().createQuery(jpql, Situacao.class);

		if (nome != null)
			comando.setParameter("nome", "%" + nome + "%");

		return comando.getResultList();
	}
}
