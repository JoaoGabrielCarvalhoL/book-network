package br.com.joaogabriel.booknetwork.controller.book.impl;

import br.com.joaogabriel.booknetwork.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookControllerImpl {

    private final BookService bookService;

    public BookControllerImpl(BookService bookService){
        this.bookService = bookService;
    }
}
