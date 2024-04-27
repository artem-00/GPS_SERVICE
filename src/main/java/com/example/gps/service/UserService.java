package com.example.gps.service;

import com.example.gps.entity.User;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.DTO.UserDTO;
import com.example.gps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> registration(List<User> users) throws UserAlreadyExistsException {
        return users.stream()
                .map(user -> {
                    try {
                        return registration(user);
                    } catch (UserAlreadyExistsException e) {
                        // Обработка исключения, если пользователь уже существует
                        return null; // или какой-то другой способ обработки
                    }
                })
                .filter(Objects::nonNull) // Отфильтровываем пользователей, которые были успешно зарегистрированы
                .collect(Collectors.toList()); // Собираем результаты в список
    }


    public User registration(User user) throws UserAlreadyExistsException {
        if (userRepo.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepo.save(user);
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
        userRepo.save(user);
    }

    public Long deleteUserById(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}

