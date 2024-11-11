package com.capellax.grocery_app_backend.controller;

import com.capellax.grocery_app_backend.dto.request.review.ReviewRequest;
import com.capellax.grocery_app_backend.dto.response.review.ReviewResponse;
import com.capellax.grocery_app_backend.response.ApiResponse;
import com.capellax.grocery_app_backend.security.UserDetailsImpl;
import com.capellax.grocery_app_backend.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}/all")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getProductReviews(
            @PathVariable String productId
    ) {
        ApiResponse<List<ReviewResponse>> response = reviewService.getProductReviews(productId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/{productId}/add")
    public ResponseEntity<ApiResponse<ReviewResponse>> addProductReview(
            @PathVariable String productId,
            @Valid @RequestBody ReviewRequest reviewRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewRequest.setUsername(userDetails.getUsername());
        ApiResponse<ReviewResponse> response = reviewService.addReviewToProduct(productId, reviewRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse<String>> deleteReview(
            @PathVariable String productId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String username = userDetails.getUsername();
        ApiResponse<String> response = reviewService.deleteReviewFromProduct(productId, username);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
