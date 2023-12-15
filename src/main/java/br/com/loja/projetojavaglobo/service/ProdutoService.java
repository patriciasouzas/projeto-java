package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.model.dto.RequestProdutoDto;
import br.com.loja.projetojavaglobo.model.entities.Produto;
import br.com.loja.projetojavaglobo.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    public Produto createProduto(RequestProdutoDto requestProdutoDto){
        var produto = new Produto();
        produto.setName(requestProdutoDto.getName());
        produto.setDescription(requestProdutoDto.getDescription());
        produto.setPrice(requestProdutoDto.getPrice());

        Produto savedProduto = produtoRepository.save(produto);

        return savedProduto;
    }

    public List<Produto> findProdutos() throws Exception {
        List<Produto> produtoList = produtoRepository.findAll();

        if (produtoList.isEmpty()) throw new Exception("Não encontramos produtos cadastrados.");

        return produtoList;
    }

    public Produto findById(Long id) throws Exception {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) throw new Exception("Esse produto não está cadastrado em nossa base de dados.");

        return produtoOptional.get();
    }

   /* public String deleteProduto(Long id) throws Exception {
        Produto produto = produtoRepository.deleteById(id);

        if (produto == null) throw new Exception("Esse produto não está cadastrado em nossa base de dados.");

        return "Produto removido com sucesso.";
    }*/
}
