package com.example.NDSixApi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "characterlists")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@Builder
public class Characterlist {
    @Id
    @GeneratedValue
    private Long id;
    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "json"
    )
    private String json;

    @ManyToOne(fetch  =  FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;
}
