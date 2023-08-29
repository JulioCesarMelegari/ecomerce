package backend;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.domain.Categoria;
import backend.domain.Cidade;
import backend.domain.Cliente;
import backend.domain.Endereco;
import backend.domain.Estado;
import backend.domain.Pagamento;
import backend.domain.PagamentoComBoleto;
import backend.domain.PagamentoComCartao;
import backend.domain.Pedido;
import backend.domain.Produto;
import backend.domain.enums.EstadoPagamento;
import backend.domain.enums.TipoCliente;
import backend.repositories.CategoriaRepository;
import backend.repositories.CidadeRepository;
import backend.repositories.ClienteRepository;
import backend.repositories.EnderecoRepository;
import backend.repositories.EstadoRepository;
import backend.repositories.PagamentoRepository;
import backend.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

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
		
		Cliente cli1 = new Cliente(null, "cliente1", "cliente1@gmail", "458545", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1245","69656"));
		
		Endereco e1 = new Endereco(null, "Rua 1", "45", "casa 56", "Alameda", "4555", cli1, c3);
		Endereco e2 = new Endereco(null, "Rua 2", "4s5", "ap 6", "Frances", "435", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, data.parse("28/08/2023 21:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, data.parse("15/05/2023 21:30"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,data.parse("03/08/2023 22:30"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1,pgto2));
	}

}
