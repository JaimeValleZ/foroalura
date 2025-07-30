package com.aluralatam.foro.repository;

import com.aluralatam.foro.entity.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:curso%")
    Page<Topico> findByCurso(String curso, Pageable pageable);

    Optional<Topico> findById(Long id);
    void deleteById(Long id);

}
