package edu.hw10.models.task2;

import edu.hw10.task2.Cache;

public class RecursiveFibonacciCalculator implements FibonacciCalculator {
    @Override
    @Cache(persist = true)
    public long fib(int number) {
        if (number < 0) {
            return 0;
        }
        if (number <= 1) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
