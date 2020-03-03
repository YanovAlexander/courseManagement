package com.courses.management.common;

public interface Command {
    String command();

    void process();

    default boolean canProcess(String command) {
       return command.trim().equals(command());
    }
}
