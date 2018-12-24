package com.rohit.mml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.rohit.mml.model.Temp;
import com.rohit.mml.model.User;
import com.rohit.mml.repository.RoleRepository;
import com.rohit.mml.repository.UserRepository;
import com.rohit.mml.util.FileUtils;

@SpringBootApplication
public class MmlApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(MmlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // this.saveUser(10);
        // if (userRepository.count() <= 0) {
        // this.saveUser(3);
        // }
        // if (roleRepository.count() <= 0) {
        // Role rAdmin = new Role(RoleName.ROLE_ADMIN);
        // Role rUser = new Role(RoleName.ROLE_USER);
        // Role rPm = new Role(RoleName.ROLE_MOD);
        // roleRepository.save(rAdmin);
        // roleRepository.save(rPm);
        // roleRepository.save(rUser);
        //
        // }

        String s = new FileUtils().getFileContentFromResources("sample.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Temp> temp = mapper.readValue(s, ArrayList.class);
        System.out.println(temp);
    }

    public void saveUser(int n) {
        Faker faker = new Faker();
        for (int i = 0; i < n; i++) {
            String name = faker.name().firstName().toLowerCase();
            User u = new User(name, "password", new HashSet<>());
            userRepository.save(u);
        }
    }

}
