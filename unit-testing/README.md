Don't forget to add the annotation 

```java
@ExtendWith(MockitoExtension.class)
```
while implementing mockito.

```java
@ExtendWith(MockitoExtension.class)
public class TestBusinessMockImpl {

  @InjectMocks
  private BusinessImpl impl;
  @Mock
  private SomeDataService dataService;

  @Test
  public void testCalculateSumUsingDataService() {

    when(dataService.getData()).thenReturn(new int[]{1, 2, 4});
    assertEquals(7, impl.calculateSumUsingSomeDataService());
  }
}
```

`BusinessImpl` is the class where are testing, so it is the dependency, dependencies are annotated with @InjectMock.

The Services or the data we mock should be annotated with `@Mock`.

## Argument matchers

Mockito class extends ArgumentMatchers class.

To return a specific value irrespective of any parameter, we need argument matchers.

```java
 @Test
  public void testArgumentMatchers() {
    when(mockList.get(anyInt())).thenReturn("sai");
    assertEquals(mockList.get(1), "sai");
    assertEquals(mockList.get(2), "sai");
    assertEquals(mockList.get(3), "sai");
    
    // anyInt() is the argument matcher we used in this case. 
  }
```

In the above example the argument matcher is `anyInt()`. 

## Verify method calls

There would be few scenarios where we may not return any value from service classes but call a void methods. In those scenarios we would need to know if that method is called or not.

To do that we have verifyMethod calls.

```java
@Test
  public void testVerificationMethods() {
    String val = (String) mockList.get(0);
    verify(mockList, atLeastOnce()).get(0);
  }
```

Using verify we can identify if a method is called and there is another parameter we can provide to verify method which is verification mode.

There are few verificationModes to test in specific. Few other verification modes are 

```html
    atLeast()
    atLeastOnce()
    atMost()
    never()
    times() etc
```