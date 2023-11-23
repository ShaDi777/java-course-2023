package edu.hw7.personfinder;

import java.util.List;

public class PersonDatabaseSynchronized extends PersonDatabaseBase {
    @Override
    public synchronized void add(Person person) {
        baseAdd(person);
    }

    @Override
    public synchronized void delete(int id) {
        baseDelete(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return baseFindByName(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return baseFindByAddress(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return baseFindByPhone(phone);
    }
}
