package com.example.backend.services;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}