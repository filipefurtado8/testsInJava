package junit.tests.testsInJava.business;

import junit.tests.testsInJava.model.Item;
import junit.tests.testsInJava.repository.ItemRepository;
import junit.tests.testsInJava.service.ItemBusinessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//All that is related to business logic, we need to put in the business package.
//this way we can separate the webmvctests from the business logic ones
@ExtendWith(MockitoExtension.class)
public class ItemServiceBusinessTest {

    //when we're testing the business model of the methods, aka, the business logic,
    // we don't need the @SpringBootTest annotation

    @InjectMocks
    private ItemBusinessService itemBusinessService;
    @Mock
    private ItemRepository itemRepository;


    @Test
    public void testingRetrieveAllItems_businessLogic() {

        when(itemRepository.findAll()).thenReturn(List.of(new Item(2, "Item2", 10, 10),
                new Item(3, "Item3", 20, 20)));

        List<Item> items = itemBusinessService.retrieveAllItems();
        System.out.println(items);

        assertEquals(100, items.get(0).getValue());
        assertEquals(400, items.get(1).getValue());
    }

}
