package modelo.dominio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tab_trocas")
public class Troca {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TROCA")
	@SequenceGenerator(name = "ID_TROCA", sequenceName = "SEQ_TROCA", allocationSize = 1)
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name = "id_situacao_fk")
	private Situacao situacao;

	@NotNull
	@Size(min = 5, max = 120)
	@Column(name = "local_troca", length = 120, nullable = false)
	private String local_troca;
	
	@NotNull
	@Column(name = "data_hora_troca", nullable = false)
	private Date data_hora_troca;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_livro_solicita_fk")
	private Livro solicita;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_livro_oferece_fk")
	private Livro oferece;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_envia_fk")
	private Usuario envia;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_recebe_fk")
	private Usuario recebe;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getLocal_troca() {
		return local_troca;
	}

	public void setLocal_troca(String local_troca) {
		this.local_troca = local_troca;
	}

	public Date getData_hora_troca() {
		return data_hora_troca;
	}

	public void setData_hora_troca(Date data_hora_troca) {
		this.data_hora_troca = data_hora_troca;
	}

	public Livro getSolicita() {
		return solicita;
	}

	public void setSolicita(Livro solicita) {
		this.solicita = solicita;
	}

	public Livro getOferece() {
		return oferece;
	}

	public void setOferece(Livro oferece) {
		this.oferece = oferece;
	}
	
	public Usuario getEnvia() {
		return envia;
	}

	public void setEnvia(Usuario envia) {
		this.envia = envia;
	}

	public Usuario getRecebe() {
		return recebe;
	}

	public void setRecebe(Usuario recebe) {
		this.recebe = recebe;
	}

	@Override
	public String toString() {
		return "Troca [codigo=" + codigo + ", local_troca=" + local_troca + ", data_hora_troca=" + data_hora_troca
				+ "]";
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
		Troca other = (Troca) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
