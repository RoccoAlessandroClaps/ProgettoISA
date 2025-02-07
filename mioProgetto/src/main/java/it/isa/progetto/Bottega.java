package it.isa.progetto;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Bottega {
    private final String nome;
    private final String indirizzo;
    
    private Map<Agriturismo, Map<Prodotto, Integer>> fornitureProdotti; 

    // Costruttore
    public Bottega(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.fornitureProdotti = new HashMap<>();
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    // Metodo per aggiungere forniture specifiche per agriturismo
    public void aggiungiFornitura(Agriturismo agriturismo, Prodotto prodotto, int quantita) {
        fornitureProdotti.computeIfAbsent(agriturismo, k -> new HashMap<>())
                        .put(prodotto, quantita);
    }

    public void stampaProdottiForniti() {
        for (Map.Entry<Agriturismo, Map<Prodotto, Integer>> entry : fornitureProdotti.entrySet()) {
            Agriturismo agriturismo = entry.getKey();
            Map<Prodotto, Integer> prodotti = entry.getValue();
            
            System.out.println("Provenienza: agriturismo " + agriturismo);
            for (Map.Entry<Prodotto, Integer> prodEntry : prodotti.entrySet()) {
                System.out.println("- Prodotto: " + prodEntry.getKey().getNome()
                               + " | Prezzo: " + prodEntry.getKey().getCosto() + " EUR"
                               + " | Quantità: " + prodEntry.getValue());
            }
            System.out.println();
        }
    }

    // Getter per le forniture
    public Map<Agriturismo, Map<Prodotto, Integer>> getFornitureProdotti() {
        return new HashMap<>(fornitureProdotti);
    }

    //**Nuovo metodo per calcolare la spesa totale dei prodotti**
    public double calcolaSpesaTotaleProdotti() {
        return fornitureProdotti.entrySet().stream()
            .flatMap(entry -> entry.getValue().entrySet().stream())
            .mapToDouble(prodEntry -> prodEntry.getKey().getCosto() * prodEntry.getValue())
            .sum();
    }


}
