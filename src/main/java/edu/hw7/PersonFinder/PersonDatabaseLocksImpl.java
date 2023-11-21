package edu.hw7.PersonFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseLocksImpl implements PersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<Integer, Person> database = new HashMap<>();
    private final Map<String, List<Person>> nameMap = new HashMap<>();
    private final Map<String, List<Person>> addressMap = new HashMap<>();
    private final Map<String, List<Person>> phoneMap = new HashMap<>();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            nameMap.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
            addressMap.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
            phoneMap.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
            database.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            Person deleted = database.remove(id);
            if (deleted != null) {
                nameMap.get(deleted.name()).remove(deleted);
                addressMap.get(deleted.address()).remove(deleted);
                phoneMap.get(deleted.phoneNumber()).remove(deleted);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameMap.getOrDefault(name, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return addressMap.getOrDefault(address, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneMap.getOrDefault(phone, List.of());
        } finally {
            lock.readLock().unlock();
        }
    }
}
