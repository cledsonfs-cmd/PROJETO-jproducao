package org.jproducao.model.repository.pcp;

import org.jproducao.model.entity.pcp.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
