package com.Duoc.SceneIt.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.Duoc.SceneIt.controller.UsuarioController;
import com.Duoc.SceneIt.modelo.Usuario;
import com.Duoc.SceneIt.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    /*
     Asegurate de estar en el directorio correcto
     FullStack_SceneIt\SceneIt\SceneIt
    */

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void testUsuarioExist(){
        Usuario usuario = new Usuario(1, "Gustavo", "Saavedra", "Gsaa@gmail.com", "123456", "30-04-2024", 16);
        when(usuarioService.saveUsuario(usuario)).thenReturn(usuario);
        var respuesta = usuarioController.saveUsuario(usuario);

        assertNotNull(respuesta);
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("Gustavo", body.getNombre_usuario());
    }
}
