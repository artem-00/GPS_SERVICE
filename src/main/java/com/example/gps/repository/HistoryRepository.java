package com.example.gps.repository;

import com.example.gps.entity.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepository extends CrudRepository<History, Long> {
}