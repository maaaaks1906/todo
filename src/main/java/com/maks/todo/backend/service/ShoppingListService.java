package com.maks.todo.backend.service;

import com.maks.todo.backend.entity.ShoppingObject;
import com.maks.todo.backend.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingObject save(ShoppingObject shoppingObject) {
        return shoppingListRepository.save(shoppingObject);
    }

    public List<ShoppingObject> findAll() {
        return shoppingListRepository.findAll();
    }

    public void delete(ShoppingObject shoppingObject) {
        shoppingListRepository.delete(shoppingObject);
    }

}
