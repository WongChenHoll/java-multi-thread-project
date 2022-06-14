package com.jason.jcip.chapter_04;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 基于监视器模式的车辆追踪。
 *
 * <pre>
 *     虽然类 MutablePoint 不是线程安全的，但是追踪器类 MonitorVehicleTracker 是线程安全的。
 *     它所包含的Map对象和可变的Point对象都未曾发布。
 * </pre>
 * <pre>
 *      Monitor
 *          n. 显示器，监控器；监视仪，监护仪；
 *          v. 监视；监听
 *
 *      Vehicle
 *          n. 交通工具，车辆；
 *
 *      Tracker
 *          n. 拉纤者，纤夫；追踪系统，[自] 跟踪装置；追踪者
 * </pre>
 *
 * @author WongChenHoll
 * @version 1.0
 * @date 2022-6-14 星期二 16:55
 **/
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String vehicle) {
        MutablePoint point = locations.get(vehicle);
        return point == null ? null : new MutablePoint(point);
    }

    public synchronized void setLocation(String vehicle, int x, int y) {
        MutablePoint point = locations.get(vehicle);
        if (point == null) {
            throw new IllegalArgumentException("No such vehicle:" + vehicle);
        }
        point.x = x;
        point.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> map) {
        HashMap<String, MutablePoint> result = new HashMap<>();
        for (String vehicle : map.keySet()) {
            result.put(vehicle, map.get(vehicle));
        }
        return Collections.unmodifiableMap(result); // 返回的是一个“无法改变的Map”
    }
}
