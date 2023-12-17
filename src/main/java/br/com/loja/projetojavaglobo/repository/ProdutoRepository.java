package br.com.loja.projetojavaglobo.repository;

import br.com.loja.projetojavaglobo.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> { }
