package com.courses.management.common;

import com.courses.management.common.commands.util.InputString;

public interface Command {
    int COMMAND_NAME_INDEX = 0;
    String command();

    void process(InputString input);

    default boolean canProcess(InputString userInput) {
        String[] splitFormat = command().split("\\|");
        final String[] inputParameters = userInput.getParameters();
        return inputParameters[COMMAND_NAME_INDEX].equals(splitFormat[COMMAND_NAME_INDEX]);
    }
}
