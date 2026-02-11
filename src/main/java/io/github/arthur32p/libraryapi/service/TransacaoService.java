package io.github.arthur32p.libraryapi.service;

import io.github.arthur32p.libraryapi.model.Autor;
import io.github.arthur32p.libraryapi.model.GeneroLivro;
import io.github.arthur32p.libraryapi.model.Livro;
import io.github.arthur32p.libraryapi.repository.AutorRepository;
import io.github.arthur32p.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("688fa3fa-5133-43d2-ade1-230ac7c301f3")).orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 1, 5));
    }

    @Transactional
    public void executar(){
        Autor autor = new Autor();
        autor.setNome("Teste Juarez");
        autor.setNacionalidade("Frances");
        autor.setDataNascimento(LocalDate.of(1950, 3, 21));

        autorRepository.save(autor);


        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(89.9));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Teste Juarez Teste Livro");
        livro.setDataPublicacao(LocalDate.of(1994, 7, 12));


        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Juarez")){
            throw new RuntimeException("Rollback");
        }
    }

}
