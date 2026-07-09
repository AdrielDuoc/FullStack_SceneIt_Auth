package com.Duoc.SceneIt.repository;

import com.Duoc.SceneIt.modelo.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> { }