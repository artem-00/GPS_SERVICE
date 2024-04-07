package com.example.gps.service;

import com.example.gps.entity.UserEntity;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.model.User;
import com.example.gps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return User.toModel(user);
    }

    public Iterable<User> getAll() {
        Iterable<UserEntity> users = userRepo.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userEntity -> userList.add(User.toModel(userEntity)));
        return userList;
    }

    public void updateUser(Long id, UserEntity updatedUser) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        user.setLogin(updatedUser.getLogin());
        user.setPassword(updatedUser.getPassword());
        // Update other fields as needed
        userRepo.save(user);
    }

    public Long deleteUserById(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}

