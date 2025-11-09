package org.example.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "socio")

public class Socio implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private Long idSocio;

    @Column (nullable = false)
    private String nombre;

    @Column (nullable = false, unique = true)
    private String email;

    @Column(name = "fecha_inscripcion")
    private LocalDate fecha_inscripcion;

    @OneToOne
    @JoinColumn(
            name = "id_perfil",
            referencedColumnName = "id_perfil",
            unique = true,
            foreignKey = @ForeignKey(name = "fk_socio_perfil"))
    private Perfil perfil;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocioClase> socioClases = new ArrayList<>();


    public Socio(String nombre, String email, LocalDate fecha_inscripcion) {
        this.nombre = nombre;
        this.email = email;
        this.fecha_inscripcion = fecha_inscripcion;
    }


}
