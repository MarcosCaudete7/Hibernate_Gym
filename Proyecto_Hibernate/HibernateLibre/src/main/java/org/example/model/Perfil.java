package org.example.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
@Data
@NoArgsConstructor
@Entity
@Table (name = "perfil_fisico")

public class Perfil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_perfil")
    private Long idPerfil;

    @Column
    private float peso;

    @Column
    private float altura;

    @Enumerated(EnumType.STRING)
    @Column
    private Objetivo objetivo;
}
