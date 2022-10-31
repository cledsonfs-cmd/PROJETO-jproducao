package org.jproducao.model.repository.pc;

import org.jproducao.model.entity.pc.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {
}
