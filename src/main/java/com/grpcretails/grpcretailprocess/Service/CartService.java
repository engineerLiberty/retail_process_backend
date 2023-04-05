package com.grpcretails.grpcretailprocess.Service;

import com.grpcretails.grpcretailprocess.model.Cart;
import com.grpcretails.grpcretailprocess.model.Item;

public interface CartService {

    Cart getCart(String username);

    void addItemToCart(String username, Item item);

    void updateItemQuantityInCart(String username, Long itemId, int quantity);

    void removeItemFromCart(String username, int itemId);

    void clearCart(String username);
}
