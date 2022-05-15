import controller.DepartamentoController;
import controller.ProgramadorController;
import database.DataBaseController;
import dto.DepartamentoDTO;
import dto.ProgramadorDTO;
import manager.HibernateController;
import model.Departamento;
import model.Lenguajes;
import model.Programador;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

public class Empresa {
    Departamento d1, d2;
    Programador p1, p2, p3, p4, p5, p6, p7;
    private static Empresa instance;

    private Empresa() {

    }

    public static Empresa getInstance() {
        if (instance == null) {
            instance = new Empresa();
        }
        return instance;
    }

    /**
     * Método para comprobar la conexión a la BBDD
     */
    public void checkService() {
        DataBaseController controller = DataBaseController.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'Hello World'");
            if (rs.isPresent()) {
                rs.get().next();
                controller.close();
                System.out.println("Conexión correcta");
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Método para ejecutar un Script, ya sea para iniciar una BBDD con datos o para borrarlos
     *
     * @param script nombre del archivo Script que queremos ejecutar
     */
    public void resetData(String script) {
        System.out.println("Reseteando BBDD...");
        DataBaseController controller = DataBaseController.getInstance();
        String sqlFile = System.getProperty("user.dir") + File.separator + "sql" + File.separator + script;
        System.out.println(sqlFile);
        try {
            controller.open();
            controller.initData(sqlFile);
            controller.close();
            System.out.println("Datos eliminados correctamente");
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            System.exit(1);
        }
    }

    public void initDataBase() {
        HibernateController hc = HibernateController.getInstance();

        hc.open();

        //Departamentos
        System.out.println("Insertando departamentos...");
        hc.getTransaction().begin();
        d1 = new Departamento("Programación", 200000);
        d2 = new Departamento("BBDD", 100000);
        hc.getManager().persist(d1);
        hc.getManager().persist(d2);
        hc.getTransaction().commit();

        //Programadores
        System.out.println("Insertando programadores...");
        hc.getTransaction().begin();
        p1 = new Programador("Sebastian", 4, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java)), 3000, d1);
        p2 = new Programador("David", 7, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.C, Lenguajes.CSharp)), 4500, d1);
        p3 = new Programador("Alejandro", 2, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.Python)), 1600, d2);
        p4 = new Programador("Sandra", 2, new HashSet<Lenguajes>(Arrays.asList(Lenguajes.Java, Lenguajes.Python)), 1600, d2);
        hc.getManager().persist(p1);
        hc.getManager().persist(p2);
        hc.getManager().persist(p3);
        hc.getManager().persist(p4);
        hc.getTransaction().commit();
        hc.close();
    }

    /**
     * Método donde se ejecutan los CRUD para la tabla Departamento
     */
    public void Departamentos() {
        DepartamentoController dc = DepartamentoController.getInstance();

        System.out.println("Inicio DEPARTAMENTOS");
        System.out.println("--Obteniendo todos los departamentos--");
        System.out.println(dc.getAllDepartamentos());

        System.out.println("--Obteniendo departamento mediante su ID--");
        System.out.println(dc.getDepartamentoById(2L));

        System.out.println("--Insertando nuevo departamento--");
        DepartamentoDTO nuevoDept = DepartamentoDTO.builder()
                .nombre("Diseño")
                .presupuesto(200000)
                .build();
        dc.postDepartamento(nuevoDept);

        System.out.println("--Eliminando departamento--");
        dc.deleteDepartamento(dc.getDepartamentoById(3L));

    }

    /**
     * Método donde se ejecutan los CRUD para la tabla Programador
     */
    public void Programadores() {
        ProgramadorController pc = ProgramadorController.getInstance();

        System.out.println("Inicio PROGRAMADORES");
        System.out.println("--Obteniendo todos los programadores--");
        System.out.println(pc.getAllProgramadores());

        System.out.println("--Obteniendo programador mediante su ID--");
        System.out.println(pc.getProgramadorById(1L));

        System.out.println("--Insertando nuevo programador--");
        ProgramadorDTO nuevoProg = ProgramadorDTO.builder()
                .nombre("Juliana")
                .exp(5)
                .lenguajes(new HashSet<Lenguajes>(Arrays.asList(Lenguajes.CSharp, Lenguajes.Java)))
                .salario(4500)
                .departamento(d1)
                .build();
        pc.postProgramador(nuevoProg);

        System.out.println("--Eliminando programador--");
        pc.deleteProgramador(pc.getProgramadorById(3L));
    }
}
