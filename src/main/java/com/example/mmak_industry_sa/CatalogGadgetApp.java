package com.example.mmak_industry_sa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CatalogGadgetApp extends Application {

    private static final String FILE_PATH = "gadgeturi.ser";
    private List<Gadget> gadgeturi;

    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MMAK Industry SA");

        // Load the initial list of gadgets from file
        gadgeturi = loadInitialGadgets();

        // Create a ListView for displaying gadget names
        ListView<String> gadgetList = new ListView<>();
        for (Gadget gadget : gadgeturi) {
            gadgetList.getItems().add(gadget.getNume()); // Add the name of each gadget
        }

        // Create a container for displaying details of the selected gadget
        VBox detailsBox = new VBox(10);
        detailsBox.setStyle("-fx-padding: 10; -fx-border-color: red; -fx-border-width: 1;");

        // TextFields for editing gadget details
        TextField IDField = new TextField();
        TextField nameField = new TextField();
        TextField brandField = new TextField();
        TextField priceField = new TextField();
        TextField descField = new TextField();
        TextField ratingField = new TextField();
        TextField stockField = new TextField();

        // Button for saving modifications
        Button updateButton = new Button("Salvează modificările");

        // Event handling when a gadget is selected
        gadgetList.setOnMouseClicked(event -> {
            String selectedItem = gadgetList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int selectedIndex = gadgetList.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    Gadget gadget = gadgeturi.get(selectedIndex);

                    // Populate the edit fields with gadget details
                    IDField.setText(gadget.getId());
                    nameField.setText(gadget.getNume());
                    brandField.setText(gadget.getBrand());
                    priceField.setText(String.valueOf(gadget.getPret()));
                    descField.setText(gadget.getDescriere());
                    ratingField.setText(String.valueOf(gadget.getRating()));
                    stockField.setText(String.valueOf(gadget.getStoc()));

                    // Clear and update the VBox with the editable fields and save button
                    detailsBox.getChildren().clear();
                    detailsBox.getChildren().addAll(
                            new Label("ID: "), IDField,
                            new Label("Nume: "), nameField,
                            new Label("Brand: "), brandField,
                            new Label("Preț: "), priceField,
                            new Label("Descriere: "), descField,
                            new Label("Rating: "), ratingField,
                            new Label("Stoc: "), stockField,
                            updateButton
                    );

                    // Logic for saving modifications
                    updateButton.setOnAction(e -> {
                        gadget.setId(IDField.getText());
                        gadget.setNume(nameField.getText());
                        gadget.setBrand(brandField.getText());
                        gadget.setPret(Double.parseDouble(priceField.getText()));
                        gadget.setDescriere(descField.getText());
                        gadget.setRating(Double.parseDouble(ratingField.getText()));
                        gadget.setStoc(Integer.parseInt(stockField.getText()));

                        // Update ListView to reflect changes
                        gadgetList.getItems().set(selectedIndex, gadget.getNume());

                        // Save changes to file
                        saveChanges(gadgeturi);

                        // Optional: Confirmation
                        System.out.println("Detaliile au fost actualizate pentru: " + gadget.getNume());
                    });
                }
            }
        });

        // Button for adding a new gadget
        Button addButton = new Button("Adaugă Gadget");
        addButton.setOnAction(event -> {
            Gadget newGadget = new Gadget("New Gadget", "Brand Nou", 0, "Descriere nouă", 0.0, 0, 12, 0, Gadget.Categorie.ALTELE);
            gadgeturi.add(newGadget);
            gadgetList.getItems().add(newGadget.getNume());

            // Save changes to file
            saveChanges(gadgeturi);

            // Optionally select and display the new gadget's details
            gadgetList.getSelectionModel().selectLast();
            nameField.setText(newGadget.getNume());
            brandField.setText(newGadget.getBrand());
            priceField.setText(String.valueOf(newGadget.getPret()));
            descField.setText(newGadget.getDescriere());
            ratingField.setText(String.valueOf(newGadget.getRating()));
            stockField.setText(String.valueOf(newGadget.getStoc()));
        });

        // Main layout container for UI elements
        VBox mainLayout = new VBox(10, gadgetList, detailsBox, addButton);
        mainLayout.setStyle("-fx-padding: 20;");

        // Create and set the scene
        Scene scene = new Scene(mainLayout, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to save changes to the gadget list
    private void saveChanges(List<Gadget> gadgetList) {
        GadgetPersistence.saveGadgetsToFile(gadgetList, FILE_PATH);
    }

    // Method to load the initial list of gadgets
    private List<Gadget> loadInitialGadgets() {
        List<Gadget> loadedGadgets = GadgetPersistence.loadGadgetsFromFile(FILE_PATH);
        return (loadedGadgets != null) ? loadedGadgets : new ArrayList<>();
    }
}
