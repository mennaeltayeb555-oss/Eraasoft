package org.example.task7.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Add Item
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    // Update Item
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    // Show All Items
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Show Item By ID
    public Item getItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }

    // Delete Item
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
