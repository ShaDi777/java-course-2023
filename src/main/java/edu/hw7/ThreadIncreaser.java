package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadIncreaser {
    private final AtomicInteger counter = new AtomicInteger(0);

    public ThreadIncreaser() {
    }

    // Напишите программу, в которой несколько потоков увеличивают общий счетчик на 1.
    // Напишите тесты: убедитесь, что счетчик потокобезопасен и использует классы Atomic
    // для исключения состояния гонки.
    public void incrementUntil(int n) throws InterruptedException {
        Thread thread1 = new Thread(() -> run(n / 2));
        Thread thread2 = new Thread(() -> run((n / 2) + (n % 2)));

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    public int getCounter() {
        return counter.get();
    }

    private void run(int n) {
        for (int i = 0; i < n; i++) {
            counter.incrementAndGet();
        }
    }
}
