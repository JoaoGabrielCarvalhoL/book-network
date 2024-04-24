package br.com.joaogabriel.booknetwork.mapper;

import br.com.joaogabriel.booknetwork.model.entity.User;
import br.com.joaogabriel.booknetwork.payload.request.user.UserPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.user.UserCreationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUser(UserPostRequest userPostRequest);

    UserCreationResponse toUserCreationResponse(User user);
}
