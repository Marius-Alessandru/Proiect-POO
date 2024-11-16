module com.example.mmak_industry_sa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mmak_industry_sa to javafx.fxml;
    exports com.example.mmak_industry_sa;
}