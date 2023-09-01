module com.editorimage.imageedtior {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.editorimage.imageedtior to javafx.fxml;
    exports com.editorimage.imageedtior;
}