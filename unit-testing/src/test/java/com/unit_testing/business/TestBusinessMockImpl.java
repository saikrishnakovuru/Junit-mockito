package com.unit_testing.business;

import com.unit_testing.data.SomeDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
