package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarBodyType {
    SEDAN("Седан"),
    COUPE("Купе"),
    HATCHBACK("Хэтчбек"),
    CROSSOVER("Кроссовер"),
    MINIVAN("Минивэн");

    private final String name;

}
