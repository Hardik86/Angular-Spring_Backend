package com.example.backend.services;

import com.example.backend.entities.Cart;
import com.example.backend.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    private Cart cart;
    private Set<CartItem> cartItems;
}