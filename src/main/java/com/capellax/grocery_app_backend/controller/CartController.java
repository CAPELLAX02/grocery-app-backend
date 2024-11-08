package com.capellax.grocery_app_backend.controller;

import com.capellax.grocery_app_backend.dto.request.cart.AddItemToCartRequest;
import com.capellax.grocery_app_backend.dto.request.cart.UpdateCartItemRequest;
import com.capellax.grocery_app_backend.dto.response.cart.CartResponse;
import com.capellax.grocery_app_backend.response.ApiResponse;
import com.capellax.grocery_app_backend.security.UserDetailsImpl;
import com.capellax.grocery_app_backend.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ApiResponse<CartResponse>> getCart() {
        String username = getAuthenticatedUsername();
        ApiResponse<CartResponse> response = cartService.getCart(username);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addItemToCart(
            @Valid @RequestBody AddItemToCartRequest request
    ) {
        String username = getAuthenticatedUsername();
        ApiResponse<String> response = cartService.addItemToCart(username, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<String>> updateCartItemQuantity(
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        String username = getAuthenticatedUsername();
        ApiResponse<String> response = cartService.updateCartItemQuantity(username, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ApiResponse<String>> removeItemFromCart(
            @PathVariable String productId
    ) {
        String username = getAuthenticatedUsername();
        ApiResponse<String> response = cartService.removeItemFromCart(username, productId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearCart() {
        String username = getAuthenticatedUsername();
        ApiResponse<String> response = cartService.clearCart(username);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    private String getAuthenticatedUsername() {
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();

        return userDetails.getUsername();
    }

}
