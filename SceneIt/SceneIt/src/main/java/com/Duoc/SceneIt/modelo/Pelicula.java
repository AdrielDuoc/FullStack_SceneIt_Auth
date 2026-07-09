package com.Duoc.SceneIt.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pelicula;

    @NotBlank
    private String titulo_pelicula;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;


    @NotBlank
    private String descripcion;

    @NotBlank
    private String genero_pelicula;

    @NotBlank
    private String duracion;

    @NotNull
    private String fecha_estreno_pelicula;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_productora")
    private Productora productora;

}
