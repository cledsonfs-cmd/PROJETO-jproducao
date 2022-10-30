package org.jproducao.model.repository.ambiente;

import org.jproducao.model.entity.ambiente.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Integer> {
}
