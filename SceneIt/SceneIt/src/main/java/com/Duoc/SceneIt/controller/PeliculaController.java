package com.Duoc.SceneIt.controller;

import com.Duoc.SceneIt.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.Duoc.SceneIt.service.PeliculaService;

import jakarta.validation.Valid;

import com.Duoc.SceneIt.modelo.Pelicula;
import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> getAllPeliculas() {
        System.out.println("[PeliculaController] -> getAllPeliculas");
        return ResponseEntity.ok(peliculaService.getPelicula());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Integer id) {
        System.out.println("[PeliculaController] -> getPeliculaById id=" + id);
        Pelicula pelicula = peliculaService.getPeliculaId(id);
        if (pelicula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pelicula);
    }

    @PostMapping
    public ResponseEntity<Pelicula> savePelicula(@Valid @RequestBody Pelicula pelicula) {
        System.out.println("[PeliculaController] -> savePelicula");
        return ResponseEntity.status(201).body(peliculaService.savePelicula(pelicula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Integer id, @Valid @RequestBody Pelicula pelicula) {
        System.out.println("[PeliculaController] -> updatePelicula id=" + id);
        pelicula.setId_pelicula(id);
        Pelicula updatedPelicula = peliculaService.updatePelicula(pelicula);
        if (updatedPelicula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePelicula(@PathVariable int id) {
        System.out.println("[PeliculaController] -> removePelicula id=" + id);
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-genero")
    public ResponseEntity<List<PeliculaDTO>> PeliculasPorGenero(){
        System.out.println("[PeliculaController] -> PeliculasPorGenero");
        return ResponseEntity.ok(peliculaService.getPeliculaDTO());
    }
    @GetMapping("/test-error")
    public ResponseEntity<Pelicula> testError(){
        System.out.println("[PeliculaController] -> testError");
        throw new RuntimeException("Este es un error de prueba lanzado intencionalmente para compombrar GlobalExceptionHandler");
    }
    
}

