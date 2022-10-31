package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Faturamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturamentoRepository extends JpaRepository<Faturamento,Integer> {
}
