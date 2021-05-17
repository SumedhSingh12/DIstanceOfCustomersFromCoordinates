package com.intercom.calculations;

import com.intercom.TestData;
import com.intercom.exceptions.InvalidCoordinatesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DistanceCalculatorTests {

    @Test
    public void testGetDistance() throws InvalidCoordinatesException {
        final double latitude = TestData.zeroDouble;
        final double longitude = TestData.zeroDouble;
        final double distance = DistanceCalculator.getDistance(latitude, longitude);
        Assertions.assertEquals(distance, TestData.distanceOfIntercomFromEquator);
    }

    @Test
    public void testLatitudeOutOfRange() {
        final double latitude = TestData.outOfRangeLatitude;
        final double longitude = TestData.zeroDouble;
        Assertions.assertThrows(InvalidCoordinatesException.class, () -> DistanceCalculator.getDistance(latitude, longitude));
    }

    @Test
    public void testLongitudeOutOfRange() {
        final double latitude = TestData.zeroDouble;
        final double longitude = TestData.outOfRangeLongitude;
        Assertions.assertThrows(InvalidCoordinatesException.class, () -> DistanceCalculator.getDistance(latitude, longitude));
    }
}
