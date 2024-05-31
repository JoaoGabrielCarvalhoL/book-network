package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.controller.book.BookController;
import br.com.joaogabriel.booknetwork.exception.BookNotAvailableException;
import br.com.joaogabriel.booknetwork.exception.IllegalBorrowException;
import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.mapper.BookMapper;
import br.com.joaogabriel.booknetwork.model.entity.Book;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.book.BookPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.book.BookResponse;
import br.com.joaogabriel.booknetwork.repository.BookRepository;
import br.com.joaogabriel.booknetwork.service.BookService;
import br.com.joaogabriel.booknetwork.service.BookTransactionHistoryService;
import br.com.joaogabriel.booknetwork.service.FileStorageService;
import br.com.joaogabriel.booknetwork.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());
    private final BookMapper bookMapper;
    private final BookTransactionHistoryService bookTransactionHistoryService;
    private final FileStorageService fileStorageService;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,
                           BookTransactionHistoryService bookTransactionHistoryService,
                           FileStorageService fileStorageService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookTransactionHistoryService = bookTransactionHistoryService;
        this.fileStorageService = fileStorageService;
    }


    @Override
    public EntityModel<BookResponse> save(BookPostRequest bookPostRequest, Authentication authentication) {
        logger.log(Level.INFO, "Saving book: {0}", bookPostRequest);
        User user = ((User) authentication.getPrincipal());
        Book book = bookMapper.toBook(bookPostRequest);
        book.setOwner(user);
        BookResponse saved = bookMapper.toBookResponse(bookRepository.save(book));
        Link link = WebMvcLinkBuilder.linkTo(BookController.class).slash(saved.id()).withSelfRel();
        EntityModel<BookResponse> response = EntityModel.of(saved);
        response.add(link);
        return response;
    }

    @Override
    public BookResponse findById(UUID id) {
        logger.log(Level.INFO, "Getting book by id: {0}", id);
        return this.bookRepository.findById(id).map(bookMapper::toBookResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<BookResponse> findAllAvailable() {
        return this.bookRepository.findAll().stream().filter(Book::getShareable)
                .filter(Predicate.not(Book::getArchived))
                .map(bookMapper::toBookResponse).toList();
    }

    @Override
    public Page<BookResponse> findAllByOwner(UUID id, Authentication authentication, Pageable pageable) {
        User user = ((User) authentication.getPrincipal());
        List<BookResponse> list =
                bookRepository.findAll(BookSpecification.withOwner(user.getId()), pageable)
                        .map(bookMapper::toBookResponse).toList();
        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public void changeStatusShareable(UUID id) {
        logger.log(Level.INFO, "Changing status shareable of book id: {0}", id);
        Book book = this.bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        book.setShareable(!book.getShareable());
        this.bookRepository.save(book);
    }

    @Override
    public void changeArchivedShareable(UUID id) {
        logger.log(Level.INFO, "Changing status archived of book id: {0}", id);
        Book book = this.bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        book.setArchived(!book.getArchived());
        this.bookRepository.save(book);
    }

    @Override
    public UUID borrow(UUID book, Authentication authentication) {
        Book borrow =
                this.bookRepository.findById(book)
                        .orElseThrow(ResourceNotFoundException::new);
        User user = ((User) authentication.getPrincipal());
        if (borrow.getArchived() || borrow.getShareable()) {
            throw new BookNotAvailableException("The book is not available for loan.");
        }
        if (Objects.equals(borrow.getOwner(), user)) throw new IllegalBorrowException();
        borrow.setOwner(user);
        borrow.setShareable(true);
        bookRepository.save(borrow);
        return bookTransactionHistoryService.loan(borrow, user);
    }

    @Override
    public void devolution(UUID transaction, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        Book devolution =
                this.bookTransactionHistoryService.getBookByTransaction(transaction);
        if (devolution.getArchived() || devolution.getShareable()) {
            throw new BookNotAvailableException("The book is not available for loan.");
        }
        if (Objects.equals(devolution.getOwner(), user)) throw new IllegalBorrowException();
        devolution.setShareable(false);
        bookTransactionHistoryService.devolution(transaction);
    }

    @Override
    public void upload(MultipartFile file, Authentication authentication, UUID bookId) {
        Book book =
                this.bookRepository.findById(bookId)
                        .orElseThrow(ResourceNotFoundException::new);
        User user = ((User) authentication.getPrincipal());
        String path = this.fileStorageService.save(file, user);
        book.setPathCoverPicture(path);
        this.bookRepository.save(book);
    }


}
