package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.CategoriaDAO;
import modelo.dominio.Categoria;

@ManagedBean(name = "categoriaMB")
@RequestScoped
public class CategoriaMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private String filtroNome;

	private Categoria categoria = new Categoria();

	private List<Categoria> lista = null;

	private CategoriaDAO dao = new CategoriaDAO();

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCodParam() {
		return codParam;
	}

	public void setCodParam(String codParam) {
		this.codParam = codParam;
	}

	public int getListaSize() {
		if (this.lista == null)
			return 0;

		return this.lista.size();
	}

	public List<Categoria> getLista() {

		if (this.lista == null)
			this.lista = this.dao.listar(this.filtroNome);

		return lista;
	}

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}

	public void lerCategoria() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.categoria = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "categoriaListar.jsf";
	}

	public String acaoAbrirInclusao() {
		return "categoriaEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer codigo) {
		this.categoria = this.dao.lerPorId(codigo);

		return "categoriaEditar.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.categoria);

		return this.acaoListar();
	}

	public String acaoExcluir(Integer codigo) {
		this.categoria = this.dao.lerPorId(codigo);
		this.dao.excluir(this.categoria);

		this.categoria = null;
		this.lista = null;

		return this.acaoListar();
	}

}
