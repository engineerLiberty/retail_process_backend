package com.grpcretails.grpcretailprocess.Service.serviceImpl;

import com.grpcretails.grpcretailprocess.Service.InventoryService;
import com.grpcretails.grpcretailprocess.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InventoryServiceImpl implements InventoryService {


    List<Item> items = new ArrayList<>();

    Map<Long, Integer> itemStocks = new HashMap<>();

    @Override
    public List<Item> getItems() {

        return items;
    }

    @Override
    public Item getItem(Long itemId) {
        List<Item> items = new ArrayList<>();
        for (Item item : items) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean updateItemStock(Long itemId, int quantity) {

        if (itemStocks.containsKey(itemId)) {
            int currentStock = itemStocks.get(itemId);
            int newStock = currentStock + quantity;
            if (newStock < 0) {
                return false;
            } else {
                itemStocks.put(itemId, newStock);
                return true;
            }
        } else {
            itemStocks.put(itemId, quantity);
            return true;
        }
    }

    public void addItem(Item item, int stockQuantity) {

        List<Item> items = new ArrayList<>();

        items.add(item);

        itemStocks.put(item.getId(), stockQuantity);
    }
}
