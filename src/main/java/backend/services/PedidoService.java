package backend.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.domain.Pedido;
import backend.repositories.PedidoRepository;
import backend.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElse(null);
	}
	
	public Pedido find(Integer id) {
		Optional<Pedido> objPedido = repository.findById(id);
		return objPedido.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
				", Tipo: " + Pedido.class.getName()));
	}

}
