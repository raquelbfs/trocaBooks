package modelo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import modelo.dominio.Categoria;
import modelo.dominio.Livro;
import modelo.dominio.Estado;
import modelo.dominio.Usuario;
import modelo.dominio.Editora;

public class LivroDAO extends JpaDAO<Livro> {
	
	public Livro lerPorCodigo(int codigo) {

		try {
			String jpql = "from Livro l where l.codigo= :codigo";
			TypedQuery<Livro> comando = this.getEntityManager().createQuery(jpql, Livro.class);
			comando.setParameter("codigo", codigo);
			return (Livro) comando.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public List<Livro> listar(String titulo, Categoria cat, Estado sta, Usuario usu, Editora edi) {

		String conector = " where ";
		String jpql = "from Livro l";

		if (titulo != null)
		{
			jpql = jpql + conector + " l.titulo like :titulo";
			conector = " and ";
		}

		if (cat != null)
		{
			jpql = jpql + conector + " l.categoria = :cat";
			conector = " and ";
		}
		
		if (sta != null)
		{
			jpql = jpql + conector + " l.estado = :sta";
			conector = " and ";
		}
		
		if (edi != null)
		{
			jpql = jpql + conector + " l.editora = :edi";
			conector = " and ";
		}
		
		if (usu != null)
		{
			jpql = jpql + conector + " l.usuario = :usu";
			conector = " and ";
		}

		jpql = jpql + "  order by l.titulo";

		TypedQuery<Livro> comando = this.getEntityManager().createQuery(jpql, Livro.class);

		if (titulo != null)
			comando.setParameter("titulo", "%" + titulo + "%");

		if (cat != null)
			comando.setParameter("cat", cat);
		
		if (sta != null)
			comando.setParameter("sta", sta);
		
		if (edi != null)
			comando.setParameter("edi", edi);
		
		if (usu != null)
			comando.setParameter("usu", usu);

		return comando.getResultList();
	}

}
