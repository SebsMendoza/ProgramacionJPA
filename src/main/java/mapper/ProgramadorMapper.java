package mapper;

import dto.ProgramadorDTO;
import model.Programador;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {
    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        Programador prog = new Programador();
        if (item.getId() != null) {
            prog.setId(item.getId());
        }
        prog.setNombre(item.getNombre());
        prog.setExp(item.getExp());
        prog.setLenguajes(item.getLenguajes());
        prog.setSalario(item.getSalario());
        prog.setDepartamento(item.getDepartamento());
        return prog;
    }

    @Override
    public ProgramadorDTO toDTO(Programador item) {
        return ProgramadorDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .exp(item.getExp())
                .lenguajes(item.getLenguajes())
                .salario(item.getSalario())
                .departamento(item.getDepartamento())
                .build();
    }
}
