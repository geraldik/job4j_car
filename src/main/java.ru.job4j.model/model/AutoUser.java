package model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "auto_user")
public class AutoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "login", nullable = false, length = 30)
    private String login;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

}