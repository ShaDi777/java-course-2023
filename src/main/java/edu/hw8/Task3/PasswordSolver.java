package edu.hw8.Task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;

public class PasswordSolver {
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final Map<String, String> profilesMap = new ConcurrentHashMap<>();
    private final Map<String, String> solved = new ConcurrentHashMap<>();
    private final List<Integer> currentPasswordCharIndexes = new ArrayList<>() {{
        add(-1);
    }};

    public PasswordSolver(Collection<Profile> profiles) {
        for (var profile : profiles) {
            profilesMap.put(profile.passwordHashMD5(), profile.username());
        }
    }

    public Map<String, String> solveSingleThread() {
        solved.clear();
        while (profilesMap.size() > solved.size()) {
            tryNextPassword();
        }
        return solved;
    }

    public Map<String, String> solveMultiThread(int numThreads) {
        // ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThreads);
        try (ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(numThreads)) {
            while (profilesMap.size() > solved.size()) {
                tryNextPassword();
            }
        }
        return solved;
    }

    private void tryNextPassword() {
        String password = getNextPassword();
        String hash;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder();
            byte[] array = md.digest(password.getBytes());
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100), 1, 3);
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException ignored) {
            hash = password;
        }
        if (profilesMap.containsKey(hash)) {
            solved.put(profilesMap.get(hash), password);
        }
    }

    private synchronized String getNextPassword() {
        int index = currentPasswordCharIndexes.size() - 1;
        boolean increaseValue = true;
        while (index >= 0 && increaseValue) {
            increaseValue = false;
            int newValue = currentPasswordCharIndexes.get(index) + 1;

            if (newValue >= ALPHABET.length()) {
                newValue = 0;
                increaseValue = true;
            }

            currentPasswordCharIndexes.set(index, newValue);
            index--;
        }

        if (increaseValue) {
            currentPasswordCharIndexes.add(0);
        }

        return currentPasswordCharIndexes.stream()
            .map(ALPHABET::charAt)
            .collect(
                Collector.of(
                    StringBuilder::new,
                    StringBuilder::append,
                    StringBuilder::append,
                    StringBuilder::toString
                )
            );
    }
}
