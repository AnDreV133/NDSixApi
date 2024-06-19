package com.example.NDSixApi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "email",
            nullable = false
    )
    private String login;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Characterlist> characterlists;
}
