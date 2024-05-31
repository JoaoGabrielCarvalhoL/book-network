package br.com.joaogabriel.booknetwork.mapper;

import br.com.joaogabriel.booknetwork.model.entity.Book;
import br.com.joaogabriel.booknetwork.payload.request.book.BookPostRequest;
import br.com.joaogabriel.booknetwork.payload.response.book.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import br.com.joaogabriel.booknetwork.utils.FileUtils;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    CategoryMapper categoryMapper = new CategoryMapperImpl();
    AuthorMapper authorMapper = new AuthorMapperImpl();
    FeedbackMapper feedbackMapper = new FeedbackMapperImpl();
    FileUtils fileUtils = new FileUtils();

    @Mapping(target = "user", expression = "java(book.getOwner().getId())")
    @Mapping(target = "authorResponse", expression = "java(authorMapper.toAuthorResponse(book.getAuthor()))")
    @Mapping(target = "categoryResponse", expression = "java(categoryMapper.toCategoryResponse(book.getCategory()))")
    @Mapping(target = "feedbackResponse", expression = "java(feedbackMapper.toFeedbackResponseList(book.getFeedback()))")
    @Mapping(target = "cover", expression = "java(fileUtils.readFileFromLocation(book.getPathCoverPicture()))")
    BookResponse toBookResponse(Book book);

    Book toBook(BookPostRequest bookPostRequest);
}
