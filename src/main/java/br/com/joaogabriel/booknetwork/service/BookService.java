package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.payload.request.book.BookPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.book.BookResponse;
import jakarta.mail.Multipart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BookService {

    EntityModel<BookResponse> save(final BookPostRequest bookPostRequest, Authentication authentication);

    BookResponse findById(final UUID id);

    List<BookResponse> findAllAvailable();

    Page<BookResponse> findAllByOwner(final UUID id, Authentication authentication, Pageable pageable);

    void changeStatusShareable(final UUID id);

    void changeArchivedShareable(final UUID id);

    UUID borrow(UUID book, Authentication authentication);

    void devolution(UUID transaction, Authentication authentication);

    void upload(MultipartFile file, Authentication authentication, UUID bookId);
}
