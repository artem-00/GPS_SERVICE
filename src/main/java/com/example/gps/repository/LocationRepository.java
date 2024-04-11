package com.example.gps.repository;

import com.example.gps.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LocationRepository extends CrudRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.user.id = :userId")
    List<Location> findAllByUserId(@Param("userId") Long userId);
}
