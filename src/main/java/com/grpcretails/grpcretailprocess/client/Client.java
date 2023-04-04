package com.grpcretails.grpcretailprocess.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {

       ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8080)
                .usePlaintext()
                .build();
    }
}
