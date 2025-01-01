package com.botola.apicmi.dto;

public class FactureDTO {

    private Long id;
    private double montant;
    private boolean payee;

    // Constructeur
    public FactureDTO(Long id, double montant, boolean payee) {
        this.id = id;
        this.montant = montant;
        this.payee = payee;
    }

    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour montant
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    // Getter et Setter pour payee
    public boolean isPayee() {
        return payee;
    }

    public void setPayee(boolean payee) {
        this.payee = payee;
    }
}
