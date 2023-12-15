package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.EditoraDAO;
import modelo.dominio.Editora;

@ManagedBean(name = "editoraMB")
@RequestScoped
public class EditoraMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private String filtroNome;

	private Editora editora = new Editora();

	private List<Editora> lista = null;

	private EditoraDAO dao = new EditoraDAO();

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
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

	public List<Editora> getLista() {

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

	public void lerEditora() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.editora = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "editoraListar.jsf";
	}

	public String acaoAbrirInclusao() {
		return "editoraEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer codigo) {
		this.editora = this.dao.lerPorId(codigo);

		return "editoraEditar.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.editora);

		return this.acaoListar();
	}

	public String acaoExcluir(Integer codigo) {
		this.editora = this.dao.lerPorId(codigo);
		this.dao.excluir(this.editora);

		this.editora = null;
		this.lista = null;

		return this.acaoListar();
	}

}
