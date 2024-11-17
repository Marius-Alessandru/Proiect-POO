package com.example.mmak_industry_sa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class GadgetApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load gadgets from file (assumed to be already serialized)
        String filePath = "gadgeturi.ser";
        List<Gadget> gadgeturi = GadgetUtils.incarcaGadgeturi(filePath);

        // Create a VBox layout
        VBox vbox = new VBox(10); // 10 is the spacing between the elements

        // Check if gadgets are loaded and add them to the VBox
        if (gadgeturi != null) {
            for (Gadget gadget : gadgeturi) {
                // Create labels for each detail of the gadget
                Label nameLabel = new Label("Nume: " + gadget.getNume());
                Label brandLabel = new Label("Brand: " + gadget.getBrand());
                Label priceLabel = new Label("Preț: " + gadget.getPret() + " RON");
                Label descLabel = new Label("Descriere: " + gadget.getDescriere());
                Label ratingLabel = new Label("Rating: " + gadget.getRating() + " stele");
                Label stocLabel = new Label("Stoc: " + gadget.getStoc() + " bucăți");

                // Add a separator line or an empty label for spacing
                Label separator = new Label(" \n");

                // Add all labels to the VBox
                vbox.getChildren().addAll(nameLabel, brandLabel, priceLabel, descLabel, ratingLabel, stocLabel, separator);
            }
        } else {
            // Display a message if no gadgets are loaded
            vbox.getChildren().add(new Label("Nu s-au încărcat gadgeturi din fișier."));
        }

        // Wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true); // Ensure the content width fits the ScrollPane width

        // Set up the Scene and Stage
        Scene scene = new Scene(scrollPane, 400, 600);
        primaryStage.setTitle("Detalii Gadgeturi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}