package com.jason.jcip.chapter_04;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过封闭机制来保证线程安全
 * <pre>
 *     在该类中说明了如何通过封闭与加锁机制使一个类称为线程安全的。
 *     PersonSet 的状态由HashSet 来管理，而HashSet 并非线程安全的。
 *     由于 mySet 是私有的，所以并不会逸出，依次HashSet被封闭在PersonSet中。
 *     在 PersonSet 中使用了它的内置锁来保护它的状态
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-6-14 星期二 16:07
 **/
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean isContainsPerson(Person person) {
        return mySet.contains(person);
    }
}

class Person {

}
