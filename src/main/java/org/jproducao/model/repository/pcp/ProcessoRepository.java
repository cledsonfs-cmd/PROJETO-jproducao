package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo,Integer> {
}
