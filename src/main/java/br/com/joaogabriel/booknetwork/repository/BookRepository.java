package br.com.joaogabriel.booknetwork.repository;

import br.com.joaogabriel.booknetwork.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>,
        JpaSpecificationExecutor<Book> {


}
