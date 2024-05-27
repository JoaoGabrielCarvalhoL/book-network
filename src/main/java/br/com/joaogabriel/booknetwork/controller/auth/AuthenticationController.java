package br.com.joaogabriel.booknetwork.controller.auth;

import br.com.joaogabriel.booknetwork.payload.request.auth.AuthenticateRequest;
import br.com.joaogabriel.booknetwork.payload.response.auth.JWTTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Activate Account.", description = "Request to Activate Account")
    @ApiResponses(value = { @ApiResponse(responseCode = "202", description = "ACCEPTED", content =
            { @Content(schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request."),
            @ApiResponse(responseCode = "401", description = "Unauthorized."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error.")})
    @GetMapping("/activate-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<Void> activate(@RequestParam("token") String token) throws MessagingException;

}
