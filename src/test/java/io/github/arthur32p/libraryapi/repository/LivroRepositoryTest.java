package io.github.arthur32p.libraryapi.repository;

import io.github.arthur32p.libraryapi.model.Autor;
import io.github.arthur32p.libraryapi.model.GeneroLivro;
import io.github.arthur32p.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(89.9));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("It a Coisa");
        livro.setDataPublicacao(LocalDate.of(1994, 7, 12));

        Autor autor = autorRepository.findById(UUID.fromString("7eb1ee27-da7d-471d-8277-b8026fe39225")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

}