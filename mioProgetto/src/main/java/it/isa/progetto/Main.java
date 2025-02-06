package it.isa.progetto;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Creazione dei dati iniziali
        Database db = new Database();
        db.popolaDatiIniziali();

        // Recupero liste dal database
        List<Agriturismo> agriturismi = db.getAgriturismi();
        List<Cliente> clienti = db.getClienti();
        List<Attivita> attivitaDisponibili = db.getAttivita();
        List<Prodotto> prodotti = db.getProdotti();
        List<Bottega> botteghe = db.getBotteghe();


        // Simulazione del servizio clienti
        ClienteService clienteService = new ClienteService();

        // Aggiunta dei clienti
        db.getClienti().forEach( cliente -> {
            try {
                clienteService.aggiungiCliente(cliente, db.getAgriturismi());
            } catch (IllegalArgumentException e) {
                System.err.println("Errore nell'aggiunta del cliente: " + e.getMessage());
            }
        });
  
        // Recupero del cliente
        Optional<Cliente> trovato = clienteService.trovaCliente("VRDNCL1029384756");
        trovato.ifPresent(c -> System.out.println("Cliente trovato: " + c));
        System.out.println();
        System.out.println();

        //Calcolo della spesa per un cliente
        Cliente clienteDaCalcolare = clienti.get(1); // Mario Rossi
        Agriturismo agriturismoAssociato = clienteDaCalcolare.getAgriturismo();
        List<Attivita> attivitaScelte = attivitaDisponibili.subList(0, 2); // Equitazione, Cucina Tipica
        int numeroNotti = 3;

        double spesa = clienteService.calcolaSpesa(clienteDaCalcolare, agriturismoAssociato, attivitaScelte, numeroNotti);

        // Stampa del risultato 
        System.out.println("Spesa cliente: " + clienteDaCalcolare); 
        System.out.println("Agriturismo: " + agriturismoAssociato); 
        System.out.println("Totale: " + spesa + " EUR");  

    }

}
