package com.apple.selfone.component;

import com.apple.selfone.entity.Role;
import com.apple.selfone.entity.User;
import com.apple.selfone.repository.RoleRepository;
import com.apple.selfone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

//        if (roleRepository.count() == 0){
//            roleRepository.saveAll(Arrays.asList(
//                    new Role(10, "ROLE_ADMIN"),
//                    new Role(20, "ROLE_MODERATOR"),
//                    new Role(30, "ROLE_USER")
//            ));
//        }
//
//        if (initialMode.equals("always")){
//            userRepository.saveAll(
//                    Arrays.asList(
//                            new User(
//                                    "admin1",
//                                    "ismoil1428@gmail.com",
//                                    passwordEncoder.encode("root123"),
//                                    "Ismoil Yuldoshev",
//                                    "",
//                                    true,
//                                    roleRepository.findAll()
//                            ),
//                            new User(
//                                    "admin2",
//                                    "ilhom1428@gmail.com",
//                                    passwordEncoder.encode("root123"),
//                                    "Ilhom Yuldoshev",
//                                    "",
//                                    true,
//                                    roleRepository.findAll()
//                            ),
//                            new User(
//                                    "admin3",
//                                    "ilhom1428@gmail.com",
//                                    passwordEncoder.encode("root123"),
//                                    "Ilhom Yuldoshev",
//                                    "",
//                                    true,
//                                    Arrays.asList(roleRepository.getOne(10))
//                            )
//                    )
//            );
//        }
    }
}
