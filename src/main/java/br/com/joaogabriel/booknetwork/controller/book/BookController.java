package br.com.joaogabriel.booknetwork.controller.book;

import br.com.joaogabriel.booknetwork.payload.request.book.BookPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.book.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Book Controller", description = "Endpoint to manage books")
public interface BookController {

    @Operation(summary = "Create Book", description = "Request to save a new book into database.")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "CREATED", content =
            { @Content(schema = @Schema(implementation = BookResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EntityModel<BookResponse>> save(@RequestBody @Valid BookPostRequest bookPostRequest,
                                                   Authentication authentication);
}
