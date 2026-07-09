package com.Duoc.SceneIt.service;

import java.util.List;

import com.Duoc.SceneIt.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Duoc.SceneIt.modelo.Pelicula;
import com.Duoc.SceneIt.repository.PeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getPelicula(){
        return peliculaRepository.findAll();
    }

    public Pelicula savePelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    public Pelicula getPeliculaId(Integer id){
        return peliculaRepository.findById(id).orElse(null);
    }

    public void deletePelicula(Integer id){
        peliculaRepository.deleteById(id);
    }

    public Pelicula updatePelicula(Pelicula pelicula){
        if(!peliculaRepository.existsById(pelicula.getId_pelicula())){
            return null;
        }
        return peliculaRepository.save(pelicula);
    }

    public List<PeliculaDTO> getPeliculaDTO(){
        return peliculaRepository.findAll().stream()
        .map(p -> new PeliculaDTO(
            p.getTitulo_pelicula(),
            p.getGenero_pelicula(),
            p.getProductora().getNombre_productora()
        ))
        .toList();
    }

}
