package pl.sda.meetup.meetup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.model.Comment;
import pl.sda.meetup.meetup.model.Event;
import pl.sda.meetup.meetup.model.Role;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.CommentRepository;
import pl.sda.meetup.meetup.repository.EventRepository;
import pl.sda.meetup.meetup.repository.RoleRepository;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
//TODO FlyWay
public class DataInitialization  {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;
    private final CommentRepository commentRepository;

    public DataInitialization(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, EventRepository eventRepository, CommentRepository commentRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventRepository = eventRepository;
        this.commentRepository = commentRepository;
    }

//    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Role adminRole = Role.builder()
                .id(1L)
                .roleName("ADMIN")
                .build();
        Role userRole = Role.builder()
                .id(2L)
                .roleName("User")
                .build();

        Set<Role> roles = new HashSet<>();
        User admin = User.builder()
                .id(1L)
                .username("ADMIN")
                .email("admin@sda.pl")
                .passwordHash(passwordEncoder.encode("password"))
                .roles(roles)
                .build();
        roles.add(adminRole);
        roles.add(userRole);

        Event testEvent = Event.builder()
                .id(1L)
                .title("Event Testowy")
                .start(LocalDate.of(2019, 8, 10))
                .end(LocalDate.of(2019, 8, 12))
                .user(admin)
                .description("sdajkadsbjkdsbsadjkdbdsadsabadsk")
                .build();

        Event testEvent2 = Event.builder()
                .id(2L)
                .title("Event Testowy2")
                .start(LocalDate.of(2019, 10, 10))
                .end(LocalDate.of(2019, 10, 12))
                .user(admin)
                .description("Jest sobie event jakistam")
                .build();

        Event testEvent3 = Event.builder()
                .id(3L)
                .title("Event Testowy3")
                .start(LocalDate.of(2019, 12, 10))
                .end(LocalDate.of(2019, 12, 12))
                .user(admin)
                .description("trololololololololololololo")
                .build();

        Comment testComment = Comment.builder()
                .id(1L)
                .user(admin)
                .description("Bardzo fajny event, polecam dzieciom")
                .event(testEvent2)
                .dateOfCreation(LocalDateTime.now())
                .build();
        Comment testComment2 = Comment.builder()
                .id(2L)
                .user(admin)
                .description("no chyba nie")
                .event(testEvent2)
                .dateOfCreation(LocalDateTime.of(2019, 8, 7, 10, 10))
                .build();
        Comment testComment3 = Comment.builder()
                .id(3L)
                .user(admin)
                .description("lubie pączki")
                .event(testEvent2)
                .dateOfCreation(LocalDateTime.of(2019, 7, 19, 19, 8))
                .build();


        Optional<Role> adminRoleOptional = roleRepository.findByRoleName("ADMIN");
        Optional<Role> userRoleOptional = roleRepository.findByRoleName("User");
        Optional<User> adminUserOptional = userRepository.findUserByEmail("admin@sda.pl");
        Optional<Event> byId = eventRepository.findById(1L);
        Optional<Event> byId2 = eventRepository.findById(2L);
        Optional<Event> byId3 = eventRepository.findById(3L);
        Optional<Comment> commentById = commentRepository.findById(1L);
        Optional<Comment> commentById2 = commentRepository.findById(2L);
        Optional<Comment> commentById3 = commentRepository.findById(3L);


        if (!adminRoleOptional.isPresent()) {
            roleRepository.save(adminRole);
        }

        if (!userRoleOptional.isPresent()) {
            roleRepository.save(userRole);
        }

        if (!adminUserOptional.isPresent()) {
            userRepository.save(admin);
        }

        if (!byId.isPresent()) {
            eventRepository.save(testEvent);
        }

        if (!byId2.isPresent()) {
            eventRepository.save(testEvent2);
        }

        if (!byId3.isPresent()) {
            eventRepository.save(testEvent3);
        }

        if (!commentById.isPresent()) {
            commentRepository.save(testComment);
        }

        if (!commentById2.isPresent()) {
            commentRepository.save(testComment2);
        }

        if (!commentById3.isPresent()) {
            commentRepository.save(testComment3);
        }


        log.info("loading init data");
    }


}
