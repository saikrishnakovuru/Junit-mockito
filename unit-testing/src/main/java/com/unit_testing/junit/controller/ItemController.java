package com.unit_testing.junit.controller;

import com.unit_testing.junit.business.ItemBusinessService;
import com.unit_testing.junit.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

  @Autowired
  private ItemBusinessService itemBusinessService;

  @GetMapping("/dummy-item")
  public Item dmmyItem() {
    return new Item(1, "Ball", 10, 100);
  }

  @GetMapping("/item")
  public Item itemFromBusinessService() {
    return itemBusinessService.getItem();
  }

  @GetMapping("/item-db")
  public List<Item> itemFromDb() {
    return itemBusinessService.getAllItems();
  }
}
