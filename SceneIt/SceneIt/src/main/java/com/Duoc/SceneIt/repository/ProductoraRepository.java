package com.Duoc.SceneIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Duoc.SceneIt.modelo.Productora;

@Repository
public interface ProductoraRepository extends JpaRepository<Productora, Integer> { }
