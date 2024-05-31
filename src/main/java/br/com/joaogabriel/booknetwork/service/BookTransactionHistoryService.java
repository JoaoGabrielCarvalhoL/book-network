package br.com.joaogabriel.booknetwork.service;

import br.com.joaogabriel.booknetwork.model.entity.Book;
import br.com.joaogabriel.booknetwork.model.entity.BookTransactionHistory;
import br.com.joaogabriel.booknetwork.model.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface BookTransactionHistoryService {

    List<BookTransactionHistory> historyBorrowedBookByUser(Authentication authentication);

    UUID loan(Book book, User user);

    void devolution(UUID transaction);

    Book getBookByTransaction(UUID id);
}
