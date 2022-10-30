package org.jproducao.model.repository.ambiente;

import org.jproducao.model.entity.ambiente.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Integer> {
}
