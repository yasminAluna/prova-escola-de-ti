package br.edu.unicesumar.folia.controller.curso;

import br.edu.unicesumar.folia.domain.curso.Curso;
import br.edu.unicesumar.folia.domain.curso.CursoRepository;
import br.edu.unicesumar.folia.domain.curso.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "Curso",

        description = "CRUD REST - create Curso, Update Curso, Delete Curso"

)
@RestController
@RequestMapping("api/curso")
public class CursoRestController {

    private final CursoService cursoService;

    private final CursoRepository repository;

    public CursoRestController(CursoService cursoService, CursoRepository repository) {
        this.cursoService = cursoService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid Curso curso){
        cursoService.salvaCurso(curso);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable UUID uuid){
        cursoService.deletaCurso(uuid);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Curso> atualizar(@PathVariable UUID uuid, @RequestBody Curso curso){
        cursoService.atualizaCurso(uuid, curso);
        return  new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @GetMapping
    public Page<Curso> listar(Pageable paginacao){
        return repository.findAll(paginacao).map(Curso::new);
    }

}