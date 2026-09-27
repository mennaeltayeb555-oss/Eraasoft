package org.example.task7.serviceimp;

import org.example.task7.model.Item;
import org.example.task7.repository.ItemRepository;
import org.example.task7.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Add Item
    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    // Update Item
    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    // Show All Items
    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Show Item By ID
    @Override
    public Item getItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElse(null);
    }

    // Delete Item
    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
