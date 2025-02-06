package it.isa.progetto;

public class Bottega {
    private final String nome;
    private final String indirizzo;

    // Costruttore
    public Bottega(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

}
