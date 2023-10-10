package edu.hw2.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {}

    public static CallingInfo callingInfo() {
        // Thread.currentThread().getStackTrace()
        var callerInfo = new Throwable().getStackTrace()[1];
        return new CallingInfo(callerInfo.getClassName(), callerInfo.getMethodName());
    }

    public record CallingInfo(String className, String methodName) {}
}
