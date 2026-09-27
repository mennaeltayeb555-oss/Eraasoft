package org.example.task7.service;

import org.example.task7.model.Item;

import java.util.List;

public interface ItemService {

    Item addItem(Item item);

    Item updateItem(Item item);

    List<Item> getAllItems();

    Item getItemById(Long id);

    void deleteItemById(Long id);
}
