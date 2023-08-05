package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
