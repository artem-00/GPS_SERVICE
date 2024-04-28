package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void getId() {
        Long id = 1L;
        History history = new History();
        history.setId(id);

        assertEquals(id, history.getId());
    }

    @Test
    void setId() {
        Long id = 1L;
        History history = new History();

        history.setId(id);

        assertEquals(id, history.getId());
    }

    @Test
    void getIdUser() {
        Long idUser = 1L;
        History history = new History();
        history.setIdUser(idUser);

        assertEquals(idUser, history.getIdUser());
    }

    @Test
    void setIdUser() {
        Long idUser = 1L;
        History history = new History();

        history.setIdUser(idUser);

        assertEquals(idUser, history.getIdUser());
    }

    @Test
    void getIdLocation() {
        Long idLocation = 1L;
        History history = new History();
        history.setIdLocation(idLocation);

        assertEquals(idLocation, history.getIdLocation());
    }

    @Test
    void setIdLocation() {
        Long idLocation = 1L;
        History history = new History();

        history.setIdLocation(idLocation);

        assertEquals(idLocation, history.getIdLocation());
    }

    @Test
    void getIp() {
        String ip = "192.168.1.1";
        History history = new History();
        history.setIp(ip);

        assertEquals(ip, history.getIp());
    }

    @Test
    void setIp() {
        String ip = "192.168.1.1";
        History history = new History();

        history.setIp(ip);

        assertEquals(ip, history.getIp());
    }

    @Test
    void getRequestDateTime() {
        Date requestDateTime = new Date();
        History history = new History();
        history.setRequestDateTime(requestDateTime);

        assertEquals(requestDateTime, history.getRequestDateTime());
    }

    @Test
    void setRequestDateTime() {
        Date requestDateTime = new Date();
        History history = new History();

        history.setRequestDateTime(requestDateTime);

        assertEquals(requestDateTime, history.getRequestDateTime());
    }
}
