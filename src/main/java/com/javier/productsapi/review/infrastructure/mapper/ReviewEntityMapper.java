package com.javier.productsapi.review.infrastructure.mapper;

import com.javier.productsapi.review.domain.Review;
import com.javier.productsapi.review.infrastructure.ReviewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ReviewEntityMapper {

    @Mapping(target = "product", ignore = true)
    Review maptoReview(ReviewEntity reviewEntity);

    @Mapping(target = "productEntity", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);



}