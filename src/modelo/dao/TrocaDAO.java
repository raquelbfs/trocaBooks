package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Situacao;
import modelo.dominio.Troca;

public class TrocaDAO extends JpaDAO<Troca>{
	
	public List<Troca> listar(Situacao sit) {

		String conector = " where ";
		String jpql = "from Troca t";

		if (sit != null)
		{
			jpql = jpql + conector + " t.situacao like :sit";
			conector = " and ";
		}

		jpql = jpql + "  order by t.situacao";

		TypedQuery<Troca> comando = this.getEntityManager().createQuery(jpql, Troca.class);

		if (sit != null)
			comando.setParameter("sit", "%" + sit + "%");

		return comando.getResultList();
	}

}
