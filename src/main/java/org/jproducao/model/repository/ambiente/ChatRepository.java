package org.jproducao.model.repository.ambiente;

import org.jproducao.model.entity.ambiente.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
}
