package com.maks.todo.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "shopping_object")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingObject {

    @Id // TODO: 12/08/2020 dlaczego musialem dodac identyfikator ( nie vaadin tylko persistance)
    private String content;

    private String quantity;

    private Boolean isDone;

}
