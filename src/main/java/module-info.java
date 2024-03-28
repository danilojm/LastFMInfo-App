module com.danilomendes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires lastfm.java;
    requires com.google.gson;
    requires java.net.http;

    opens com.danilomendes.Model to com.google.gson;
    opens com.danilomendes.Controller to javafx.fxml;
    opens com.danilomendes to javafx.fxml;

    exports com.danilomendes.Model;
    exports com.danilomendes;
}
