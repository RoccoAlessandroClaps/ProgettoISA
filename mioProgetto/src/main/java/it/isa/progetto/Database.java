package it.isa.progetto;

import java.util.List;
import java.util.ArrayList;

public class Database {
    private List<Agriturismo> agriturismi = new ArrayList<>();
    private List<Cliente> clienti = new ArrayList<>();
    private List<Attivita> attivita = new ArrayList<>();
    private List<Prodotto> prodotti = new ArrayList<>();
    private List<Bottega> botteghe = new ArrayList<>();

    public List<Agriturismo> getAgriturismi() {
        return agriturismi;
    }

    public List<Cliente> getClienti() {
        return clienti;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public List<Bottega> getBotteghe() {
        return botteghe;
    }

    // Metodi per popolare le liste
    public void popolaDatiIniziali() {
        agriturismi.add(new Agriturismo("Le Vele", "Via Cavour 10", "info@levele.com", 50.0, 2005));
        agriturismi.add(new Agriturismo("La Quiete", "Via Verde 12", "info@laquiete.it", 75.0, 2000));

        clienti.add(new Cliente("4897561029384756", "Nicoletta", "Verdi", 39, "F", "Via Ragno 8, Milano", agriturismi.get(0), 4));
        clienti.add(new Cliente("1237898580115012", "Mario", "Rossi", 38, "M", "Via Roma 10, Padova", agriturismi.get(1), 2));

        attivita.add(new Attivita("Equitazione", "Passeggiata a cavallo", 20.0));
        attivita.add(new Attivita("Cucina Tipica", "Corso di cucina tradizionale", 30.0));
        attivita.add(new Attivita("Escursione", "Escursione guidata in montagna", 15.0));

        prodotti.add(new Prodotto("Vino Rosso", 15.0));
        prodotti.add(new Prodotto("Miele Artigianale", 10.0));
        prodotti.add(new Prodotto("Olio Extra Vergine", 12.0));

        botteghe.add(new Bottega("Bottega del Gusto", "Via Sapori 5"));
        botteghe.add(new Bottega("La Tradizione", "Via Antica 7"));

    }
}
