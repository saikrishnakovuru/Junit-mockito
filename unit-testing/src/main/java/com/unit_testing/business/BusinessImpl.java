package com.unit_testing.business;

import com.unit_testing.data.SomeDataService;

public class BusinessImpl {

  private SomeDataService someDataService;

  public BusinessImpl(SomeDataService someDataService) {
    this.someDataService = someDataService;
  }

  public int calculateSumUsingSomeDataService() {
    int sum = 0;

    for (int val : someDataService.getData()) {
      sum += val;
    }
    return sum;
  }

}
