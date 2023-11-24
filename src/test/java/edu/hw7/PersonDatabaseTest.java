package edu.hw7;

import edu.hw7.personfinder.Person;
import edu.hw7.personfinder.PersonDatabase;
import edu.hw7.personfinder.PersonDatabaseLocks;
import edu.hw7.personfinder.PersonDatabaseSynchronized;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonDatabaseTest {
    private static Stream<Arguments> paramsDatabases() {
        return Stream.of(
            Arguments.of(new PersonDatabaseSynchronized()),
            Arguments.of(new PersonDatabaseLocks())
        );
    }

    @ParameterizedTest
    @MethodSource("paramsDatabases")
    void findEverywhere(PersonDatabase database) {
        Person p = new Person(1, "Ivan", "Russia", "7-999-123-45-67");

        Thread threadWrite = new Thread(() -> database.add(p));
        threadWrite.start();

        List<Person> nameList = database.findByName(p.name());
        List<Person> addressList = database.findByAddress(p.address());
        List<Person> phoneList = database.findByPhone(p.phoneNumber());

        assertThat(nameList).isEqualTo(addressList).isEqualTo(phoneList);
    }

    @ParameterizedTest
    @MethodSource("paramsDatabases")
    void findGuaranteed(PersonDatabase database) throws InterruptedException {
        Person p = new Person(1, "Ivan", "Russia", "7-999-123-45-67");

        Thread threadWrite = new Thread(() -> database.add(p));
        threadWrite.start();
        threadWrite.join();

        List<Person> nameList = database.findByName(p.name());
        List<Person> addressList = database.findByAddress(p.address());
        List<Person> phoneList = database.findByPhone(p.phoneNumber());

        assertThat(nameList).isEqualTo(addressList).isEqualTo(phoneList).contains(p);
    }

    @ParameterizedTest
    @MethodSource("paramsDatabases")
    void synchronizedRaceCondition(PersonDatabase template) {
        Person p1 = new Person(1, "Ivan", "Russia", "7-999-123-45-67");

        for (int i = 0; i < 5000; i++) {
            PersonDatabase database = template;

            Thread threadWrite = new Thread(() -> database.add(p1));
            Thread threadDelete = new Thread(() -> database.delete(p1.id()));

            AtomicReference<List<Person>> nameList = new AtomicReference<>(List.of());
            int finalI = i;
            Thread threadReadByName = new Thread(() -> {
                try {
                    Thread.sleep(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                nameList.set(database.findByName(p1.name()));
            }
            );

            AtomicReference<List<Person>> addressList = new AtomicReference<>(List.of());
            Thread threadReadByAddress = new Thread(() -> addressList.set(database.findByAddress(p1.address())));

            AtomicReference<List<Person>> phoneList = new AtomicReference<>(List.of());
            Thread threadReadByPhone = new Thread(() -> phoneList.set(database.findByPhone(p1.phoneNumber())));

            threadWrite.start();

            if (i % 2 == 0) {
                threadDelete.start();
            }
            switch (i % 3) {
                case 0:
                    threadReadByName.start();
                    if (!nameList.get().isEmpty()) {
                        threadReadByAddress.start();
                        threadReadByPhone.start();
                        assertThat(nameList.get()).isEqualTo(addressList.get()).isEqualTo(phoneList.get());
                    }
                    break;
                case 1:
                    threadReadByAddress.start();
                    if (!addressList.get().isEmpty()) {
                        threadReadByName.start();
                        threadReadByPhone.start();
                        assertThat(addressList.get()).isEqualTo(nameList.get()).isEqualTo(phoneList.get());
                    }
                    break;
                case 2:
                    threadReadByPhone.start();
                    if (!addressList.get().isEmpty()) {
                        threadReadByName.start();
                        threadReadByAddress.start();
                        assertThat(phoneList.get()).isEqualTo(nameList.get()).isEqualTo(addressList.get());
                    }
                    break;
            }
        }
    }
}
