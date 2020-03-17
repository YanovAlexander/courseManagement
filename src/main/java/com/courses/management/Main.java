package com.courses.management;

import com.courses.management.common.Console;
import com.courses.management.common.DatabaseConnector;
import com.courses.management.common.MainController;
import com.courses.management.common.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseConnector dbConnector = new DatabaseConnector();
        MainController controller = new MainController(view, dbConnector.getDataSource());
        controller.read();
    }
}
