package io.github.arthur32p.libraryapi.service;

import io.github.arthur32p.libraryapi.model.Livro;
import io.github.arthur32p.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}
