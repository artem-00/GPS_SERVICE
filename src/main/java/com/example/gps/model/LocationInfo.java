package com.example.gps.model;

public class LocationInfo {
    private String country;
    private String city;

    public LocationInfo() {
        // Пустой конструктор требуется для JPA-сущности
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
