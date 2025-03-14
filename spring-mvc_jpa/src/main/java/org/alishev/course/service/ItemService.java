package org.alishev.course.service;

import org.alishev.course.model.Item;
import org.alishev.course.model.Person;
import org.alishev.course.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }
    public List<Item> findByItemName(String itemName) {
        return itemsRepository.findByItemName(itemName);
    }
    public List<Item> findByOwner(Person owner) {
        return itemsRepository.findByOwner(owner);
    }
}
