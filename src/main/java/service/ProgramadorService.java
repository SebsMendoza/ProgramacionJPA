package service;

import dto.ProgramadorDTO;
import mapper.ProgramadorMapper;
import model.Programador;
import repository.ProgramadorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProgramadorService extends BaseService<Programador, Long, ProgramadorRepository> {
    ProgramadorMapper mapper = new ProgramadorMapper();

    public ProgramadorService(ProgramadorRepository repository) {
        super(repository);
    }

    public List<ProgramadorDTO> getAllProgramadores() throws SQLException {
        return mapper.toDTO(this.findAll());
    }

    public ProgramadorDTO getProgramadorById(Long id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public ProgramadorDTO postProgrmador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.save(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }

    public ProgramadorDTO updateProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.update(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }

    public ProgramadorDTO deleteProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador prog = this.delete(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(prog);
    }
}
