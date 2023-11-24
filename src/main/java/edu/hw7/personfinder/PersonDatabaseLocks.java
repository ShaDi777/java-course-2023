package edu.hw7.personfinder;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseLocks extends PersonDatabaseBase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            baseAdd(person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            baseDelete(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return baseFindByName(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return baseFindByAddress(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return baseFindByPhone(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
