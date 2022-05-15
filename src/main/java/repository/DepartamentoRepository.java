package repository;

import manager.HibernateController;
import model.Departamento;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class DepartamentoRepository implements CrudRepository<Departamento, Long> {

    @Override
    public List<Departamento> findAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Departamento> query = hc.getManager().createNamedQuery("Departamento.findAll", Departamento.class);
        List<Departamento> list = query.getResultList();
        hc.close();
        return list;
    }

    @Override
    public Departamento getById(Long ID) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Departamento dep = hc.getManager().find(Departamento.class, ID);
        hc.close();
        if (dep != null)
            return dep;
        throw new SQLException("Error DepartamentoRepository -> No existe departamento con ID: " + ID);
    }

    @Override
    public Departamento save(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(departamento);
            hc.getTransaction().commit();
            hc.close();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al insertar departamento en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Departamento update(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(departamento);
            hc.close();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al actualizar departamento " + e.getMessage());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Departamento delete(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            departamento = hc.getManager().find(Departamento.class, departamento.getId());
            hc.getManager().remove(departamento);
            hc.getTransaction().commit();
            hc.close();
            return departamento;
        } catch (Exception e) {
            throw new SQLException("Error DepartamentoRepository al eliminar departamento con id: " + departamento.getId());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
