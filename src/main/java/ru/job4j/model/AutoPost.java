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
public class AutoPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auto_user_id")
    private AutoPost post;

}