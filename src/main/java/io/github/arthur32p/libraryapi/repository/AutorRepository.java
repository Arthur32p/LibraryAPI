package io.github.arthur32p.libraryapi.repository;

import io.github.arthur32p.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}