package com.Duoc.SceneIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Duoc.SceneIt.modelo.Watchlist;
import com.Duoc.SceneIt.service.WatchlistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService watchlistService;

    @GetMapping
    public ResponseEntity<List<Watchlist>> getAllWatchlist(){
        System.out.println("[WatchlistController] -> getAllWatchlist");
        return ResponseEntity.ok(watchlistService.getWatchlist());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watchlist> getWatchlistId(@PathVariable Integer id){
        System.out.println("[WatchlistController] -> getWatchlistById id=" + id);
        Watchlist watchlist = watchlistService.getWatchlistId(id);
        if(watchlist == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(watchlist);
    }

    @PostMapping
    public ResponseEntity<Watchlist> saveWatchlist(@Valid @RequestBody Watchlist watchlist){
        System.out.println("[WatchlistController] -> saveWatchlist");
        return ResponseEntity.status(HttpStatus.CREATED).body(watchlistService.saveWatchlist(watchlist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Watchlist> updateWatchlist(@PathVariable Integer id, @Valid @RequestBody Watchlist watchlist){
        System.out.println("[WatchlistController] -> updateWatchlist id=" + id);
        watchlist.setId_watchlist(id);
        Watchlist actualizado = watchlistService.updateWatchlist(watchlist);
        if(actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWatchlist(@PathVariable Integer id){
        System.out.println("[WatchlistController] -> deleteWatchlist id=" + id);
        watchlistService.deleteWatchlist(id);
        return ResponseEntity.noContent().build();
    }
}
