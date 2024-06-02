package com.unit_testing.junit.business;

import com.unit_testing.junit.data.ItemRepository;
import com.unit_testing.junit.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemBusinessService {

  @Autowired
  private ItemRepository repository;

  public Item getItem() {
    return new Item(1, "Ball", 10, 100);
  }

  public List<Item> getAllItems() {
    List<Item> items = repository.findAll();

    for (Item item : items)
      item.setValue(item.getPrice() * item.getQuantity());
    return repository.findAll();
  }
}
