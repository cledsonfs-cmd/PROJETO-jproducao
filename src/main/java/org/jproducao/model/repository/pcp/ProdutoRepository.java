package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {
}
