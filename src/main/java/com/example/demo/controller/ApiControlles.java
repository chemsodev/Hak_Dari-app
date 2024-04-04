package com.example.demo.controller;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:8080","https://chemsodev.github.io"})
public class ApiControlles {

    @Autowired
    private UserRepo userRepo;
    @GetMapping("/")
    public String getIndex() {
        return loadIndexHtml();
    }
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        String firstName = user.getFirstName();
      List<User>  existingUsers=  userRepo.findByFirstName(firstName);
        if (!existingUsers.isEmpty()) {
            return "User with the same first name already exists. Not saved.";
        }
        userRepo.save(user);
        return "User saved.";
    }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setRole(user.getRole());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setUsername(user.getUsername());
        userRepo.save(updatedUser);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deletedUser = userRepo.findById(id).get();
        userRepo.delete(deletedUser);
        return "deleted...";
    }

    private String loadIndexHtml() {
        try {
            Resource resource = new ClassPathResource("static/index.html");
            byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading index.html";
        }
    }
}