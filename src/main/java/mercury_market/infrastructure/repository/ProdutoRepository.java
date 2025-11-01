package mercury_market.infrastructure.repository;

import mercury_market.domain.model.Produto;
import mercury_market.domain.enums.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findByDetalhesProdutosCategoria(Categoria categoria, Pageable pageable);
    Page<Produto> findByDetalhesProdutosNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Produto> findByDetalhesProdutosNomeContainingIgnoreCaseAndDetalhesProdutosCategoria (String nome, Categoria categoria, Pageable pageable);
}
