package com.Duoc.SceneIt.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import com.Duoc.SceneIt.controller.AutorController;
import com.Duoc.SceneIt.modelo.Autor;
import com.Duoc.SceneIt.service.AutorService;

@ExtendWith(MockitoExtension.class)
public class AutorControllerTest {

    /*
     Asegurate de estar en el directorio correcto
     FullStack_SceneIt\SceneIt\SceneIt
    */

    @Mock
    private AutorService autorService;

    @InjectMocks
    private AutorController autorController;

    @Test
    public void testAutorExist(){
        Autor autor = new Autor(1, "Juan", "Lobos", 35);

        when(autorService.saveAutor(autor)).thenReturn(autor);
        var respuesta = autorController.saveAutor(autor);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals(1, body.getId_autor());
        assertEquals("Juan", body.getNombre_autor());
        assertEquals("Lobos", body.getApellido_autor());
        assertEquals(35, body.getEdad_autor());
    }
}
