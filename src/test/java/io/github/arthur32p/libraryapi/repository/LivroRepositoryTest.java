package io.github.arthur32p.libraryapi.repository;

import io.github.arthur32p.libraryapi.model.Autor;
import io.github.arthur32p.libraryapi.model.GeneroLivro;
import io.github.arthur32p.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(89.9));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Terceiro Livro");
        livro.setDataPublicacao(LocalDate.of(1994, 7, 12));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Frances");
        autor.setDataNascimento(LocalDate.of(1950, 3, 21));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(89.9));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Livro");
        livro.setDataPublicacao(LocalDate.of(1994, 7, 12));

        Autor autor = new Autor();
        autor.setNome("Jose");
        autor.setNacionalidade("Ingles");
        autor.setDataNascimento(LocalDate.of(1950, 3, 21));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("89b02eff-22bf-4d27-a10c-1b988cc6284d");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID id_autor = UUID.fromString("77b6f3ab-bf55-44b1-9fff-a99acb6f8eaf");
        Autor autor = autorRepository.findById(id_autor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("89b02eff-22bf-4d27-a10c-1b988cc6284d");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("688fa3fa-5133-43d2-ade1-230ac7c301f3");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());

        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

}