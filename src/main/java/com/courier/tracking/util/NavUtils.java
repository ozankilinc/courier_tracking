package com.courier.tracking.util;

import com.courier.tracking.model.dto.PointDto;

public class NavUtils {

    private NavUtils() {
    }

    public static double getDistance(PointDto startPoint, PointDto endPoint) {

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
        /*
        final SpatialContext spatialContext = SpatialContext.GEO;
        Point point1 = spatialContext.makePoint(startPoint.getLat(), startPoint.getLng());
        Point point2 = spatialContext.makePoint(endPoint.getLat(), endPoint.getLng());
        return DistanceUtils.degrees2Dist(spatialContext.getDistCalc().distance(point1, point2), DistanceUtils.DEG_TO_KM) * 1000;


        double longitude1 = startPoint.getLng();
        double longitude2 = endPoint.getLng();
        double latitude1 = Math.toRadians(startPoint.getLat());
        double latitude2 = Math.toRadians(endPoint.getLat());
        double longDiff = Math.toRadians(longitude2 - longitude1);
        double y = Math.sin(longDiff) * Math.cos(latitude2);
        double x = Math.cos(latitude1) * Math.sin(latitude2) - Math.sin(latitude1) * Math.cos(latitude2) * Math.cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;

 */
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
