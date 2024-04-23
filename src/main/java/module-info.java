module com.example.composerapp.composerapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.composerapp.composerapp to javafx.fxml;
    exports com.example.composerapp.composerapp;
}