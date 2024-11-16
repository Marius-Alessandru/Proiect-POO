package com.example.mmak_industry_sa;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.List;

public class GadgetUtils {
    // Metodă pentru salvarea listei de gadgeturi într-un fișier
    public static void salveazaGadgeturi(List<Gadget> gadgeturi, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(gadgeturi);
            System.out.println("Lista de gadgeturi a fost salvată cu succes.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodă pentru încărcarea listei de gadgeturi dintr-un fișier
    @SuppressWarnings("unchecked")
    public static List<Gadget> incarcaGadgeturi(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Gadget>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}


