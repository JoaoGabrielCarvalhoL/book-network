package br.com.joaogabriel.booknetwork.specification;

import br.com.joaogabriel.booknetwork.model.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class BookSpecification {

    public static Specification<Book> withOwner(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("owner").get("id"), id));
    }

}
