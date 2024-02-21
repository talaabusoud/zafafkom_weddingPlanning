module org.example.zafafcom {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.zafafcom to javafx.fxml;
    exports org.example.zafafcom;
}