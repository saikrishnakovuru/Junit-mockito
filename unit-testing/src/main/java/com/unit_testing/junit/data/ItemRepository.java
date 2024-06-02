package com.unit_testing.junit.data;

import com.unit_testing.junit.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
