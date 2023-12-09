package edu.hw10.models.task2;

import edu.hw10.task2.Cache;

public interface FibonacciCalculator {
    @Cache(persist = false)
    long fib(int number);
}
