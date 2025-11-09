package org.example.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "socio_clase")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SocioClase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SocioClaseID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSocio")
    @JoinColumn(name = "id_socio")
    private Socio socio;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idClase")
    @JoinColumn(name = "id_clase")
    private Clase clase;

    @Column
    private LocalDate fechaRegistro;

}
