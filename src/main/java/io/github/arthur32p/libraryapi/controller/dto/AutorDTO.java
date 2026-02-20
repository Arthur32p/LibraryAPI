package io.github.arthur32p.libraryapi.controller.dto;

import io.github.arthur32p.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AutorDTO(@NotBlank(message = "Campo obrigatório") @Size(max = 100, message = "Campo fora do tamanho padrão") String nome, @NotNull(message = "Campo obrigatório") @Past(message = "Não pode ser uma data futura") LocalDate dataNascimento, @NotBlank(message = "Campo obrigatório") @Size(max = 50, min = 2, message = "campo fora do tamanho padrão") String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }
}
