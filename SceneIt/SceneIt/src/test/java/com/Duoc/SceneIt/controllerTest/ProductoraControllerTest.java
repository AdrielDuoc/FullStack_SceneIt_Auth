package com.Duoc.SceneIt.controllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.Duoc.SceneIt.controller.ProductoraController;
import com.Duoc.SceneIt.modelo.Productora;
import com.Duoc.SceneIt.service.ProductoraService;

@ExtendWith(MockitoExtension.class)
public class ProductoraControllerTest {

    /*
     Asegurate de estar en el directorio correcto
     FullStack_SceneIt\SceneIt\SceneIt
    */

    @Mock
    private ProductoraService productoraService;

    @InjectMocks
    private ProductoraController productoraController;

    @Test
    public void TestProductoraExist(){

        Productora productora = new Productora(1, "Marvel");

        when(productoraService.saveProductora(productora)).thenReturn(productora);
        var respuesta = productoraController.saveProductora(productora);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals(1, body.getId_productora());
        assertEquals("Marvel", body.getNombre_productora());
    }
}
