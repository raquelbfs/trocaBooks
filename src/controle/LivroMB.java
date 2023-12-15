package controle;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import modelo.dao.CategoriaDAO;
import modelo.dao.LivroDAO;
import modelo.dao.EstadoDAO;
import modelo.dominio.Categoria;
import modelo.dominio.Livro;
import modelo.dominio.Estado;
import modelo.dominio.Usuario;
import modelo.dao.UsuarioDAO;
import modelo.dominio.Editora;
import modelo.dao.EditoraDAO;

@ManagedBean(name = "livroMB")
@RequestScoped
public class LivroMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;

	private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	@ManagedProperty(value="#{loginMB}")
	private LoginMB loginMB;

	private String filtroDescricao;
	private Categoria filtroCat;
	private Estado filtroStat;
	private Editora filtroEdi;
	private Usuario filtroUsu;

	private Livro livro = new Livro();

	private List<Livro> lista = null;

	private List<Categoria> categorias = null;
	
	private List<Editora> editoras = null;
	
	private List<Estado> estados = null;
	
	private List<Usuario> usuarios = null;

	private LivroDAO dao = new LivroDAO();

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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

	public List<Livro> getLista() {

		if (this.lista == null)
			this.lista = this.dao.listar(this.filtroDescricao, this.filtroCat, this.filtroStat, this.filtroUsu, this.filtroEdi);

		return lista;
	}

	public List<Categoria> getCategorias() {

		if (this.categorias == null) {
			CategoriaDAO daoCat = new CategoriaDAO();
			this.categorias = daoCat.lerTodos();
		}

		return categorias;
	}
	
	public List<Estado> getEstados() {

		if (this.estados == null) {
			EstadoDAO daoStat = new EstadoDAO();
			this.estados = daoStat.lerTodos();
		}

		return estados;
	}
	
	public List<Editora> getEditoras() {

		if (this.editoras == null) {
			EditoraDAO daoEdi = new EditoraDAO();
			this.editoras = daoEdi.lerTodos();
		}

		return editoras;
	}
	
	public List<Usuario> getUsuarios() {

		if (this.usuarios == null) {
			UsuarioDAO daoUsu = new UsuarioDAO();
			this.usuarios = daoUsu.lerTodos();
		}

		return usuarios;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public String getFiltroDescricao() {
		return filtroDescricao;
	}

	public void setFiltroDescricao(String filtroDescricao) {
		this.filtroDescricao = filtroDescricao;
	}

	public Categoria getFiltroCat() {
		return filtroCat;
	}

	public void setFiltroCat(Categoria filtroCat) {
		this.filtroCat = filtroCat;
	}
	
	public Estado getFiltroStat() {
		return filtroStat;
	}

	public void setFiltroStat(Estado filtroStat) {
		this.filtroStat = filtroStat;
	}
	
	public Usuario getFiltroUsu() {
		if (filtroUsu == null)
			filtroUsu = loginMB.getUsuario();
		
		return filtroUsu;
	}

	public void setFiltroUsu(Usuario filtroUsu) {
		this.filtroUsu = filtroUsu;
	}

	public Editora getFiltroEdi() {
		return filtroEdi;
	}

	public void setFiltroEdi(Editora filtroEdi) {
		this.filtroEdi = filtroEdi;
	}

	public void lerLivro() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.livro = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "livroListar.jsf";
	}
	
	public String acaoListarDisponiveis() {
		return "livroListarDisponiveis.jsf";
	}

	public String acaoAbrirInclusao() {
		return "livroEditar.jsf";
	}

	public String acaoAbrirAlteracao(Integer codigo) {
		this.livro = this.dao.lerPorId(codigo);

		return "livroEditar.jsf";
	}

	public String acaoSalvar() {

		byte[] arquivo = uploadedFile.getContents();
		if (arquivo != null && arquivo.length > 0)
			this.livro.setFoto(arquivo);

		this.dao.salvar(this.livro);

		return this.acaoListar();
	}

	public String acaoExcluir(Integer codigo) {

		this.livro = this.dao.lerPorId(codigo);
		this.dao.excluir(this.livro);

		this.livro = null;
		this.lista = null;

		return this.acaoListar();
	}

	public void download() throws IOException {

		FacesContext contexto = FacesContext.getCurrentInstance();
		ExternalContext external = contexto.getExternalContext();
		OutputStream saida = external.getResponseOutputStream();

		String codigo = external.getRequestParameterMap().get("codigo");

		external.responseReset();
		external.setResponseContentType("image/jpeg");
		external.setResponseHeader("Content-Disposition", "attachment; filename=foto-" + codigo + ".jpg");
		external.setResponseHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		external.setResponseHeader("Pragma", "no-cache");
		external.setResponseHeader("Expires", "0");

		Integer id = null;
		Livro livro = null;
		byte[] foto = null;
		try {
			id = Integer.parseInt(codigo);
			livro = dao.lerPorId(id);
			foto = livro.getFoto();
			
		} catch (Exception e) {
		}
		
		if (foto == null) {
			foto = IOUtils.toByteArray(getClass().getResourceAsStream("/resources/semfoto.jpg"));
		}

		external.setResponseContentLength(foto.length);
		saida.write(foto);
		contexto.responseComplete();
	}

}
