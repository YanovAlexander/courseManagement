package com.courses.management.common;

public class MainController {
    private View view;

    public MainController(View view) {
        this.view = view;
    }

    public void read() {
        view.write("Welcome");
        while (true) {
            String read = view.read();
            doCommand(read);
        }
    }

    private void doCommand(String read) {
        switch (read) {
            case "help": {
                view.write("Help information");
                break;
            }
            case "create_course": {
                break;
            }
            default: {
                view.write("Enter the correct command");
                break;
            }
        }
    }
}
