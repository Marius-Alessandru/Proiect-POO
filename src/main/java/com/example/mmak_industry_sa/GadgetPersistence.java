package com.example.mmak_industry_sa;

import java.io.*;
import java.util.List;


public class GadgetPersistence {

    // Method to save the list of gadgets to a file
    public static void saveGadgetsToFile(List<Gadget> gadgetList, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gadgetList);
            System.out.println("Gadget list successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving gadget list: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to load the list of gadgets from a file
    @SuppressWarnings("unchecked")
    public static List<Gadget> loadGadgetsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Gadget>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading gadget list: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
