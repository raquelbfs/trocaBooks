package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.SituacaoDAO;
import modelo.dominio.Situacao;

@ManagedBean(name = "situacaoMB")
@RequestScoped
public class SituacaoMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private String filtroNome;

	private Situacao situacao = new Situacao();

	private List<Situacao> lista = null;

	private SituacaoDAO dao = new SituacaoDAO();

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
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

	public List<Situacao> getLista() {

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

	public void lerSituacao() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.situacao = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "situacaoListar.jsf";
	}

	public String acaoAbrirInclusao() {
		return "situacaoEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer codigo) {
		this.situacao = this.dao.lerPorId(codigo);

		return "situacaoEditar.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.situacao);

		return this.acaoListar();
	}

	public String acaoExcluir(Integer codigo) {
		this.situacao = this.dao.lerPorId(codigo);
		this.dao.excluir(this.situacao);

		this.situacao = null;
		this.lista = null;

		return this.acaoListar();
	}

}
