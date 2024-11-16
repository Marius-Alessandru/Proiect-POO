package com.example.mmak_industry_sa;

import java.io.Serializable;
import java.util.UUID;

public class Gadget implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String id; // ID unic pentru fiecare gadget
    private String nume;
    private String brand;
    private double pret;
    private String descriere;
    private double rating; // rating între 0 și 5
    private int stoc;
    private int garantie; // în luni
    private double discount; // discount ca procent (ex: 10 pentru 10%)
    private Categorie categorie; // enum pentru tipul de gadget

    // Enum pentru categorii de gadgeturi
    public enum Categorie {
        DRONA, PRIZA_SMART, ALRMA_SMART, SMARTWATCH, ACCESORIU, ALTELE
    }

    // Constructor complet
    public Gadget(String nume, String brand, double pret, String descriere, double rating, int stoc,
                  int garantie, double discount, Categorie categorie) {
        this.id = UUID.randomUUID().toString(); // Generează un ID unic
        this.nume = nume;
        this.brand = brand;
        this.pret = pret;
        this.descriere = descriere;
        setRating(rating); // folosim setter pentru validare
        this.stoc = stoc;
        this.garantie = garantie;
        this.discount = discount;
        this.categorie = categorie;
    }

    // Getters și Setters
    public String getId() {
        return id;
    }
    public String setId(String id) {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        if (pret < 0) {
            throw new IllegalArgumentException("Prețul nu poate fi negativ.");
        }
        this.pret = pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Ratingul trebuie să fie între 0 și 5.");
        }
        this.rating = rating;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        if (stoc < 0) {
            throw new IllegalArgumentException("Stocul nu poate fi negativ.");
        }
        this.stoc = stoc;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discountul trebuie să fie între 0% și 100%.");
        }
        this.discount = discount;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    // Metodă pentru a afișa rezumatul gadgetului (folosită în ListView)
    @Override
    public String toString() {
        return nume; // Returnează numele pentru afișarea simplificată în ListView
    }

    // Alte metode auxiliare...
    public boolean esteInStoc() {
        return stoc > 0;
    }

    public double calculeazaPretCuDiscount() {
        return pret - (pret * discount / 100);
    }

    public void vinde(int cantitate) {
        if (cantitate > stoc) {
            throw new IllegalArgumentException("Nu există suficient stoc.");
        }
        stoc -= cantitate;
    }

    public void adaugaStoc(int cantitate) {
        if (cantitate < 0) {
            throw new IllegalArgumentException("Cantitatea trebuie să fie pozitivă.");
        }
        stoc += cantitate;
    }

    public void afiseazaDetalii() {
        System.out.println("ID: " + id);
        System.out.println("Nume: " + nume);
        System.out.println("Brand: " + brand);
        System.out.println("Preț (fără discount): " + pret + " lei");
        System.out.println("Preț (cu discount): " + calculeazaPretCuDiscount() + " lei");
        System.out.println("Descriere: " + descriere);
        System.out.println("Rating: " + rating + " / 5");
        System.out.println("Stoc: " + (esteInStoc() ? "Disponibil" : "Indisponibil"));
        System.out.println("Garanție: " + garantie + " luni");
        System.out.println("Categorie: " + categorie);
    }
}
