package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa,Integer> {
}
