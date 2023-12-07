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

    public PasswordSolver(Collection<Profile> profiles) {
        for (var profile : profiles) {
            profilesMap.put(profile.passwordHashMD5(), profile.username());
        }
    }

    public PasswordSolver(PasswordSolver other) {
        profilesMap.putAll(other.profilesMap);
    }

    public Map<String, String> solveSingleThread() {
        int length = 1;
        while (profilesMap.size() > solved.size()) {
            int steps = getTotalPasswordsCount(length);
            List<Integer> currentPassword = getEmptyPassword(length);
            tryNextPasswordNTimes(currentPassword, steps);
            length++;
        }
        return solved;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public Map<String, String> solveMultiThread(int numThreads) {
        try (ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(numThreads)) {
            threadPoolExecutor.execute(() -> tryNextPasswordNTimes(getEmptyPassword(1), getTotalPasswordsCount(1)));
            threadPoolExecutor.execute(() -> tryNextPasswordNTimes(getEmptyPassword(2), getTotalPasswordsCount(2)));
            int length = 3;
            while (profilesMap.size() > solved.size()) {
                int steps = (getTotalPasswordsCount(length) / numThreads) + numThreads;
                List<Integer> currentPassword = getEmptyPassword(length);

                for (int i = 0; i < numThreads; i++) {
                    List<Integer> finalCurrentPassword = new ArrayList<>(currentPassword);
                    threadPoolExecutor.execute(() -> tryNextPasswordNTimes(finalCurrentPassword, steps));
                    mutateToNextPassword(currentPassword, steps);
                }

                length++;
            }
        }
        return solved;
    }

    private List<Integer> getEmptyPassword(int length) {
        List<Integer> currentPassword = new ArrayList<>();
        for (int i = 0; i < length - 1; i++) {
            currentPassword.add(0);
        }
        currentPassword.add(-1);
        return currentPassword;
    }

    private Integer getTotalPasswordsCount(int length) {
        return (int) (Math.pow(ALPHABET.length(), length));
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private String getHashMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        byte[] array = md.digest(input.getBytes());
        for (byte b : array) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }

        return sb.toString();
    }

    private void tryNextPasswordNTimes(List<Integer> passwordList, int n) {
        for (int iteration = 0; iteration < n && solved.size() < profilesMap.size(); iteration++) {
            mutateToNextPassword(passwordList, 1);
            String password = getStringFromCharList(passwordList);
            String hash;
            try {
                hash = getHashMD5(password);
            } catch (NoSuchAlgorithmException ignored) {
                hash = password;
            }
            if (profilesMap.containsKey(hash)) {
                solved.put(profilesMap.get(hash), password);
            }
        }
    }

    private void mutateToNextPassword(List<Integer> currentPassword, int n) {
        int steps = n;
        int index = currentPassword.size() - 1;
        while (index >= 0 && steps > 0) {
            int currentSteps = (steps % getTotalPasswordsCount(currentPassword.size() - index))
                / getTotalPasswordsCount(currentPassword.size() - index - 1);
            int newValue = currentPassword.get(index) + currentSteps;

            if (newValue >= ALPHABET.length()) {
                newValue = newValue % ALPHABET.length();
                steps += getTotalPasswordsCount(currentPassword.size() - index);
            }

            currentPassword.set(index, newValue);
            steps -= (int) (currentSteps * Math.pow(ALPHABET.length(), currentPassword.size() - index - 1));
            index--;
        }
    }

    private String getStringFromCharList(List<Integer> charList) {
        return charList.stream()
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
