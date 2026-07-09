package com.Duoc.SceneIt.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.Duoc.SceneIt.controller.PeliculaController;
import com.Duoc.SceneIt.modelo.Autor;
import com.Duoc.SceneIt.modelo.Pelicula;
import com.Duoc.SceneIt.modelo.Productora;
import com.Duoc.SceneIt.service.PeliculaService;

@ExtendWith(MockitoExtension.class)
public class PeliculaControllerTest {

    /*
     Asegurate de estar en el directorio correcto
     FullStack_SceneIt\SceneIt\SceneIt
    */

    @Mock
    private PeliculaService peliculaService;

    @InjectMocks
    private PeliculaController peliculaController;

    @Test
    public void testPeliculaExist(){
        Autor autor = new Autor(1,"Juan","Lobos",35);
        Productora productora = new Productora(1,"Marvel");
        Pelicula pelicula = new Pelicula(1, "Rapidos y furiosos 6", autor, "Algo de pelicula", "Accion", "2Hrs y 12 Minutos", "20-05-2012", productora);

        when(peliculaService.savePelicula(pelicula)).thenReturn(pelicula);
        var respuesta = peliculaController.savePelicula(pelicula);
        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("Rapidos y furiosos 6", body.getTitulo_pelicula());
    }
}
