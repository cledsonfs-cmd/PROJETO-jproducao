package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}
