package com.example.gps.DTO;

import com.example.gps.entity.Location;

public class LocationDTO {
    private Long id;
    private String country;
    private String city;

    public static LocationDTO toModel(Location entity){
        LocationDTO model = new LocationDTO();
        model.setId(entity.getId());
        model.setCountry(entity.getCountry());
        model.setCity(entity.getCity());
        return model;
    }
    public LocationDTO() {
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
