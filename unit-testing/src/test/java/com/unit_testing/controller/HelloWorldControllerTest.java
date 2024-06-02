package com.unit_testing.controller;

import com.unit_testing.junit.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private RequestBuilder getRequestOfAnEndPoint(String endPoint) {
    return MockMvcRequestBuilders.get(endPoint).accept(MediaType.APPLICATION_JSON);
  }

  @Test
  public void testHelloWorldResponse() throws Exception {
    RequestBuilder request = getRequestOfAnEndPoint("/hello-world");
    MvcResult result = mockMvc
            .perform(request)
            .andExpect(content().string("Hello World"))
            .andReturn();

//    assertEquals("Hello World", result.getResponse().getContentAsString());
  }
}
