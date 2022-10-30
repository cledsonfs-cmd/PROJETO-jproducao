package org.jproducao.model.repository.ambiente;

import org.jproducao.model.entity.ambiente.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
