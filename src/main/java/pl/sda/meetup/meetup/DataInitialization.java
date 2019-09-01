package pl.sda.meetup.meetup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.meetup.meetup.model.Role;
import pl.sda.meetup.meetup.model.User;
import pl.sda.meetup.meetup.repository.RoleRepository;
import pl.sda.meetup.meetup.repository.UserRepository;

import java.util.*;

@Slf4j
@Component
//TODO FlyWay
public class DataInitialization implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitialization(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
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
        roles.add(adminRole);
        roles.add(userRole);

        User admin = User.builder()
                .id(1L)
                .username("ADMIN")
                .email("admin@sda.pl")
                .passwordHash(passwordEncoder.encode("password"))
                .roles(roles)
                .build();

        Optional<Role> adminRoleOptional = roleRepository.findByRoleName("ADMIN");
        Optional<Role> userRoleOptional = roleRepository.findByRoleName("User");

        if (!adminRoleOptional.isPresent()){
            roleRepository.save(adminRole);
        }

        if(!userRoleOptional.isPresent()){
            roleRepository.save(userRole);
        }

        Optional<User> adminUserOptional = userRepository.findUserByEmail("admin@sda.pl");
        if (!adminUserOptional.isPresent()){
            userRepository.save(admin);
        }
        log.info("loading init data");
    }


}
