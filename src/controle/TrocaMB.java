package controle;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import modelo.dao.LivroDAO;
import modelo.dao.SituacaoDAO;
import modelo.dao.TrocaDAO;
import modelo.dao.UsuarioDAO;
import modelo.dominio.Livro;
import modelo.dominio.Situacao;
import modelo.dominio.Troca;
import modelo.dominio.Usuario;

@ManagedBean(name = "trocaMB")
@RequestScoped
public class TrocaMB implements Serializable {

	@ManagedProperty("#{param.codParam}")
	private String codParam;
	
	private String filtroDescricao;
	private Situacao filtroSit;
	private int filtroCodigo;

	private Troca troca = new Troca();

	private List<Troca> lista = null;
	
	private List<Situacao> situacoes = null;

	private List<Livro> livros = null;
	
	private Livro liv;

	private List<Usuario> usuarios = null;

	private TrocaDAO dao = new TrocaDAO();

	public Troca getTroca() {
		return troca;
	}

	public void setTroca(Troca troca) {
		this.troca = troca;
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

	public List<Troca> getLista() {

		if (this.lista == null)
			this.lista = this.dao.listar(this.filtroSit);

		return lista;
	}
	
	public List<Situacao> getSituacoes() {

		if (this.situacoes == null) {
			SituacaoDAO daoSit = new SituacaoDAO();
			this.situacoes = daoSit.lerTodos();
		}
		return situacoes;
	}

	public Livro getLiv() {
		if (this.liv == null) {
			LivroDAO livDao = new LivroDAO();
			liv = livDao.lerPorCodigo(filtroCodigo);
		}
		
		return liv;
	}

	public void setLiv(Livro liv) {
		this.liv = liv;
	}

	public List<Livro> getLivros() {

		if (this.livros == null) {
			LivroDAO daoLiv = new LivroDAO();
			this.livros = daoLiv.lerTodos();
		}

		return livros;
	}
	
	public List<Usuario> getUsuarios() {

		if (this.usuarios == null) {
			UsuarioDAO daoUsu = new UsuarioDAO();
			this.usuarios = daoUsu.lerTodos();
		}

		return usuarios;
	}
	
	public String getFiltroDescricao() {
		return filtroDescricao;
	}

	public void setFiltroDescricao(String filtroDescricao) {
		this.filtroDescricao = filtroDescricao;
	}

	public Situacao getFiltroSit() {
		return filtroSit;
	}

	public void setFiltroSit(Situacao filtroSit) {
		this.filtroSit = filtroSit;
	}

	public int getFiltroCodigo() {
		return filtroCodigo;
	}

	public void setFiltroCodigo(int filtroCodigo) {
		this.filtroCodigo = filtroCodigo;
	}

	public void lerTroca() {

		if (codParam != null) {
			try {
				Integer id = Integer.parseInt(this.codParam);
				this.troca = this.dao.lerPorId(id);
			} catch (Exception e) {
			}
		}
	}

	public String acaoListar() {
		return "trocaListar.jsf";
	}

	public String acaoAbrirInclusao() {
		
		SituacaoDAO daoSit = new SituacaoDAO();
		Situacao sit = daoSit.lerPorId(1);
		
		this.troca = new Troca();
		this.troca.setSituacao(sit);
		this.filtroCodigo = 0;
		
		return "trocaEditar.jsf";
	}

	public String acaoResponder(Integer codigo) {
		this.troca = this.dao.lerPorId(codigo);

		return "trocaResponder.jsf";
	}

	public String acaoSalvar() {
		this.dao.salvar(this.troca);

		return this.acaoListar();
	}
	
	public String acaoAceitar() {
		
		SituacaoDAO daoSit = new SituacaoDAO();
		Situacao sit = daoSit.lerPorId(2);
		
		this.troca.setSituacao(sit);
		this.dao.salvar(this.troca);
		
		return this.acaoListar();
	}
	
	public String acaoRecusar() {
		
		SituacaoDAO daoSit = new SituacaoDAO();
		Situacao sit = daoSit.lerPorId(3);
		
		this.troca.setSituacao(sit);
		this.dao.salvar(this.troca);
		
		return this.acaoListar();
	}

	public String acaoCancelar() {

		SituacaoDAO daoSit = new SituacaoDAO();
		Situacao sit = daoSit.lerPorId(4);
		
		this.troca.setSituacao(sit);
		this.dao.salvar(this.troca);
		
		return this.acaoListar();
	}
	
}
