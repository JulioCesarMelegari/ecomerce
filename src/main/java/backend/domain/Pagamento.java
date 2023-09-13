package backend.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import backend.domain.enums.EstadoPagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	@Id
	private Integer id; //id do pedido = id do pagamento
	
	private Integer estado;
	
	@JsonBackReference //nao serializa
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId //garante que o id do pagamento seja o mesmo do pedido
	private Pedido pedido;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}
