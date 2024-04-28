package com.example.gps.controller;

import com.example.gps.entity.User;
import com.example.gps.exception.EndpointActionLogger;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.DTO.UserDTO;
import com.example.gps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> registration(@RequestBody User user) throws UserAlreadyExistsException {
        userService.registration(user);
        return ResponseEntity.ok("User saved");
    }

    @PostMapping("bulk/add")
    public ResponseEntity<String> registrationManyUsers(@RequestBody List<User> users) throws UserAlreadyExistsException {
        userService.registration(users);
        return ResponseEntity.ok("Users saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {
        UserDTO user = userService.getOne(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() throws UserNotFoundException {
        List<UserDTO> users = (List<UserDTO>) userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) throws UserNotFoundException {
        userService.updateUser(id, user);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted");
    }
}
