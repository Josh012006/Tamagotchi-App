module TP2 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens controllers to javafx.fxml;
    exports view;
    exports model.util;
    exports model.network;
    exports model.emotion;
    exports model.behavior;
    exports model.action;
    exports model;
    exports controllers;
}
