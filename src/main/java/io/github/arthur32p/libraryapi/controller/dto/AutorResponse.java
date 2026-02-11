package io.github.arthur32p.libraryapi.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AutorResponse(UUID id, String nome, LocalDate dataNascimento, String nacionalidade) {
}
