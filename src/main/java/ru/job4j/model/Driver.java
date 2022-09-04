package ru.job4j.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder(builderMethodName = "of")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "driver_id", nullable = false, updatable = false)})
    private Set<Brand> cars = new HashSet<>();

}