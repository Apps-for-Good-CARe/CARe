package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.List;

public class OnPath {
    public static boolean isOnPath(List<Point> path, Point p){
        for (int i = 0; i < path.size()-1; i++){
            Point p1 = path.get(i);
            Point p2 = path.get(i+1);
            if (Math.abs((p2.longitude() - p1.longitude()) * p.latitude() + (p1.latitude() - p2.latitude()) * p.longitude() + p1.longitude() * (p2.latitude() - p1.latitude()) - p1.latitude() * (p2.longitude() - p1.longitude())) / Math.sqrt((p2.longitude() - p1.longitude())*(p2.longitude() - p1.longitude())  + (p1.latitude() - p2.latitude())*(p1.latitude() - p2.latitude())) < 20/111000){
                return true;
            }
        }
        return false;
    }
}
