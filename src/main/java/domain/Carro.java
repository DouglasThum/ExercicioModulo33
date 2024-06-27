package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_CARRO")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CODIGO", length = 10, nullable = false, unique = true)
	private String codigo;
	
	@Column(name = "MODELO", length = 20, nullable = false)
	private String modelo;
	
	@Column(name = "COR", length = 15, nullable = false)
	private String cor;
	
	@Column(name = "ANO", length = 4, nullable = false)
	private Long ano;
	
	@ManyToOne()
	@JoinColumn(name = "id_marca_fk",
		foreignKey = @ForeignKey(name = "fk_marca_carro"),
		referencedColumnName = "id", nullable = false)
	private Marca marca;
	
	@ManyToMany(mappedBy = "carros", cascade = CascadeType.ALL)
	List<Acessorio> acessorios;

	public Carro() {
		this.acessorios = new ArrayList<>();
	}
	
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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public void addAcessorio(Acessorio acessorio) {
		this.acessorios.add(acessorio);
	}
}
