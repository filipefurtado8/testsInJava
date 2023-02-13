package junit.tests.testsInJava.service;

import junit.tests.testsInJava.model.Item;
import junit.tests.testsInJava.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusinessService {

    private final ItemRepository itemRepository;

    public ItemBusinessService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item retrieveHardcodedItem() {
        return new Item(1, "Ball", 10, 100);
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = itemRepository.findAll();
        items.forEach(item -> item.setValue(item.getPrice() * item.getQuantity()));
        return items;

    }

}
