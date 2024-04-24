package br.com.joaogabriel.booknetwork.controller.auth;

import br.com.joaogabriel.booknetwork.payload.request.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.JWTTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Authentication Controller", description = "Endpoint to authenticate user")
public interface AuthenticationController {

    @Operation(summary = "Authentication.", description = "Request to authenticate user and generate JWT Token")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK.", content =
            { @Content(schema = @Schema(implementation = JWTTokenResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<JWTTokenResponse> authenticate(@RequestBody @Valid AuthenticateRequest authRequest);

}
