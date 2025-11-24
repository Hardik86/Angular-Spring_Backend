package com.example.backend.services;

import com.example.backend.dao.CartRepository;
import com.example.backend.entities.Cart;
import com.example.backend.entities.CartItem;
import com.example.backend.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        cart.setStatus(StatusType.ordered);

        Set<CartItem> cartItems = purchase.getCartItems();
        for (CartItem cartItem : cartItems) {
            cart.getCartItem().add(cartItem);
            cartItem.setCart(cart);
        }

        cartRepository.save(cart);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}