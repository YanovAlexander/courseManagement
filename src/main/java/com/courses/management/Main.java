package com.courses.management;

import com.courses.management.common.Console;
import com.courses.management.common.MainController;
import com.courses.management.common.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        MainController controller = new MainController(view);
        controller.read();
    }
}
