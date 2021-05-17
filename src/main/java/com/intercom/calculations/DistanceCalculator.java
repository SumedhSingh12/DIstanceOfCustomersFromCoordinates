package com.intercom.calculations;

import com.intercom.common.Constants;
import com.intercom.exceptions.InvalidCoordinatesException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DistanceCalculator {

    private final static double intercomLatitude = Math.toRadians(Double.parseDouble(Constants.INTERCOM_LATITUDE));
    private final static double intercomLongitude = Math.toRadians(Double.parseDouble(Constants.INTERCOM_LONGITUDE));

    public static double getDistance(final double latitude, final double longitude) throws InvalidCoordinatesException {

        validateLatitudeAndLongitude(latitude, longitude);

        final double customerLatitude = Math.toRadians(latitude);
        final double customerLongitude = Math.toRadians(longitude);

        final double latitudeDelta = customerLongitude - intercomLongitude;
        final double centralAngle = getCentralAngle(customerLatitude, intercomLatitude, latitudeDelta);
        final double surface_distance = Constants.EARTH_RADIUS * centralAngle;
        return surface_distance;
    }

    private static void validateLatitudeAndLongitude(final double latitude, final double longitude) throws InvalidCoordinatesException {
        if (!(Double.compare(latitude, Constants.MIN_LATITUDE) >= 0 && Double.compare(latitude, Constants.MAX_LATITUDE) <= 0)) {
            throw new InvalidCoordinatesException("The latitude should be in the range -90 to 90.");
        }
        if (!(Double.compare(longitude, Constants.MIN_LONGITUDE) >= 0 && Double.compare(longitude, Constants.MAX_LONGITUDE) <= 0)) {
            throw new InvalidCoordinatesException("The longitude should be in the range -180 to 180.");
        }
    }

    private static double getCentralAngle(final double customerLatitude, final double intercomLatitude, final double latitudeDelta) {
        return Math.acos(Math.sin(customerLatitude) * Math.sin(intercomLatitude) + Math.cos(customerLatitude) * Math.cos(intercomLatitude) * Math.cos(latitudeDelta));
    }
}
