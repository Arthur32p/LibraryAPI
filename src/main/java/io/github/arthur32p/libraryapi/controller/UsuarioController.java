package io.github.arthur32p.libraryapi.controller;

import io.github.arthur32p.libraryapi.controller.mappers.UsuarioMapper;
import io.github.arthur32p.libraryapi.model.UsuarioDTO;
import io.github.arthur32p.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto){
        var usuario = mapper.toEntity(dto);

        service.salvar(usuario);
    }
}
