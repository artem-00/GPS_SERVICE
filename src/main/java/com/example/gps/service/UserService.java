package com.example.gps.service;

import com.example.gps.entity.User;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.DTO.UserDTO;
import com.example.gps.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDTO.toModel(user);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }


    public Iterable<UserDTO> getAll() throws UserNotFoundException {
        Iterable<User> users = userRepo.findAll();

        // Проверяем, есть ли пользователи
        if (users == null || !users.iterator().hasNext()) {
            throw new UserNotFoundException("No users found");
        }

        // Создаем список UserDTO из пользователей
        List<UserDTO> userList = StreamSupport.stream(users.spliterator(), false)
                .map(UserDTO::toModel)
                .collect(Collectors.toList());

        return userList;
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) throws UserNotFoundException {
        if (updatedUser == null) {
            throw new IllegalArgumentException("Updated user cannot be null");
        }

        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setLogin(updatedUser.getLogin());
        user.setPassword(updatedUser.getPassword());

        return userRepo.save(user);
    }

    public Long deleteUserById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            userRepo.deleteById(id);
            return id;
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }
}

