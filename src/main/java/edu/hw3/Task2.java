package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public final class Task2 {
    private Task2() {
    }

    public static List<String> clusterize(String line) {
        List<String> results = new ArrayList<>();
        if (line == null) {
            return results;
        }

        Stack<Character> stack = new Stack<>();
        StringBuilder tempResult = new StringBuilder();
        boolean init = false;
        for (char c : line.toCharArray()) {
            tempResult.append(c);
            if (c == '(') {
                init = true;
                stack.push(c);
            }
            if (c == ')') {
                stack.pop();
            }
            if (stack.empty() && init) {
                init = false;
                results.add(tempResult.toString());
                tempResult.setLength(0);
            }
        }

        return results;
    }
}
