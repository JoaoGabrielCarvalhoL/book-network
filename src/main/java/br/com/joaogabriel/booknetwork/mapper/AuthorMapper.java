package br.com.joaogabriel.booknetwork.mapper;

import br.com.joaogabriel.booknetwork.model.entity.Author;
import br.com.joaogabriel.booknetwork.payload.request.author.AuthorPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.author.AuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorMapper {

    Author toAuthor(AuthorPostRequest authorPostRequest);

    AuthorResponse toAuthorResponse(Author author);
}
