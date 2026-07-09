package com.Duoc.SceneIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Duoc.SceneIt.modelo.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula,Integer> { }
