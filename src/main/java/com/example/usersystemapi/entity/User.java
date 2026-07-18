package com.example.usersystemapi.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false,length=200)
    private String name;

    @Column(nullable=false,unique = true, length=20)
    private String phoneNumber;

    @Column(nullable=false)
    private Integer age;

    @Column(nullable=false)
    private String department;

}
