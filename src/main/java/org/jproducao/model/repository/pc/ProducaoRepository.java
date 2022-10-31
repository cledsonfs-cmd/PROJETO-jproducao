package org.jproducao.model.repository.pc;

import org.jproducao.model.entity.pc.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducaoRepository extends JpaRepository<Producao,Integer> {
}
