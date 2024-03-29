package pl.sda.meetup.meetup.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    @ToString.Exclude
    private String passwordHash;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Event> events = new HashSet<>();

    @ManyToMany
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role) {
        roles.add(role);
    }


}

