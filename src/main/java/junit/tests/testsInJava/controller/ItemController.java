package junit.tests.testsInJava.controller;

import junit.tests.testsInJava.model.Item;
import junit.tests.testsInJava.service.ItemBusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ItemController {


    private final ItemBusinessService businessService;

    public ItemController(ItemBusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {
        return businessService.retrieveHardcodedItem();
    }

    @GetMapping("/all-items-from-database")
    public List<Item> retrieveAllItems() {
        return businessService.retrieveAllItems();
    }
}
