package com.maks.todo.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends AbstractEntity {

    private String content;

    private Boolean isDone;

    private LocalDateTime deadline;
}
