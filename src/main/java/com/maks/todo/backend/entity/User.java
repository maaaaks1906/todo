package com.maks.todo.backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {

    @NotBlank(message = "First name cannot be empty")
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
}
