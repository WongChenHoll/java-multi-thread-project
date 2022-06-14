package com.jason.jcip.chapter_04;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于监视器模式的车辆追踪。
 * 将线程安全委托给 ConcurrentHashMap.
 *
 * @author WongChenHoll
 * @version 2.0
 * @date 2022-6-14 星期二 17:26
 **/
@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(ConcurrentHashMap<String, Point> points) {
        this.locations = new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;
    }

    public Point getLocation(String vehicle) {
        return locations.get(vehicle);
    }

    public void setLocation(String vehicle, int x, int y) {
        if (locations.replace(vehicle, new Point(x, y)) == null) {
            throw new IllegalArgumentException("invalid vehicle name: " + vehicle);
        }
    }
}
