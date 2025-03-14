package org.alishev.course.repositories;

import org.alishev.course.model.Item;
import org.alishev.course.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemName(String itemName);
    List<Item> findByOwner(Person owner);
}
