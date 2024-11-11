package com.capellax.grocery_app_backend.service.review;

import com.capellax.grocery_app_backend.dto.request.review.ReviewRequest;
import com.capellax.grocery_app_backend.dto.response.review.ReviewResponse;
import com.capellax.grocery_app_backend.exception.custom.CustomRuntimeException;
import com.capellax.grocery_app_backend.exception.enums.ErrorCode;
import com.capellax.grocery_app_backend.model.Product;
import com.capellax.grocery_app_backend.model.Review;
import com.capellax.grocery_app_backend.repository.ProductRepository;
import com.capellax.grocery_app_backend.response.ApiResponse;
import com.capellax.grocery_app_backend.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ProductRepository productRepository;
    private final ReviewServiceUtils reviewServiceUtils;
    private final ProductService productService;

    public ApiResponse<List<ReviewResponse>> getProductReviews(
            String productId
    ) {
        Product product = productService.getProductById(productId);

        List<ReviewResponse> reviews = product.getReviews().stream()
                .map(reviewServiceUtils::buildReviewResponse)
                .toList();

        return ApiResponse.success(reviews, "Product reviews fetched successfully");
    }

    public ApiResponse<ReviewResponse> addReviewToProduct(
            String productId,
            ReviewRequest request
    ) {
        Product product = productService.getProductById(productId);

        boolean alreadyReviewed = product.getReviews().stream()
                .anyMatch(review -> review.getUsername().equals(request.getUsername()));

        if (alreadyReviewed) {
            throw new CustomRuntimeException(ErrorCode.ALREADY_REVIEWED);
        }

        Review review = new Review();
        review.setUsername(request.getUsername());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setDate(LocalDate.now());

        product.getReviews().add(review);
        productRepository.save(product);

        ReviewResponse response = reviewServiceUtils.buildReviewResponse(review);
        return ApiResponse.success(response, "Review added successfully");
    }

    public ApiResponse<String> deleteReviewFromProduct(
            String productId,
            String username
    ) {
        Product product = productService.getProductById(productId);

        boolean removed = product.getReviews().removeIf(review -> review.getUsername().equals(username));
        if (!removed) {
            throw new CustomRuntimeException(ErrorCode.REVIEW_NOT_FOUND);
        }

        productRepository.save(product);
        return ApiResponse.success(null, "Review deleted successfully");
    }

}