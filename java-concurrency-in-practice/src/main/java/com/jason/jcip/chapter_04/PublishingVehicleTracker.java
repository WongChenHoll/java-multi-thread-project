package com.jason.jcip.chapter_04;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 安全发布底层状态的车辆追踪器
 * <pre>
 *     PublishingVehicleTracker 将其线程安全性委托给 ConcurrentHashMap ，只是Map中的元素是线程安全且可变的Point，而并非不可变。
 * </pre>
 *
 * @author WongChenHoll
 * @version 3.0
 * @date 2022-6-14 星期二 21:55
 **/
@ThreadSafe
public class PublishingVehicleTracker {

    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String, SafePoint> locations) {
        this.locations = new ConcurrentHashMap<>(locations);
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }

    public Map<String, SafePoint> getLocations() {
        return unmodifiableMap;
    }

    public SafePoint getLocation(String vehicle) {
        return locations.get(vehicle);
    }

    public void setLocation(String vehicle, int x, int y) {
        if (!locations.containsKey(vehicle)) {
            throw new IllegalArgumentException("invalid vehicle name: " + vehicle);
        }
        locations.get(vehicle).set(x, y);
    }
}
