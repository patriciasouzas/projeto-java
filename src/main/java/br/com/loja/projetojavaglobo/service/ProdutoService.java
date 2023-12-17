package br.com.loja.projetojavaglobo.service;

import br.com.loja.projetojavaglobo.exceptions.ProductNotFoundException;
import br.com.loja.projetojavaglobo.model.dto.RequestProdutoDto;
import br.com.loja.projetojavaglobo.model.entities.Produto;
import br.com.loja.projetojavaglobo.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }
    public Produto createProduto(RequestProdutoDto requestProdutoDto){
        var produto = new Produto();
        produto.setName(requestProdutoDto.getName());
        produto.setDescription(requestProdutoDto.getDescription());
        produto.setPrice(requestProdutoDto.getPrice());

        Produto savedProduto = produtoRepository.save(produto);

        return savedProduto;
    }

    public List<Produto> findProdutos() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado com Id: " + id));
    }

   public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado com Id: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
