package com.example.gps.service;

import com.example.gps.DTO.UserDTO;
import com.example.gps.entity.User;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepo;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registration_singleUserSuccess() throws UserAlreadyExistsException {
        User user = new User();
        user.setLogin("testUser");

        when(userRepo.findByLogin(user.getLogin())).thenReturn(null);
        when(userRepo.save(user)).thenReturn(user);

        User registeredUser = userService.registration(user);

        assertEquals(user, registeredUser);
        verify(userRepo, times(1)).findByLogin(user.getLogin());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testRegistration_UserNotExists() throws UserAlreadyExistsException {
        User user = new User();
        user.setLogin("testUser");

        when(userRepo.findByLogin(user.getLogin())).thenReturn(null);
        when(userRepo.save(user)).thenReturn(user);

        User registeredUser = userService.registration(user);

        assertEquals(user, registeredUser);
        verify(userRepo, times(1)).findByLogin(user.getLogin());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testRegistration_UserAlreadyExists() {
        User user = new User();
        user.setLogin("existingUser");

        when(userRepo.findByLogin(user.getLogin())).thenReturn(user);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registration(user);
        });

        verify(userRepo, times(1)).findByLogin(user.getLogin());
        verify(userRepo, never()).save(user);
    }

    @Test
    void testGetOne_UserExists() throws UserNotFoundException {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setLogin("testUser");

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getOne(userId);

        assertEquals(userId, userDTO.getId());
        assertEquals(user.getLogin(), userDTO.getLogin());
        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void testGetOne_UserNotFound() {
        Long userId = 1L;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.getOne(userId);
        });

        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void testGetAll_UserNotFoundException() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList()); // Возвращаем пустой список пользователей

        assertThrows(UserNotFoundException.class, () -> {
            userService.getAll();
        });
    }

    @Test
    void testUpdateUser_UserNotFound() {
        Long userId = 1L;
        String newLogin = "newLogin";
        String newPassword = "newPassword";

        User updatedUser = new User();
        updatedUser.setLogin(newLogin);
        updatedUser.setPassword(newPassword);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, updatedUser));
    }

    @Test
    void testDeleteUserById_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUserById(userId);
        });
    }

}