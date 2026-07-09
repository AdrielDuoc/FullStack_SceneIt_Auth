package com.Duoc.SceneIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Duoc.SceneIt.modelo.Autor;
import com.Duoc.SceneIt.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAutor(){
        return autorRepository.findAll();
    }

    public Autor saveAutor(Autor autor){
        return autorRepository.save(autor);
    }

    public Autor getAutorId(Integer id){
        return autorRepository.findById(id).orElse(null);
    }

    public Autor updateAutor(Autor autor){
        if(!autorRepository.existsById(autor.getId_autor())){
            return null;
        }
        return autorRepository.save(autor);
    }

    public void deleteAutor(Integer id){
        autorRepository.deleteById(id); 
    }

}
