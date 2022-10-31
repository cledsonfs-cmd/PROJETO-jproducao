package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer> {
}
