package com.Duoc.SceneIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Duoc.SceneIt.modelo.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> { }
