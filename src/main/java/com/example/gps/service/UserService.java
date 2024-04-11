package com.example.gps.service;

import com.example.gps.cache.UserCache;
import com.example.gps.entity.User;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.DTO.UserDTO;
import com.example.gps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    private UserCache userCache;

    public User registration(User user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User savedUser = userRepo.save(user);
        //userCache.addUserToCache(savedUser.getId(), savedUser); // Добавляем пользователя в кэш
        return savedUser;
    }

    public UserDTO getOne(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        return UserDTO.toModel(user);
    }


    public Iterable<UserDTO> getAll() {
        Iterable<User> users = userRepo.findAll();
        List<UserDTO> userList = new ArrayList<>();
        users.forEach(userEntity -> userList.add(UserDTO.toModel(userEntity)));
        return userList;
    }

    public void updateUser(Long id, User updatedUser) throws UserNotFoundException {
        User user = userRepo.findById(id).orElse(null);
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

