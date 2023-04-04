package com.grpcretails.grpcretailprocess.shoppingService;

import com.grpcretails.grpcretailprocess.AddToCartResponse;
import com.grpcretails.grpcretailprocess.InventoryServiceGrpc;
import com.grpcretails.grpcretailprocess.model.Cart;
import com.grpcretails.grpcretailprocess.model.Item;
import com.grpcretails.grpcretailprocess.repository.CartRepository;
import com.grpcretails.grpcretailprocess.repository.ItemRepository;
import com.grpcretails.grpcretailprocess.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RetailService extends InventoryServiceGrpc.InventoryServiceImplBase {
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    @Override
    public void addToCart(com.grpcretails.grpcretailprocess.AddToCartRequest request,
                          io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.AddToCartResponse> responseObserver) {

        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found"));
        int quantity = request.getQuantity();
        if (quantity > item.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }
        Cart cart = new Cart();
        Map<Long, Integer> items = cart.getItems();
        int existingQuantity = items.getOrDefault(item.getId(), 0);
        items.put(item.getId(), existingQuantity + quantity);
        cart.setLastModifiedDate(LocalDateTime.now());
        cartRepository.save(cart);

        AddToCartResponse response = AddToCartResponse
                .newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeFromCart(com.grpcretails.grpcretailprocess.RemoveFromCartRequest request,
                               io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.RemoveFromCartResponse> responseObserver) {
    }

    @Override
    public void updateCart(com.grpcretails.grpcretailprocess.UpdateCartRequest request,
                           io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.UpdateCartResponse> responseObserver) {
    }

    @Override
    public void viewCart(com.grpcretails.grpcretailprocess.ViewCartRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.ViewCartResponse> responseObserver) {

    }

    @Override
    public void getItems(com.grpcretails.grpcretailprocess.GetItemsRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.GetItemsResponse> responseObserver) {

    }

    @Override
    public void checkout(com.grpcretails.grpcretailprocess.CheckoutRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.CheckoutResponse> responseObserver) {
    }


}
