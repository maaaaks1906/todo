package com.maks.todo.backend.repository;

import com.maks.todo.backend.entity.ShoppingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingObject, Long> {
}
