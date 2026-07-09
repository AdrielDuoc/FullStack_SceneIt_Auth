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

import com.Duoc.SceneIt.modelo.Autor;
import com.Duoc.SceneIt.service.AutorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<Autor>> getAllAutores(){
        System.out.println("[AutorController] -> getAllAutores");
        return ResponseEntity.ok(autorService.getAutor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getUsuarioId(@PathVariable Integer id){
        System.out.println("[AutorController] -> getUsuarioId id=" + id);
        Autor autor = autorService.getAutorId(id);
        if(autor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<Autor> saveAutor(@Valid @RequestBody Autor autor){
        System.out.println("[AutorController] -> saveAutor");
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.saveAutor(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Integer id, @Valid @RequestBody Autor autor){
        System.out.println("[AutorController] -> updateAutor id=" + id);
        autor.setId_autor(id);
        Autor actualizado = autorService.updateAutor(autor);
        if(actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAutor(@PathVariable Integer id){
        System.out.println("[AutorController] -> deleteAutor id=" + id);
        autorService.deleteAutor(id);
        return ResponseEntity.noContent().build();
    }
}
