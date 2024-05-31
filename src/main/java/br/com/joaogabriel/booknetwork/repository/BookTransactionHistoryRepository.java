package br.com.joaogabriel.booknetwork.repository;

import br.com.joaogabriel.booknetwork.model.entity.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, UUID> {

    List<BookTransactionHistory> findAllById(UUID id);

    List<BookTransactionHistory> findAllByReturned(Boolean returned);

    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.owner.id = :id
            """)
    Page<BookTransactionHistory> findAllReturnedBooks(UUID id, Pageable pageable);
}
