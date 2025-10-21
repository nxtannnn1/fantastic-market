package mercury_market.infrastructure.repository;

import mercury_market.domain.model.Produto;
import mercury_market.domain.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByDetalhesProdutosNome(String nome);
    List<Produto> findByDetalhesProdutosCategoria(Categoria categoria);
}
