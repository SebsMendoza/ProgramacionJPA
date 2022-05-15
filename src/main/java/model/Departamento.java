package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
})
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "jefe_id", referencedColumnName = "id", nullable = true)
    private Programador jefe;
    @Basic
    @Column(name = "presupuesto", nullable = false, length = 250)
    private double presupuesto;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "departamento", cascade = CascadeType.MERGE)
    private Set<Programador> programadores;

    public Departamento(String nombre, double presupuesto) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Programador getJefe() {
        return jefe;
    }

    public void setJefe(Programador jefe) {
        this.jefe = jefe;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Set<Programador> getProgramadores() {
        return programadores;
    }

    public void setProgramadores(Set<Programador> programadores) {
        this.programadores = programadores;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", jefe=" + jefe +
                ", presupuesto=" + presupuesto +
                ", programadores=" + programadores.stream().map(Programador::getNombre).collect(Collectors.toList()) +
                '}';
    }
}
