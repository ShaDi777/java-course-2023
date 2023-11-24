package edu.hw7.personfinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PersonDatabaseBase implements PersonDatabase {
    protected final Map<Integer, Person> database = new HashMap<>();
    protected final Map<String, List<Person>> nameMap = new HashMap<>();
    protected final Map<String, List<Person>> addressMap = new HashMap<>();
    protected final Map<String, List<Person>> phoneMap = new HashMap<>();

    protected void baseAdd(Person person) {
        nameMap.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        addressMap.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        phoneMap.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        database.put(person.id(), person);
    }

    protected void baseDelete(int id) {
        Person deleted = database.remove(id);
        if (deleted != null) {
            nameMap.get(deleted.name()).remove(deleted);
            addressMap.get(deleted.address()).remove(deleted);
            phoneMap.get(deleted.phoneNumber()).remove(deleted);
        }
    }

    protected List<Person> baseFindByName(String name) {
        return nameMap.getOrDefault(name, List.of());
    }

    protected List<Person> baseFindByAddress(String address) {
        return addressMap.getOrDefault(address, List.of());
    }

    protected List<Person> baseFindByPhone(String phone) {
        return phoneMap.getOrDefault(phone, List.of());
    }
}
