package junit.tests.testsInJava.controller;


import junit.tests.testsInJava.model.Item;
import junit.tests.testsInJava.service.ItemBusinessService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    //It is necessary to create this mockbean otherwise the WebMvcTest annotation will search for the itemBusinessService
    //inside the ItemController.class and since it is layered, it will throw an error.
    //therefore is necessary to create a Mock for the service bean -> MockBean of the ItemBusinessService
    //this way it will ignore the content inside it.
    //this is good because we can test its methods even with the Database down.
    @MockBean
    ItemBusinessService businessService;
    @Autowired
    private MockMvc mockMvc;

    public ItemControllerTest() {
    }


    @Test
    public void dummyItem_basic_assert_true() throws Exception {
        //call GET "/dummy-item
        //we use RequestBuilder with MockMvcRequestBuilders to execute our requests
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        //the mockMvc.perform is gonna execute the stream above, expect a status 200 and return a Json with the Item
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //verify Item json
        //getResponse is a method from MvcResult that gives us the result from the previous variable/method
        //we then use the getContentAsString method to obtain the content of the response in a String format
        //in addition JSONAssert.assertEquals expects (expectedResponse, actualResponse, false/true)
        //in this case we chose true, therefore both must be exact (variables and its values)
        JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}", result.getResponse().getContentAsString(), true);
    }

    @Test
    public void dummyItem_basic_assert_false() throws Exception {
        //call GET "/dummy-item
        //we use RequestBuilder with MockMvcRequestBuilders to execute our requests
        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        //the mockMvc.perform is gonna execute the stream above, expect a status 200 and return a Json with the Item
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //verify Item json
        //getResponse is a method from MvcResult that gives us the result from the previous variable/method
        //we then use the getContentAsString method to obtain the content of the response in a String format
        //in addition JSONAssert.assertEquals expects (expectedResponse, actualResponse, false/true)
        //in this case we chose false, therefore both don't need to be exact, it only needs to be Json format -> {}
        //but in this case it compares the values of the items -> the json can be empty but if it has for example price:10
        //it will compare the price 10. so if the expected has 11 and actual has 10, the test will fail
        JSONAssert.assertEquals("{\"id\":1,\"name\":\"Ball\",\"price\":10}", result.getResponse().getContentAsString(), false);
    }

    @Test
    public void itemFromBusinessService() throws Exception {

        //since the request communicates with the ItemBusinessService layer and the
        //businessService is mocked we need to implement this when/then method from Mockito

        when(businessService.retrieveHardcodedItem()).thenReturn(new Item(2, "Item2", 10, 10));

        //call GET "/dummy-item
        //we use RequestBuilder with MockMvcRequestBuilders to execute our requests
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        //the mockMvc.perform is gonna execute the stream above, expect a status 200 and return a Json with the Item
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //verify Item json
        //getResponse is a method from MvcResult that gives us the result from the previous variable/method
        //we then use the getContentAsString method to obtain the content of the response in a String format
        //in addition JSONAssert.assertEquals expects (expectedResponse, actualResponse, false/true)
        //in this case we chose true, therefore both must be exact (variables and its values)
        JSONAssert.assertEquals("{\"id\":2,\"name\":\"Item2\",\"price\":10,\"quantity\":10}", result.getResponse().getContentAsString(), true);
    }

}
