package com.example.gps.DTO;

import com.example.gps.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private Long id;
    private String login;
    private List<LocationDTO> locations;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());

        // Проверяем, не является ли список местоположений пользователя null
        if (entity.getLocation() != null) {
            // Если список местоположений не null, выполняем необходимую обработку
            model.setLocations(entity.getLocation().stream().map(LocationDTO::toModel).collect(Collectors.toList()));
        }

        return model;
    }

    UserDTO() {
        // Пустой конструктор требуется для JPA-сущности
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<LocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDTO> locations) {
        this.locations = locations;
    }
}
