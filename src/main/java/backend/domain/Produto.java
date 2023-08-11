package backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	private String fabricante;
	private String fornecedor;
	private String codigoBarras;
	private Double precoVenda;
	private Double custoMedio;
	private Integer estoque;
	
	@JsonBackReference //do outro lado da associaçcao ja foi buscado os objetos, vai entao omtir a lista de categoria para cada produto
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
			)
	private List<Categoria> categorias = new ArrayList<>();
	

	public Produto() {

	}

	public Produto(Integer id, String descricao, String fabricante, String fornecedor, String codigoBarras,
			Double precoVenda, Double custoMedio, Integer estoque) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.fornecedor = fornecedor;
		this.codigoBarras = codigoBarras;
		this.precoVenda = precoVenda;
		this.custoMedio = custoMedio;
		this.estoque = estoque;
	}
	
}
