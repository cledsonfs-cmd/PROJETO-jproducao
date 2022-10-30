package org.jproducao.model.repository.ambiente;

import org.jproducao.model.entity.ambiente.Acesso;
import org.jproducao.model.entity.ambiente.Tela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelaRepository extends JpaRepository<Tela,Integer> {
}
