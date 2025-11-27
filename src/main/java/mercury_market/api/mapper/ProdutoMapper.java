package mercury_market.api.mapper;

import mercury_market.api.dto.request.ProdutoRequest;
import mercury_market.api.dto.response.ProdutoResponse;
import mercury_market.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AvaliacaoMapper.class })
public interface ProdutoMapper {

    @Mapping(source = "detalhesProdutos.nome", target = "nome")
    @Mapping(source = "detalhesProdutos.preco", target = "preco")
    @Mapping(source = "detalhesProdutos.descricao", target = "descricao")
    @Mapping(source = "detalhesProdutos.quantidade", target = "quantidade")
    @Mapping(source = "detalhesProdutos.marca", target = "marca")
    @Mapping(source = "detalhesProdutos.categoria", target = "categoria")
    @Mapping(source = "detalhesProdutos.urlImagem", target = "urlImagem")
    ProdutoResponse toDTO(Produto produto);

    @Mapping(target = "id", ignore = true) // 👈 ignora o id gerado
    @Mapping(source = "nome", target = "detalhesProdutos.nome")
    @Mapping(source = "preco", target = "detalhesProdutos.preco")
    @Mapping(source = "descricao", target = "detalhesProdutos.descricao")
    @Mapping(source = "quantidade", target = "detalhesProdutos.quantidade")
    @Mapping(source = "marca", target = "detalhesProdutos.marca")
    @Mapping(source = "categoria", target = "detalhesProdutos.categoria")
    @Mapping(source = "urlImagem", target = "detalhesProdutos.urlImagem")
    @Mapping(target = "avaliacoes", ignore = true)
    Produto toEntity(ProdutoRequest produtoRequest);

    List<ProdutoResponse> toDTO(List<Produto> produtos);

}
