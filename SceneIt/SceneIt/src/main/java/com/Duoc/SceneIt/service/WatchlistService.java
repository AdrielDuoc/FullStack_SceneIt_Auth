package com.Duoc.SceneIt.service;

import com.Duoc.SceneIt.modelo.Watchlist;
import com.Duoc.SceneIt.repository.WatchlistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;

    public List<Watchlist> getWatchlist () {
        return watchlistRepository.findAll();
    }

    public Watchlist saveWatchlist(Watchlist watchlist){
        return watchlistRepository.save(watchlist);
    }

    public Watchlist getWatchlistId(Integer id){
        return watchlistRepository.findById(id).orElse(null);
    }

    public void deleteWatchlist(Integer id){
        watchlistRepository.deleteById(id);
    }

    public Watchlist updateWatchlist(Watchlist watchlist){
        if(!watchlistRepository.existsById(watchlist.getId_watchlist())){
            return null;
        }
        return watchlistRepository.save(watchlist);
    }

}
