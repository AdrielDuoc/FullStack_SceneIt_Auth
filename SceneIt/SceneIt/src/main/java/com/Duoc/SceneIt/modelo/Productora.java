package com.Duoc.SceneIt.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productoras")

public class Productora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_productora;

    @NotBlank
    private String nombre_productora;
}
