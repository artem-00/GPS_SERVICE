package com.example.gps.model;

import com.example.gps.entity.LocationEntity;

public class Location {
    private Long id;
    private String country;
    private String city;

    public static Location toModel(LocationEntity entity){
        Location model = new Location();
        model.setId(entity.getId());
        model.setCountry(entity.getCountry());
        model.setCity(entity.getCity());
        return model;
    }
    public Location() {
        // Пустой конструктор требуется для JPA-сущности

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
