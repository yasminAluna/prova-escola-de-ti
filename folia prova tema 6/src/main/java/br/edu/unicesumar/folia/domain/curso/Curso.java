package br.edu.unicesumar.folia.domain.curso;

import br.edu.unicesumar.folia.domain.Entidade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "CURSO")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso extends Entidade {

    @NotBlank(message = "Nome do curso não pode ser vazio")
    @Column
    private String nome;
    @Column
    private String cargaHoraria;
    @Column
    private String dataInicio;
    @Column
    private String disciplina;

    public Curso(Curso curso) {
        this.nome = curso.getNome();
        this.cargaHoraria = curso.getCargaHoraria();
        this.dataInicio = curso.getDataInicio();
        this.disciplina = curso.getDisciplina();
    }

}