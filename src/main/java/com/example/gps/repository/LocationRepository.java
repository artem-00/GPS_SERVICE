package com.example.gps.repository;

import com.example.gps.entity.LocationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LocationRepository extends CrudRepository<LocationEntity, Long> {

    @Query("SELECT l FROM LocationEntity l WHERE l.user.id = :userId")
    List<LocationEntity> findAllByUserId(@Param("userId") Long userId);
}
