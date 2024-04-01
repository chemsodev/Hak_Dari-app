package com.example.demo.controller;
import com.example.demo.models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63343")
public class ApiControlles {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/")
    public String getPage() {
        return "welcome";
    }
    @GetMapping(value = "/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "saved...";
    }
    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setAge(user.getAge());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setOccupation(user.getOccupation());
        userRepo.save(updatedUser);
        return "Updated...";
    }
    @DeleteMapping(value = "delete/{id}")
    public String deletUser(@PathVariable long id){
        User deleteduser=userRepo.findById(id).get();
        userRepo.delete(deleteduser);
        return "deleted...";
    }
}