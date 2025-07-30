package com.aluralatam.foro.repository;

import com.aluralatam.foro.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);

    @Query("""
        SELECT u.enabled
                FROM Usuario u
                        WHERE u.id = :id
        """)
    boolean findActivoById(Long id);
}
