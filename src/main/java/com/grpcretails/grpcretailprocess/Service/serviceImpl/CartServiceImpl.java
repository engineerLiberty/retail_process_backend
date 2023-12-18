package com.grpcretails.grpcretailprocess.Service.serviceImpl;

import com.grpcretails.grpcretailprocess.Service.CartService;
import com.grpcretails.grpcretailprocess.model.Cart;
import com.grpcretails.grpcretailprocess.model.Item;
import com.grpcretails.grpcretailprocess.model.User;
import com.grpcretails.grpcretailprocess.repository.CartRepository;
import com.grpcretails.grpcretailprocess.repository.ItemRepository;
import com.grpcretails.grpcretailprocess.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepo userRepo;
    private final ItemRepository itemRepository;
    private Map<String, Cart> userCarts;

    @Override
    public Cart getCart(String username) {
        User existingUser = userRepo.findByUsername(username).get();

        if (userCarts.containsKey(username)) {
            return userCarts.get(username);
        } else {
            Cart cart = Cart.builder().user(existingUser).build();
            userCarts.put(username, cart);
            return cart;
        }
    }

    @Override
    public void addItemToCart(String username, Item item) {

        Cart cart = getCart(username);

        // check if item already exists in cart
        boolean itemFound = false;
        for (Item cartItem : cart.getItems()) {
            if (cartItem.getId() == item.getId()) {
                itemFound = true;

                if (item.getQuantity() > 0){
                // increase item quantity
                int newQuantity = cartItem.getQuantity() + item.getQuantity();
                updateItemQuantityInCart(username, item.getId(), newQuantity);
                break;
            }
        }
        if (!itemFound) {
            // add new item
            cart = Cart.builder().items(cart.getItems()).build();
            userCarts.put(username, cart);
        }
    }
    }

    @Override
    public void updateItemQuantityInCart(String username, Long itemId, int quantity) {

        Cart cart = getCart(username);
        Optional<Item> item = itemRepository.findById(itemId);

        // update item quantity
           item.get().setQuantity(quantity);
        // update cart
        userCarts.put(username, cart);
    }

    public void removeItem(int itemId) {
        Cart cart = new Cart();
        List<Item> newList = cart.getItems();

        for (Item perItem :newList) {
            if (perItem.getId() == itemId) {
                newList.remove(itemId);
            }
        }
    }

    @Override
    public void removeItemFromCart(String username, int itemId) {

        Cart cart = getCart(username);
        removeItem(itemId);
        userCarts.put(username, cart);
    }

    @Override
    public void clearCart(String username) {
        Cart cart = getCart(username);
        cart.getItems().clear();

    }
}
