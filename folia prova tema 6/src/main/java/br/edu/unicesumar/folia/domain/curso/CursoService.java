package br.edu.unicesumar.folia.domain.curso;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso salvaCurso(Curso curso, Curso AdicionarNome){
        curso.setNome(AdicionarNome.removeNonDigits(curso.getNome()))
    };

    public void deletaCurso(UUID uuid){
        Curso curso = cursoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        cursoRepository.delete(curso);

    }

    public Curso atualizaCurso(UUID uuid, Curso cursoAtualizado) {
        Curso cursoExistente = cursoRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        cursoExistente.setNome(cursoAtualizado.getNome());
        cursoExistente.setCargaHoraria(cursoAtualizado.getCargaHoraria());
        cursoExistente.setDataInicio(cursoAtualizado.getDataInicio());
        cursoExistente.setDisciplina(cursoAtualizado.getDisciplina());
        salvaCurso(cursoExistente);
        return cursoExistente;
    }
}