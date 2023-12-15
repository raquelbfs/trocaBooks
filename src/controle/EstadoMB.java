package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.EstadoDAO;
import modelo.dominio.Estado;

@ManagedBean(name = "estadoMB")
@RequestScoped
public class EstadoMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private String filtroNome;

	private Estado estado = new Estado();

	private List<Estado> lista = null;

	private EstadoDAO dao = new EstadoDAO();

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

	public List<Estado> getLista() {

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

	public void lerEstado() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.estado = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "estadoListar.jsf";
	}

	public String acaoAbrirInclusao() {
		return "estadoEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer codigo) {
		this.estado = this.dao.lerPorId(codigo);

		return "estadoEditar.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.estado);

		return this.acaoListar();
	}

	public String acaoExcluir(Integer codigo) {
		this.estado = this.dao.lerPorId(codigo);
		this.dao.excluir(this.estado);

		this.estado = null;
		this.lista = null;

		return this.acaoListar();
	}

}
