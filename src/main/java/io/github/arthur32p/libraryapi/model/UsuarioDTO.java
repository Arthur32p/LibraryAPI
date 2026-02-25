package io.github.arthur32p.libraryapi.model;

import java.util.List;

public record UsuarioDTO(String login, String senha, List<String> roles) {
}
