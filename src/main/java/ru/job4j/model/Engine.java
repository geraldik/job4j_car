package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "number", length = 14)
    private String number;

    @Column(name = "volume", nullable = false, precision = 2)
    private double volume;

    @Column(name = "horse_power", nullable = false)
    private int horsePower;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

}