package com.example.gps.service;

import com.example.gps.cache.UserCache;
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
    private UserCache userCache;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        UserEntity savedUser = userRepo.save(user);
        userCache.addUserToCache(savedUser.getId(), savedUser); // Добавляем пользователя в кэш
        return savedUser;
    }

    public User getOne(Long id) throws UserNotFoundException {
        if (userCache.containsUser(id)) {
            UserEntity cachedUser = userCache.getUserFromCache(id);
            return User.toModel(cachedUser);
        }
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userCache.addUserToCache(id, user); // Добавляем пользователя в кэш
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

