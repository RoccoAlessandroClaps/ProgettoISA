package it.isa.progetto;

public class Prodotto {
    private final String nome;
    private double costo;

    // Costruttore
    public Prodotto(String nome, double costo) {
        this.nome = nome;
        this.costo = costo;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
