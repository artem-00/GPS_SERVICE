package com.example.gps.controller;

import com.example.gps.entity.UserEntity;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.repository.UserRepository;
import com.example.gps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registration(user);
            return ResponseEntity.ok("User saved");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR)");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR)");
        }
    }

    @GetMapping
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR)");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        try {
            userService.updateUser(id, user);
            return ResponseEntity.ok("User updated");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR)");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("ERROR)");
        }
    }
}
