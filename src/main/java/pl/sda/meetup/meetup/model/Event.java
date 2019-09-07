package pl.sda.meetup.meetup.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    private LocalDate start;

    private LocalDate end;

    @ManyToMany
    @JoinTable(name = "event_registered_users", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "registered_users_id"))
    private Set<User> registeredUsers = new HashSet<>();


    public boolean addUserToEvent(User user){
        return registeredUsers.add(user);
    }

    public boolean removeUserFromEvent(User user){
        return registeredUsers.remove(user);
    }
}