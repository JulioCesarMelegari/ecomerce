package backend;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.domain.Categoria;
import backend.domain.Cidade;
import backend.domain.Estado;
import backend.domain.Produto;
import backend.repositories.CategoriaRepository;
import backend.repositories.CidadeRepository;
import backend.repositories.EstadoRepository;
import backend.repositories.ProdutoRepository;

@SpringBootApplication
public class EcomerceApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcomerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria categoria1 = new Categoria(null, "bebidas");
		Categoria categoria2 = new Categoria(null, "alimentos");
	
		
		Produto produto1 = new Produto(null, "vinho", "vinicola1", "fornecedor1", "12253", 85.5, 52.5, 200);
		Produto produto2 = new Produto(null, "vinho2", "vinicola1", "fornecedor1", "12253", 85.5, 52.5, 200);
		Produto produto3 = new Produto(null, "vinho3", "vinicola1", "fornecedor1", "12253", 85.5, 52.5, 200);
		Produto produto4 = new Produto(null, "refrigerante", "vinicola1", "fornecedor1", "12253", 85.5, 52.5, 200);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1,produto3,produto2));
		categoria2.getProdutos().addAll(Arrays.asList(produto4));
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		produto4.getCategorias().addAll(Arrays.asList(categoria1));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1,categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3,produto4));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Paraná");
		
		Cidade c1 = new Cidade(null, "Campinas", est1);
		Cidade c2 = new Cidade(null, "Curitiba", est2);
		Cidade c3 = new Cidade(null, "Apucarana", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		
	}

}
