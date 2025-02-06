package it.isa.progetto;

public class Agriturismo {
    private final String nome;
    private final String indirizzo;
    private final String mail;
    private double costoNotte;
    private final int annoIn;
    
    // Costruttore
    public Agriturismo(String nome, String indirizzo, String mail, double costoNotte, int annoIn) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.mail = mail;
        if (costoNotte < 0) {
           throw new IllegalArgumentException("Il costo per notte non può essere negativo");
        }
        this.costoNotte = costoNotte;
        this.annoIn = annoIn;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getMail() {
        return mail;
    }

    public double getCostoNotte() {
        return costoNotte;
    }

    public void setCostoNotte(double costoNotte) {
        this.costoNotte = costoNotte;
    }

    public int getAnnoIn() {
        return annoIn;
    }

}
