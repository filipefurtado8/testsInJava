package junit.tests.testsInJava.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void helloWorld_basic() throws Exception {
        //call GET "/hello-world
        //we use RequestBuilder with MockMvcRequestBuilders to execute our requests
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);

        //the mockMvc.perform is gonna execute the stream above, expect a status 200 and return a Json with "Hello World"
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        //verify "Hello World"
        //getResponse is a method from MvcResult that gives us the result from the previous variable/method
        //we then use the getContentAsString method to obtain the content of the response in a String format
        assertEquals("Hello World", result.getResponse().getContentAsString());
    }


}
