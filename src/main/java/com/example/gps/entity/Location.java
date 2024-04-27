package com.example.gps.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", city = " + city +
                ", country = " + country ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(country, location.country) && Objects.equals(city, location.city) && Objects.equals(user, location.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, user);
    }
}
