package br.com.joaogabriel.booknetwork.mapper;

import br.com.joaogabriel.booknetwork.model.entity.Category;
import br.com.joaogabriel.booknetwork.payload.response.category.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
}
