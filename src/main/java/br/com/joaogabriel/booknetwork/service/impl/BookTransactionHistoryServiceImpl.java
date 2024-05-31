package br.com.joaogabriel.booknetwork.service.impl;

import br.com.joaogabriel.booknetwork.exception.ResourceNotFoundException;
import br.com.joaogabriel.booknetwork.model.entity.Book;
import br.com.joaogabriel.booknetwork.model.entity.BookTransactionHistory;
import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.repository.BookTransactionHistoryRepository;
import br.com.joaogabriel.booknetwork.service.BookTransactionHistoryService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BookTransactionHistoryServiceImpl implements BookTransactionHistoryService {

    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    public BookTransactionHistoryServiceImpl(BookTransactionHistoryRepository bookTransactionHistoryRepository) {
        this.bookTransactionHistoryRepository = bookTransactionHistoryRepository;
    }

    @Override
    public List<BookTransactionHistory> historyBorrowedBookByUser(Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        UUID id = user.getId();
        logger.log(Level.INFO, "Getting history of user: {0}", id);
        return bookTransactionHistoryRepository.findAllById(id);
    }

    @Override
    public UUID loan(Book book, User user) {
        BookTransactionHistory bookTransaction =
                new BookTransactionHistory(book, user, false, false);
        this.bookTransactionHistoryRepository.save(bookTransaction);
        return bookTransaction.getId();

    }

    @Override
    public void devolution(UUID transaction) {
        BookTransactionHistory bookTransaction =
                bookTransactionHistoryRepository.findById(transaction)
                        .orElseThrow(ResourceNotFoundException::new);
        bookTransaction.setReturned(true);
        bookTransaction.setReturnApproved(true);
        this.bookTransactionHistoryRepository.save(bookTransaction);
    }

    @Override
    public Book getBookByTransaction(UUID id) {
        BookTransactionHistory bookTransactionHistory =
                this.bookTransactionHistoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return bookTransactionHistory.getBook();
    }
}
