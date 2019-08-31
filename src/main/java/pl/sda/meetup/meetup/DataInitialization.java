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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        User admin = new User();
        Role adminRole = new Role();
        Role userRole = new Role();
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();

        Optional<Role> adminRoleOptional = roleRepository.findByRoleName("admin");
        Optional<Role> userRoleOptional = roleRepository.findByRoleName("user");

        if (!adminRoleOptional.isPresent()){
            adminRole.setRoleName("admin");
            adminRole.setId(1L);
            roles.add(adminRole);
        }

        if (!userRoleOptional.isPresent()){
            userRole.setRoleName("user");
            userRole.setId(2L);
            roles.add(userRole);
        }

        Optional<User> userByEmail = userRepository.findUserByEmail("admin@sda.pl");
        if (!userByEmail.isPresent()){
            admin.setEmail("admin@sda.pl");
            admin.setPasswordHash(passwordEncoder.encode("password1234"));
            admin.setUsername("admin");
            admin.setId(1L);
//            if (adminRoleOptional.isPresent()){
//                admin.addRole(adminRoleOptional.get());
//            }
//            else {
//                admin.addRole(new Role(1L, "admin"));
//            }

            users.add(admin);
        }



        if (!users.isEmpty()){
            userRepository.saveAll(users);
        }

        if (!roles.isEmpty()){
            roleRepository.saveAll(roles);
        }

        log.info("loading init data");
    }


}
