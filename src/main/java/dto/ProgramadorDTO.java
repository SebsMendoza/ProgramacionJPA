package dto;

import lombok.*;
import model.Departamento;
import model.Lenguajes;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ProgramadorDTO {
    private Long id;
    private String nombre;
    private int exp;
    private Set<Lenguajes> lenguajes;
    private double salario;
    private Departamento departamento;

    @Override
    public String toString() {
        return "\nProgramadorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", exp=" + exp +
                ", lenguajes=" + lenguajes +
                ", salario=" + salario +
                ", departamento=" + departamento.getNombre() +
                "}";
    }
}
