package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car_body")
public class CarBody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "color", nullable = false, length = 64)
    private String color;

    @Column(name = "body_type", nullable = false, length = 20)
    private String bodyType;

    @Column(name = "doors_number", nullable = false)
    private int doorsNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", insertable = false, updatable = false)
    private CarBodyType carBodyType;
}