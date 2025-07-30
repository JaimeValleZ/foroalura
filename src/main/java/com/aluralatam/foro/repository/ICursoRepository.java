package com.aluralatam.foro.repository;

import com.aluralatam.foro.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso, Long> {
}
