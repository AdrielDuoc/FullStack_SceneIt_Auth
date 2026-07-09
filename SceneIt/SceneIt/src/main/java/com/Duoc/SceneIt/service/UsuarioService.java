package com.Duoc.SceneIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Duoc.SceneIt.modelo.Usuario;
import com.Duoc.SceneIt.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> getUsuario () {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioId (Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario (Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario){
        if(!usuarioRepository.existsById(usuario.getId_usuario())){
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario (Integer id) {
        usuarioRepository.deleteById(id);
    }
}
