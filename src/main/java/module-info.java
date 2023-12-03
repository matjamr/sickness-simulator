module com.jamroz.sicknesssimulator.sicknesssimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires lombok;

    opens com.jamroz.sicknesssimulator.sicknesssimulator to javafx.fxml;
    exports com.jamroz.sicknesssimulator.sicknesssimulator;
    exports com.jamroz.sicknesssimulator.sicknesssimulator.controller;
    opens com.jamroz.sicknesssimulator.sicknesssimulator.controller to javafx.fxml;
    exports com.jamroz.sicknesssimulator.sicknesssimulator.util.generator;
    opens com.jamroz.sicknesssimulator.sicknesssimulator.util.generator to javafx.fxml;
}