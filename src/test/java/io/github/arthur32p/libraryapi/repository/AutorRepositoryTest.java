package io.github.arthur32p.libraryapi.repository;

import io.github.arthur32p.libraryapi.model.Autor;
import io.github.arthur32p.libraryapi.model.GeneroLivro;
import io.github.arthur32p.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Stephen King");
        autor.setNacionalidade("Estadunidense");
        autor.setDataNascimento(LocalDate.of(1950, 3, 21));

        Autor autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("b508f0d6-d84c-4bfb-a5c3-312d0b665157");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor:");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 7, 11));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("b508f0d6-d84c-4bfb-a5c3-312d0b665157");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("107ffd05-c6d3-45b0-9c62-9e321302ea21");
        var autor = repository.findById(id).get();
        repository.delete(autor);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Estadunidense");
        autor.setDataNascimento(LocalDate.of(1991, 2, 12));

        Livro livro = new Livro();
        livro.setIsbn("87642-98742");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Livro Livro");
        livro.setDataPublicacao(LocalDate.of(2014, 7, 3));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("87322-12342");
        livro2.setPreco(BigDecimal.valueOf(70));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("2 Livro");
        livro2.setDataPublicacao(LocalDate.of(2014, 7, 3));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }
}
