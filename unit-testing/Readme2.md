To test the Rest endpoints we need `MockMvc` to perform the request and `RequestBuilder` to build the request.

```java
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
            .andReturn();

    assertEquals("Hello World", result.getResponse().getContentAsString());
  }
}
```

Later perform we can rightAway test the result with `resultMatchers`

```java
    MvcResult result = mockMvc
        .perform(request)
        .andExpect(content().string("Hello World"))
        .andReturn();
```
Now we don't need assertEquals as we are already compared the result with resultMatcher. 

We can als check te status of teh result

```java
    MvcResult result = mockMvc
        .perform(request)
        .andExpect(content().string("Hello World"))
        .andExpect(statu().isOk())
        .andReturn();
```

## Asserting the Json Responses

We created an ItemController, Item  and ItemControlleTest to test the Json repose.

Everything is literally same except the endPoint 

```java
// used `textblocks` (triple inverted commas).
.andExpect(content().json("""
                    {"id":1,"name":"Ball","price":10,"quantity":100}
                    """))
            .andReturn();
```

## @MockBean vs @InjectMock

When testing `controllers`, you typically use `@WebMvcTest` to set up the web application context and `@MockBean` to mock any dependencies.

```java
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;  // Mocking a service dependency

    @Test
    public void shouldReturnListOfItems() throws Exception {
        when(itemService.getItems()).thenReturn("Mocked list of items");

        mockMvc.perform(get("/items"))
               .andExpect(status().isOk())
               .andExpect(content().string("Mocked list of items"));
    }
}
```

When testing service classes, you use @ExtendWith(MockitoExtension.class) to enable Mockito support and @InjectMocks to inject mocks into the service class being tested. You also use @Mock to create the mocks for the dependencies of the service.

```java
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private DependencyRepository dependencyRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void shouldReturnItems() {
        when(dependencyRepository.findAll()).thenReturn(List.of(new Item("Mocked Item")));
        
        List<Item> items = itemService.getItems();
        
        assertEquals(1, items.size());
        assertEquals("Mocked Item", items.get(0).getName());
    }
}
```

## Mock vs InjectMock

`@Mock` To create and inject mock instances of `dependencies`.

`@InjectMocks` is used on the field that represents the class you want to test.

