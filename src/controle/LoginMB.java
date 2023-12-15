package controle;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.dao.UsuarioDAO;
import modelo.dominio.Usuario;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

	private boolean autenticado = false;
	private Usuario usuario = new Usuario(null, "(não autenticado)", null, null);

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String acaoAutenticar() {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = dao.obter(login);

		if ((usu != null) && (usu.senhaCorreta(this.senha))) {

			this.autenticado = true;
			this.usuario = usu;

			return "home.jsf";

		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário/senha incorretos!", null);
			FacesContext.getCurrentInstance().addMessage("senha", msg);

			return "index.jsf";
		}
	}
	
	public String acaoAutenticarAdmin() {

		UsuarioDAO dao = new UsuarioDAO();
		Usuario usu = dao.obter(login);

		if ((usu != null) && (usu.senhaCorreta(this.senha))) {

			this.autenticado = true;
			this.usuario = usu;

			return "homeAdmin.jsf";

		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário/senha incorretos!", null);
			FacesContext.getCurrentInstance().addMessage("senha", msg);

			return "indexAdmin.jsf";
		}
	}

	public String home() {
		return "home.jsf";
	}
	
	public String index() {
		return "index.jsf";
	}
	
	public String indexAdmin() {
		return "indexAdmin.jsf";
	}

	public String acaoLogout() {
		this.usuario = new Usuario(null, "(não autenticado)", null, null);
		this.autenticado = false;
		this.login = null;
		this.senha = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

		session.invalidate();

		return "index.jsf";
	}

}
