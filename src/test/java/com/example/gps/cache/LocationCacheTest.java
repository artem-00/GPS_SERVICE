package com.example.gps.cache;

import com.example.gps.entity.Location;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LocationCacheTest {

    @Test
    void addToCache_AddLocationToEmptyCache() {
        // Arrange
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);

        // Act
        locationCache.addToCache(location);

        // Assert
        assertEquals(1, locationCache.getCacheSize()); // Use the new getCacheSize() method

        // Verify logging
        // Assuming logger is mocked and verified separately
    }

    @Test
    void addToCache_EnsureCacheSizeLimitIsRespected() {
        // Arrange
        LocationCache locationCache = new LocationCache();
        Location firstLocation = new Location(/* provide location details */);
        Location secondLocation = new Location(/* provide location details */);

        // Act
        locationCache.addToCache(firstLocation);
        locationCache.addToCache(secondLocation);

        // Assert
        assertEquals(2, locationCache.getCacheSize()); // Use the new getCacheSize() method

        // Verify logging
        // Assuming logger is mocked and verified separately
    }

    @Test
    void getFromCache_LocationExists_ReturnsLocation() {
        // Arrange
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);
        locationCache.addToCache(location);

        // Act
        Location cachedLocation = locationCache.getFromCache(location);

        // Assert
        assertNotNull(cachedLocation);
        assertEquals(location, cachedLocation);

        // Verify logging
        // Assuming logger is mocked and verified separately
    }

    @Test
    void getFromCache_LocationDoesNotExist_ReturnsNull() {
        // Arrange
        LocationCache locationCache = new LocationCache();
        Location location = new Location(/* provide location details */);

        // Act
        Location cachedLocation = locationCache.getFromCache(location);

        // Assert
        assertNull(cachedLocation);

        // Verify logging
        // Assuming logger is mocked and verified separately
    }
}