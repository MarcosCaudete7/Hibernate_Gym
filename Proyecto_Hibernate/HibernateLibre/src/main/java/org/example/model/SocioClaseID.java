package org.example.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioClaseID implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_socio")
    private Long idSocio;

    @Column(name = "id_clase")
    private Long idClase;

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if(object == null || getClass() != object.getClass()) {
            return false;
        }
        SocioClaseID that = (SocioClaseID) object;
        return Objects.equals(idSocio, that.idSocio) && Objects.equals(idClase, that.idClase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSocio, idClase);
    }
}
