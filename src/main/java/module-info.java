module com.example.parkingcesur {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens com.example.parkingcesur.img;


    opens com.example.parkingcesur to javafx.fxml;
    exports com.example.parkingcesur;
}