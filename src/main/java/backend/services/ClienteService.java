package backend.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.domain.Cliente;
import backend.repositories.ClienteRepository;
import backend.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> categoria = repository.findById(id);
		return categoria.orElse(null);
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> objCliente = repository.findById(id);
		return objCliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
				", Tipo: " + Cliente.class.getName()));
	}

}
