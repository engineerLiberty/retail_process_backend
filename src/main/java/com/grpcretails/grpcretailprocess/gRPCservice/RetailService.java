package com.grpcretails.grpcretailprocess.gRPCservice;

import com.grpcretails.grpcretailprocess.RetailServiceGrpc;
import com.grpcretails.grpcretailprocess.Service.CartService;
import com.grpcretails.grpcretailprocess.Service.InventoryService;
import com.grpcretails.grpcretailprocess.Service.OrderService;
import com.grpcretails.grpcretailprocess.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetailService extends RetailServiceGrpc.RetailServiceImplBase {
    private final CartService cartService;
    private final OrderService orderService;
    private final InventoryService inventoryService;


    public void register(com.grpcretails.grpcretailprocess.RegisterRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.RegisterResponse> responseObserver) {

    }

    public void login(com.grpcretails.grpcretailprocess.LoginRequest request,
                      io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.LoginResponse> responseObserver) {

    }

    public void getItems(com.grpcretails.grpcretailprocess.GetItemsRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.GetItemsResponse> responseObserver) {

    }

    public void addToCart(com.grpcretails.grpcretailprocess.AddToCartRequest request,
                          io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.AddToCartResponse> responseObserver) {
        Item item = new Item();
        item.setQuantity(request.getQuantity());
        item.setId(request.getItemId());
        item.setName(request.getName());
        cartService.addItemToCart(request.getName(),item);

    }

    public void removeFromCart(com.grpcretails.grpcretailprocess.RemoveFromCartRequest request,
                               io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.RemoveFromCartResponse> responseObserver) {
    }

    public void modifyCart(com.grpcretails.grpcretailprocess.ModifyCartRequest request,
                           io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.ModifyCartResponse> responseObserver) {
    }

    public void getCart(com.grpcretails.grpcretailprocess.GetCartRequest request,
                        io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.GetCartResponse> responseObserver) {
        cartService.getCart(request.getUsername());
    }

    public void checkout(com.grpcretails.grpcretailprocess.CheckoutRequest request,
                         io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.CheckoutResponse> responseObserver) {
    }

    public void reorder(com.grpcretails.grpcretailprocess.ReorderRequest request,
                        io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.ReorderResponse> responseObserver) {
    }

    public void cancelOrder(com.grpcretails.grpcretailprocess.CancelOrderRequest request,
                            io.grpc.stub.StreamObserver<com.grpcretails.grpcretailprocess.CancelOrderResponse> responseObserver) {
    }
}
