package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.UsuarioDAO;
import modelo.dominio.Usuario;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private String filtroLogin;

	private Usuario usuario = new Usuario();

	private List<Usuario> lista = null;

	private UsuarioDAO dao = new UsuarioDAO();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<Usuario> getLista() {

		if (this.lista == null)
			this.lista = this.dao.lerTodos();

		return lista;
	}

	public String getFiltroLogin() {
		return filtroLogin;
	}

	public void setFiltroLogin(String filtroLogin) {
		this.filtroLogin = filtroLogin;
	}

	public void lerUsuario() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.usuario = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "usuarioListar.jsf";
	}

	public String acaoAbrirInclusao() {
		return "usuarioEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer id) {
		this.usuario = this.dao.lerPorId(id);

		return "usuarioEditar.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.usuario);

		return this.acaoIndex();
	}

	public String acaoExcluir(Integer id) {
		this.usuario = this.dao.lerPorId(id);
		this.dao.excluir(this.usuario);

		this.usuario = null;
		this.lista = null;

		return this.acaoListar();
	}
	
	public String acaoIndex() {
		return "index.jsf";
	}

}
