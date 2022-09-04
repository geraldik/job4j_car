package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

}