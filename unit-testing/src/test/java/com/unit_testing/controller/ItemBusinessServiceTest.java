package com.unit_testing.controller;

import com.unit_testing.junit.business.ItemBusinessService;
import com.unit_testing.junit.controller.ItemController;
import com.unit_testing.junit.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ItemController.class)
public class ItemBusinessServiceTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ItemBusinessService itemBusinessService;

  private RequestBuilder getRequestOfAnEndPoint(String endPoint){
    return MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON);
  }

  @Test
  public void testHelloWorldResponse() throws Exception {

    when(itemBusinessService.getItem()).thenReturn(new Item(1, "Ball", 10, 100));

    RequestBuilder request = getRequestOfAnEndPoint("/item");
    MvcResult result = mockMvc
            .perform(request)
            .andExpect(content().json("""
                    {
                    "id":1,"name":"Ball","price":10,"quantity":100
                    }
                    """))
            .andReturn();

//    assertEquals("Hello World", result.getResponse().getContentAsString());
  }

}
