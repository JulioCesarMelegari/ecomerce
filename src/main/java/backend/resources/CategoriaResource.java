package backend.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat = new Categoria(1, "bebidas");
		Categoria cat2 = new Categoria(1, "aperitivos");
		
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat);
		lista.add(cat2);
		
		return lista;
	}
}
