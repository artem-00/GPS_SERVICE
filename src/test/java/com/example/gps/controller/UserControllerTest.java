package com.example.gps.controller;

import com.example.gps.entity.User;
import com.example.gps.exception.UserAlreadyExistsException;
import com.example.gps.exception.UserNotFoundException;
import com.example.gps.DTO.UserDTO;
import com.example.gps.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserById() throws UserNotFoundException {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        when(userService.getOne(userId)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
        verify(userService, times(1)).getOne(userId);
    }

    @Test
    void getAllUsers() throws UserNotFoundException {
        List<UserDTO> userDTOList = Collections.singletonList(new UserDTO());
        when(userService.getAll()).thenReturn(userDTOList);

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTOList, response.getBody());
        verify(userService, times(1)).getAll();
    }

    @Test
    void registration_Success() throws UserAlreadyExistsException {
        User user = new User();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("User saved");

        ResponseEntity<String> response = userController.registration(user);

        assertEquals(expectedResponse, response);
        verify(userService, times(1)).registration(user);
    }

    @Test
    void registrationManyUsers_Success() throws UserAlreadyExistsException {
        List<User> users = new ArrayList<>();
        users.add(new User(/* данные первого пользователя */));
        users.add(new User(/* данные второго пользователя */));

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Users saved");

        doReturn(null).when(userService).registration(users);

        ResponseEntity<String> response = userController.registrationManyUsers(users);

        assertEquals(expectedResponse, response);

        verify(userService, times(1)).registration(users);
    }


    @Test
    void testUpdateUser_Success() throws UserNotFoundException {
        Long userId = 1L;
        User updatedUser = new User(/* данные обновленного пользователя */);

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("User updated");
        doReturn(null).when(userService).updateUser(userId, updatedUser);

        ResponseEntity<String> response = userController.updateUser(userId, updatedUser);

        assertEquals(expectedResponse, response);

        verify(userService, times(1)).updateUser(userId, updatedUser);
    }

    @Test
    void testDeleteUser_Success() throws UserNotFoundException {
        Long userId = 1L;

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("User deleted");

        doReturn(null).when(userService).deleteUserById(userId);
        ResponseEntity<String> response = userController.deleteUser(userId);

        assertEquals(expectedResponse, response);

        verify(userService, times(1)).deleteUserById(userId);
    }
}
