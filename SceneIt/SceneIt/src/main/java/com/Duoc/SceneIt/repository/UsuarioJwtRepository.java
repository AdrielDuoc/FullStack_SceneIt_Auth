package com.Duoc.SceneIt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Duoc.SceneIt.modelo.UsuarioJwt;


@Repository
public interface UsuarioJwtRepository extends JpaRepository<UsuarioJwt, Integer> {
    Optional <UsuarioJwt> findByUsername(String username);
}
