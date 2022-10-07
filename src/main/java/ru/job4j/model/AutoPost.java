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
@Table(name = "auto_post")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AutoPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "text", nullable = false)
    @EqualsAndHashCode.Include
    private String text;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "sold")
    private  boolean sold;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne()
    @JoinColumn(name = "car_id")
    private Car car;
}