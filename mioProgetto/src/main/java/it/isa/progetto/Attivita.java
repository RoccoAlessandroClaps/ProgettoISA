package it.isa.progetto;

public class Attivita {
    private final String nome;
    private final String descrizione;
    private double prezzoAgg;

    // Costruttore
    public Attivita(String nome, String descrizione, double prezzoAgg) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzoAgg = prezzoAgg;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public double getPrezzoAgg() {
        return prezzoAgg;
    }

    public void setPrezzoAgg(double prezzoAgg) {
        this.prezzoAgg = prezzoAgg;
    }
}
