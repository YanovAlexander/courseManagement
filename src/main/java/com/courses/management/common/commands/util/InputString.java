package com.courses.management.common.commands.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputString {
    private static final Logger LOG = LogManager.getLogger(InputString.class);
    private String input;

    public InputString(String input) {
        this.input = input;
    }

    public void validateParameters(String command) {
        int commandLength = getParametersSize(command);
        int inputLength = getLength();
        if (commandLength != inputLength) {
            String message = String.format("Invalid number of parameters separated by |, expected %s, but was %s"
                    , commandLength, inputLength);
            LOG.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    public int getLength() {
        return getParametersSize(input);
    }

    public String[] getParameters() {
        return input.split("\\|");
    }

    private int getParametersSize(String parameters) {
        return parameters.split("\\|").length;
    }
}
