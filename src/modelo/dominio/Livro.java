package modelo.dominio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tab_livros")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIVRO")
	@SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO", allocationSize = 1)
	private Integer codigo;

	@NotNull
	@Size(max = 45)
	@Column(length = 45, nullable = false)
	private String titulo;
	
	@NotNull
	@Size(max = 45)
	@Column(length = 45, nullable = false)
	private String autor;
		
	private int ano;
	
	@NotNull
	@Column(nullable = false)
	private Date data_cadastro;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_fk")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "id_categoria_fk")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "id_editora_fk")
	private Editora editora;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_fk")
	private Usuario usuario;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Troca solicita;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Troca oferece;

	@Lob
	private byte[] foto;
	
	public Livro() {
		super();
	}

	public Livro(String titulo, String autor, int ano, Date data_cadastro) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.ano = ano;
		this.data_cadastro = data_cadastro;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Troca getSolicita() {
		return solicita;
	}

	public void setSolicita(Troca solicita) {
		this.solicita = solicita;
	}

	public Troca getOferece() {
		return oferece;
	}

	public void setOferece(Troca oferece) {
		this.oferece = oferece;
	}

	@Override
	public String toString() {
		return "Livro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", ano="
				+ ano + ", data_cadastro=" + data_cadastro + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
