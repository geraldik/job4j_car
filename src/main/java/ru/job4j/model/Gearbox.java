package ru.job4j.model;

import lombok.*;

import javax.persistence.*;

@Builder(builderMethodName = "of")
@Entity
@Setter
@Getter
@Table(name = "gearbox")
@NoArgsConstructor
@AllArgsConstructor
public class Gearbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "is_automatic")
    private Boolean automatic;

    @Column(name = "speed_number", nullable = false)
    private int speedNumber;

}