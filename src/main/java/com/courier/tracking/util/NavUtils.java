package com.courier.tracking.util;

import com.courier.tracking.model.dto.PointDto;

public class NavUtils {

    private NavUtils() {
    }

    public static double getDistance(PointDto startPoint, PointDto endPoint) {
        if (startPoint == null) {
            return 0.0;
        }

        double lon1 = startPoint.getLng();
        double lon2 = endPoint.getLng();

        double lat1 = startPoint.getLat();
        double lat2 = endPoint.getLat();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist)*1000;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
