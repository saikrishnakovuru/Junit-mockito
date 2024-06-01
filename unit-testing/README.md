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

## Spying

Spying is nothing but using the actual methods instead of mocking them.

Before looking into the example of spying lets look into mocking things

```java
@Test
  public void mocking() {
    ArrayList mockList = mock(ArrayList.class);
    System.out.println(mockList.get(0)); //null
    System.out.println(mockList.size()); //0
    mockList.add("adf");
    mockList.add("ghj");
    System.out.println(mockList.size()); //0
    when(mockList.size()).thenReturn(5);
    System.out.println(mockList.size()); //5
  }
```

Example clearly shows mocking the arrayList do not affect actual values. Though we explicitly added 2 values into the arrayList, still the size of array is 0 unless we stub it.

Spying is working on actual array list.

```java
//code-1
ArrayList mockList = spy(ArrayList.class);
System.out.println(mockList.get(0)); //NPE as the array is empty

//code-2
ArrayList mockList = spy(ArrayList.class);
mockList.add("adf");
System.out.println(mockList.get(0)); //adf
System.out.println(mockList.size()); //1
  
```

# Limitations of Mockito

We cannot mock static and constructors, to do that we need additional framework called power mockito.