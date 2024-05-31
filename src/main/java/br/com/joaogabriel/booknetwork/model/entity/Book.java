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
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@EntityListeners(AuditingEntityListener.class)
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String title;

    private String pathCoverPicture;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedback;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    @Lob
    private String synopsis;

    @Column(nullable = false)
    private Boolean archived;

    @Column(nullable = false)
    private Boolean shareable;

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

    public Book() {}

    public Book(String title, Author author, String isbn, String synopsis,
                Boolean archived, Boolean shareable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.archived = archived;
        this.shareable = shareable;
    }

    public Book(UUID id, String title, Author author, User owner,
                List<Feedback> feedback, List<BookTransactionHistory> history, String isbn,
                String synopsis, Boolean archived, Boolean shareable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.owner = owner;
        this.feedback = feedback;
        this.histories = history;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.archived = archived;
        this.shareable = shareable;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public List<BookTransactionHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<BookTransactionHistory> histories) {
        this.histories = histories;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(Boolean shareable) {
        this.shareable = shareable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getPathCoverPicture() {
        return pathCoverPicture;
    }

    public void setPathCoverPicture(String pathCoverPicture) {
        this.pathCoverPicture = pathCoverPicture;
    }
}
