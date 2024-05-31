package br.com.joaogabriel.booknetwork.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_book_transaction_history")
@EntityListeners(AuditingEntityListener.class)
public class BookTransactionHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean returned;

    @Column(nullable = false)
    private Boolean returnApproved;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(updatable = false)
    private UUID createdByUserWithId;

    @LastModifiedBy
    @Column(updatable = false)
    private UUID lastModifiedByUserId;

    public BookTransactionHistory() {}

    public BookTransactionHistory(Book book, User user, Boolean returned, Boolean returnApproved) {
        this.book = book;
        this.user = user;
        this.returned = returned;
        this.returnApproved = returnApproved;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    public Boolean getReturnApproved() {
        return returnApproved;
    }

    public void setReturnApproved(Boolean returnApproved) {
        this.returnApproved = returnApproved;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public UUID getCreatedByUserWithId() {
        return createdByUserWithId;
    }

    public void setCreatedByUserWithId(UUID createdByUserWithId) {
        this.createdByUserWithId = createdByUserWithId;
    }

    public UUID getLastModifiedByUserId() {
        return lastModifiedByUserId;
    }

    public void setLastModifiedByUserId(UUID lastModifiedByUserId) {
        this.lastModifiedByUserId = lastModifiedByUserId;
    }
}
