package com.grpcretails.grpcretailprocess.Service;

import com.grpcretails.grpcretailprocess.model.Item;

import java.util.List;

public interface InventoryService {

    List<Item> getItems();

    Item getItem(Long itemId);

    boolean updateItemStock(Long itemId, int quantity);

    public void addItem(Item item, int stockQuantity);
}
