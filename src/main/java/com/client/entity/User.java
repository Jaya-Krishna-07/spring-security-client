package com.client.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(length = 60)
    private String password;
    private String email;
    private String role;

    private boolean enabled = false;
}
