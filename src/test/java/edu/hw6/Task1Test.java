package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    private final static String TEST_DIRECTORY = "src/test/java/edu/hw6/testFiles/Task1/";

    @Test
    void testFileCreation() throws IOException {
        String fileName = "testCreation.map";
        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.size()).isEqualTo(0);
        Files.delete(filePath);
    }

    @Test
    void testLoadingFromFile() throws IOException {
        String expectedFileName = "MapExample.map";
        String fileName = "MapLoad.map";
        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.size()).isEqualTo(3);
        assertThat(Files.readAllLines(filePath))
            .isEqualTo(Files.readAllLines(Path.of(TEST_DIRECTORY + expectedFileName)));
    }

    @Test
    void testPut() throws IOException {
        String fileName = "testPut.map";
        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);
        String putResult1 = diskMap.put("key1", "value1"); // null
        String putResult2 = diskMap.put("key2", "value2"); // null
        String putResult3 = diskMap.put("key1", "value3"); // value1

        Path filePath = Path.of(TEST_DIRECTORY + fileName);

        assertThat(putResult1).isNull();
        assertThat(putResult2).isNull();
        assertThat(putResult3).isEqualTo("value1");

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.readAllLines(filePath))
            .hasSameElementsAs(List.of("key2:value2", "key1:value3"));

        Files.delete(filePath);
    }

    @Test
    void testPutAll() throws IOException {
        String fileName = "testPutAll.map";
        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);
        Map<String, String> expectedMap = Map.of(
            "key1", "value1",
            "key2", "value2"
        );
        diskMap.putAll(expectedMap);

        Path filePath = Path.of(TEST_DIRECTORY + fileName);

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.readAllLines(filePath))
            .hasSameElementsAs(List.of("key1:value1", "key2:value2"));

        Files.delete(filePath);
    }

    @Test
    void testRemove() throws IOException {
        String fileName = "testRemove.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        diskMap.remove("key1");
        diskMap.remove("key2");
        diskMap.remove("key3");

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.readAllLines(filePath)).isEmpty();

        Files.delete(filePath);
    }

    @Test
    void testClear() throws IOException {
        String fileName = "testClear.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        diskMap.clear();

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(Files.readAllLines(filePath)).isEmpty();

        Files.delete(filePath);
    }

    @Test
    void testKeySet() throws IOException {
        String fileName = "testKeySet.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.keySet()).isEqualTo(Set.of("key1", "key2", "key3"));

        Files.delete(filePath);
    }

    @Test
    void testValues() throws IOException {
        String fileName = "testValues.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.values()).hasSameElementsAs(List.of("value1", "value2", "value3"));

        Files.delete(filePath);
    }

    @Test
    void testGet() throws IOException {
        String fileName = "testGet.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key3")).isEqualTo("value3");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
        assertThrows(NoSuchElementException.class, () -> diskMap.get("unknownKey"));

        Files.delete(filePath);
    }

    @Test
    void testContains() throws IOException {
        String fileName = "testContains.map";
        Path filePath = Path.of(TEST_DIRECTORY + fileName);
        Files.copy(Path.of(TEST_DIRECTORY + "MapExample.map"), filePath);

        DiskMap diskMap = new DiskMap(TEST_DIRECTORY + fileName);

        assertThat(Files.exists(filePath)).isTrue();
        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsKey("key2")).isTrue();
        assertThat(diskMap.containsKey("key3")).isTrue();
        assertThat(diskMap.containsKey("unknownKey")).isFalse();

        assertThat(diskMap.containsValue("value1")).isTrue();
        assertThat(diskMap.containsValue("value2")).isTrue();
        assertThat(diskMap.containsValue("value3")).isTrue();
        assertThat(diskMap.containsValue("unknownValue")).isFalse();

        Files.delete(filePath);
    }
}
