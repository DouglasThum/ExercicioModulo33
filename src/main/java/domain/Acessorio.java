package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ACESSORIO")
public class Acessorio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CODIGO", length = 10, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "NOME", length = 40, nullable = false, unique = true)
	private String nome;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TB_ACESSORIO_CARRO",
		joinColumns = @JoinColumn(name = "id_acessorio_fk"),
		inverseJoinColumns = @JoinColumn(name = "fk_carro_id"))
	private List<Carro> carros;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Carro> getCarros() {
		return carros;
	}
}
