package it.isa.progetto;

public class Cliente {
    private final String codFisc;
    private final String nome;
    private final String cognome;
    private final int eta;
    private final String sesso;
    private final String indirizzo;
    private Agriturismo agriturismo;
    private final int numFamiliari;

    // Costruttore
    public Cliente(String codFisc, String nome, String cognome, int eta, String sesso, String indirizzo, Agriturismo agriturismo, int numFamiliari) {
        if (codFisc.length() != 16) {
           throw new IllegalArgumentException("Il codice fiscale deve essere lungo 16 cifre");
        }
        this.codFisc = codFisc;
        this.nome = nome;
        this.cognome = cognome;
        if (eta < 18) {
           throw new IllegalArgumentException("Il cliente deve essere maggiorenne");
        }
        this.eta = eta;
        this.sesso = sesso;
        this.indirizzo = indirizzo;
        this.agriturismo = agriturismo;
        this.numFamiliari = numFamiliari;
    }

    // Getter e Setter
    public String getCodFisc() {
        return codFisc;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public String getSesso() {
        return sesso;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Agriturismo getAgriturismo() {
        return agriturismo;
    }

    public void setAgriturismo(Agriturismo agriturismo) {
        this.agriturismo = agriturismo;
    }

    public int getNumFamiliari() {
        return numFamiliari;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + ", " + eta + " anni, " + indirizzo;
    }
}
