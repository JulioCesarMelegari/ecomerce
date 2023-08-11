package backend.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.domain.Categoria;
import backend.repositories.CategoriaRepository;
import backend.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElse(null);
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> objCategoria = repository.findById(id);
		return objCategoria.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
				", Tipo: " + Categoria.class.getName()));
	}

}
