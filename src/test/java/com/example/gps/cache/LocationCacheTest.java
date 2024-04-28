package com.example.gps.cache;

import com.example.gps.entity.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationCacheTest {

    @Test
    void addToCache_AddLocationToEmptyCache() {
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);

        locationCache.addToCache(location);

        assertEquals(1, locationCache.getCacheSize());
    }

    @Test
    void addToCache_EnsureCacheSizeLimitIsRespected() {
        LocationCache locationCache = new LocationCache();
        Location firstLocation = new Location(/* provide location details */);
        Location secondLocation = new Location(/* provide location details */);

        locationCache.addToCache(firstLocation);
        locationCache.addToCache(secondLocation);

        assertEquals(2, locationCache.getCacheSize());
    }

    @Test
    void getFromCache_LocationExists_ReturnsLocation() {
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);
        locationCache.addToCache(location);

        Location cachedLocation = locationCache.getFromCache(location);

        assertNotNull(cachedLocation);
        assertEquals(location, cachedLocation);
    }

    @Test
    void getFromCache_LocationDoesNotExist_ReturnsNull() {
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);

        Location cachedLocation = locationCache.getFromCache(location);

        assertNull(cachedLocation);
    }
}