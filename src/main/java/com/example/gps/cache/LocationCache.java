package com.example.gps.cache;

import com.example.gps.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LocationCache {
    private static final int CACHE_SIZE = 5;
    private final List<Location> cache;
    private final Logger logger = LoggerFactory.getLogger(LocationCache.class);

    public LocationCache() {
        cache = new ArrayList<>();
    }
    public int getCacheSize() {
        return cache.size();
    }

    public void addToCache(Location location) {
        if (cache.size() >= CACHE_SIZE) {
            cache.remove(0);
        }
        cache.add(location);
        logger.info("Added location to cache: {}", location);
    }

    public Location getFromCache(Location location) {
        for (Location cachedLocation : cache) {
            if (cachedLocation.equals(location)) {
                logger.info("Location found in cache: {}", cachedLocation);
                return cachedLocation;
            }
        }
        return null;
    }

    public void logCache() {
        logger.info("Current cache contents:");
        for (Location location : cache) {
            logger.info("{}", location);
        }
    }
}
