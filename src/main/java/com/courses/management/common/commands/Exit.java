package com.courses.management.common.commands;

import com.courses.management.common.Command;
import com.courses.management.common.View;
import com.courses.management.common.commands.util.Commands;
import com.courses.management.common.commands.util.InputString;
import com.courses.management.common.exceptions.ExitException;

public class Exit implements Command {
    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public String command() {
        return Commands.EXIT;
    }

    @Override
    public void process(InputString input) {
        view.write("Good bye!");
        throw new ExitException("Exit");
    }
}
