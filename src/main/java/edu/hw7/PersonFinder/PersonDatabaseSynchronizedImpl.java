package edu.hw7.PersonFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabaseSynchronizedImpl implements PersonDatabase {
    private final Map<Integer, Person> database = new HashMap<>();
    private final Map<String, List<Person>> nameMap = new HashMap<>();
    private final Map<String, List<Person>> addressMap = new HashMap<>();
    private final Map<String, List<Person>> phoneMap = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        nameMap.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        addressMap.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        phoneMap.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        database.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person deleted = database.remove(id);
        if (deleted != null) {
            nameMap.get(deleted.name()).remove(deleted);
            addressMap.get(deleted.address()).remove(deleted);
            phoneMap.get(deleted.phoneNumber()).remove(deleted);
        }
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.getOrDefault(name, List.of());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressMap.getOrDefault(address, List.of());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneMap.getOrDefault(phone, List.of());
    }
}
