package mapper;

import dto.DepartamentoDTO;
import model.Departamento;

public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {
    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        Departamento dep = new Departamento();
        if (item.getId() != null){
            dep.setId(item.getId());
        }
        dep.setNombre(item.getNombre());
        dep.setJefe(item.getJefe());
        dep.setPresupuesto(item.getPresupuesto());
        dep.setProgramadores(item.getProgramadores());
        return dep;
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        return DepartamentoDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .jefe(item.getJefe())
                .presupuesto(item.getPresupuesto())
                .programadores(item.getProgramadores())
                .build();
    }
}
