package edu.hw3;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    void nullTest() {
        List<Task5.PersonLabel> contacts = null;
        List<Task5.PersonLabel> expectedResult = List.of();

        var actualResultAsc = Task5.parseContacts(contacts, Task5.ORDER.ASC);
        var actualResultDesc = Task5.parseContacts(contacts, Task5.ORDER.DESC);

        assertThat(actualResultAsc).isEqualTo(expectedResult);
        assertThat(actualResultDesc).isEqualTo(expectedResult);
    }

    @Test
    void emptyTest() {
        List<Task5.PersonLabel> contacts = List.of();
        List<Task5.PersonLabel> expectedResult = List.of();

        var actualResultAsc = Task5.parseContacts(contacts, Task5.ORDER.ASC);
        var actualResultDesc = Task5.parseContacts(contacts, Task5.ORDER.DESC);

        assertThat(actualResultAsc).isEqualTo(expectedResult);
        assertThat(actualResultDesc).isEqualTo(expectedResult);
    }

    @Test
    void nullSurnameAscendingTest() {
        var alice = new Task5.PersonLabel("Alice", null);
        var bob = new Task5.PersonLabel("Bob", null);
        var dara = new Task5.PersonLabel("Dara", null);
        var chris = new Task5.PersonLabel("Chris", null);
        var zack = new Task5.PersonLabel("Zack", null);

        List<Task5.PersonLabel> contacts = List.of(bob, chris, zack, dara, alice);
        List<Task5.PersonLabel> expectedResult = List.of(alice, bob, chris, dara, zack);

        var actualResult = Task5.parseContacts(contacts, Task5.ORDER.ASC);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void nullSurnameDescendingTest() {
        var alice = new Task5.PersonLabel("Alice", null);
        var bob = new Task5.PersonLabel("Bob", null);
        var dara = new Task5.PersonLabel("Dara", null);
        var chris = new Task5.PersonLabel("Chris", null);
        var zack = new Task5.PersonLabel("Zack", null);

        List<Task5.PersonLabel> contacts = List.of(bob, chris, zack, dara, alice);
        List<Task5.PersonLabel> expectedResult = List.of(zack, dara, chris, bob, alice);

        var actualResult = Task5.parseContacts(contacts, Task5.ORDER.DESC);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void withSurnameAscendingTest() {
        var p1 = new Task5.PersonLabel("Thomas", "Aquinas");
        var p2 = new Task5.PersonLabel("Rene", "Descartes");
        var p3 = new Task5.PersonLabel("David", "Hume");
        var p4 = new Task5.PersonLabel("John", "Locke");

        List<Task5.PersonLabel> contacts = List.of(p4, p1, p3, p2);
        List<Task5.PersonLabel> expectedResult = List.of(p1, p2, p3, p4);

        var actualResult = Task5.parseContacts(contacts, Task5.ORDER.ASC);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void withSurnameDescendingTest() {
        var p1 = new Task5.PersonLabel("Carl", "Gauss");
        var p2 = new Task5.PersonLabel("Leonhard", "Euler");
        var p3 = new Task5.PersonLabel("Paul", "Erdos");

        List<Task5.PersonLabel> contacts = List.of(p1, p3, p2);
        List<Task5.PersonLabel> expectedResult = List.of(p1, p2, p3);

        var actualResult = Task5.parseContacts(contacts, Task5.ORDER.DESC);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
