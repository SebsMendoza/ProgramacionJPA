package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "programador")
@NamedQueries({
        @NamedQuery(name = "Programador.findAll", query = "SELECT p FROM Programador p")
})
public class Programador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 250)
    private String nombre;
    @Basic
    @Column(name = "experiencia", nullable = false, length = 250)
    private int exp;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Lenguajes> lenguajes;
    @Basic
    @Column(name = "salario", nullable = false)
    private double salario;
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "id_departamento", referencedColumnName = "id", nullable = false)
    private Departamento departamento;

    public Programador(String nombre, int exp, Set<Lenguajes> lenguajes, double salario, Departamento departamento) {
        this.nombre = nombre;
        this.exp = exp;
        this.lenguajes = lenguajes;
        this.salario = salario;
        this.departamento = departamento;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Set<Lenguajes> getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Set<Lenguajes> lenguajes) {
        this.lenguajes = lenguajes;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Programador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", exp=" + exp +
                ", lenguajes=" + lenguajes +
                ", salario=" + salario +
                ", departamento=" + departamento +
                "\n}";
    }
}
