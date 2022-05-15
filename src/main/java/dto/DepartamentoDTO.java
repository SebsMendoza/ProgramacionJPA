package dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import model.Programador;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
public class DepartamentoDTO {
    private Long id;
    private String nombre;
    private Programador jefe;
    private double presupuesto;
    private Set<Programador> programadores;

    @Override
    public String toString() {
        return "\nDepartamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe=" + jefe +
                ", presupuesto=" + presupuesto +
                ", programadores=" + programadores.stream().map(Programador::getNombre).collect(Collectors.toList()) +
                "}";
    }
}
