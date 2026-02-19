package com.example.E_CommerceOrder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.E_CommerceOrder.dto.AddToCartRequestdto;
import com.example.E_CommerceOrder.dto.CartItemResponsedto;
import com.example.E_CommerceOrder.dto.CartResponsedto;
import com.example.E_CommerceOrder.entity.Cart;
import com.example.E_CommerceOrder.entity.CartItem;
import com.example.E_CommerceOrder.entity.Product;
import com.example.E_CommerceOrder.entity.User;
import com.example.E_CommerceOrder.repository.CartRepo;
import com.example.E_CommerceOrder.repository.ProductRepo;
import com.example.E_CommerceOrder.repository.UserRepo;
import com.example.E_CommerceOrder.service.CartService;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public CartServiceImpl(
            CartRepo cartRepo,
            UserRepo userRepo,
            ProductRepo productRepo
    ) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    // 🔁 MAP CART → RESPONSE DTO
    private CartResponsedto mapToDto(Cart cart) {

        double total = 0;
        List<CartItemResponsedto> items = new ArrayList<>();

        for (CartItem item : cart.getItems()) {

            double price = item.getProduct().getPrice();
            int qty = item.getQuantity();

            items.add(new CartItemResponsedto(
                    item.getProduct().getProductId(),
                    item.getProduct().getProductName(),
                    price,
                    qty
            ));

            total += price * qty;
        }

        return new CartResponsedto(
                cart.getCartId(),
                items,
                total
        );
    }

    // 🛒 ADD TO CART
    @Override
    public CartResponsedto addToCart(AddToCartRequestdto dto, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int availableStock = product.getInventory().getQuantityAvailable();

        if (dto.getQuantity() > availableStock) {
            throw new RuntimeException("Not enough stock available");
        }

        Cart cart = cartRepo.findByUser(user).orElse(null);

        // CREATE CART IF NOT EXISTS
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
            cart = cartRepo.save(cart);
        }

        boolean found = false;

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + dto.getQuantity());
                found = true;
                break;
            }
        }

        if (!found) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(dto.getQuantity());
            cart.getItems().add(item);
        }

        return mapToDto(cartRepo.save(cart));
    }

    // 👀 VIEW CART
    @Override
    public CartResponsedto viewCart(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        return mapToDto(cart);
    }

    // 🧹 CLEAR CART
    @Override
    public void clearCart(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepo.findByUser(user).orElse(null);

        if (cart != null) {
            cartRepo.delete(cart);
        }
    }
}