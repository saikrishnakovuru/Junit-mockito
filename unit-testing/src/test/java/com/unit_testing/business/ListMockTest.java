package com.unit_testing.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListMockTest {

  @Mock
  private List<Object> mockList;

  @Test
  public void test() {
    when(mockList.size()).thenReturn(5);
    assertEquals(5, mockList.size());
  }

  @Test
  public void testArgumentMatchers() {
    when(mockList.get(anyInt())).thenReturn("sai");
    assertEquals(mockList.get(1), "sai");
    assertEquals(mockList.get(2), "sai");
    assertEquals(mockList.get(3), "sai");
  }

  @Test
  public void testVerificationMethods() {
    String val = (String) mockList.get(0);
    verify(mockList, atLeastOnce()).get(0);
  }
}
