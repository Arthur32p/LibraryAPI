package io.github.arthur32p.libraryapi.controller;

import io.github.arthur32p.libraryapi.controller.dto.AutorDTO;
import io.github.arthur32p.libraryapi.controller.dto.AutorResponse;
import io.github.arthur32p.libraryapi.controller.dto.ErroResposta;
import io.github.arthur32p.libraryapi.controller.mappers.AutorMapper;
import io.github.arthur32p.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.arthur32p.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.arthur32p.libraryapi.model.Autor;
import io.github.arthur32p.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO dto){
        try {
            Autor autor = mapper.toEntity(dto);
            service.salvar(autor);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponse> obterDetalhes(@PathVariable("id") String id){
        var idAutor = UUID.fromString(id);

        return service
                .obterPorId(idAutor)
                .map(autor -> {
                    AutorResponse response = mapper.toResponse(autor);
                    return ResponseEntity.ok(response);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluir(@PathVariable("id") String id){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            service.excluir(autorOptional.get());

            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e){
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorResponse>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade){
        List<Autor> resultado =  service.pesquisaByExample(nome, nacionalidade);
        List<AutorResponse> lista = resultado.
                stream().
                map(mapper::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable("id") String id, @RequestBody @Valid AutorDTO dto){
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = service.obterPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dto.nome());
            autor.setNacionalidade(dto.nacionalidade());
            autor.setDataNascimento(dto.dataNascimento());

            service.atualizar(autor);

            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}
