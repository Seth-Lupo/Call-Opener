module com.lupo.callopener {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.media;
    requires json.simple;
    requires java.desktop;

    opens com.lupo.callopener to javafx.fxml, javafx.graphics;
    exports com.lupo.callopener;
}
