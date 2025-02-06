package it.isa.progetto;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

public class MenuManager {
    private final Scanner scanner;
    private final ClienteService clienteService;
    private final Database database;

    public MenuManager(ClienteService clienteService, Database database) {
        this.scanner = new Scanner(System.in);
        this.clienteService = clienteService;
        this.database = database;
    }

    public void start() {
        boolean continua = true;
        while (continua) {
            stampaMenuPrincipale();
            int scelta = leggiIntero("Seleziona un'opzione: ");
           
            switch (scelta) {
                case 1:
                    registrazioneCliente();
                    break;
                case 2:
                    ricercaCliente();
                    break;
                case 3:
                    calcolaPreventivo();
                    break;
                case 4:
                    visualizzaProdottiBottega();
                    break;
                case 5:
                    visualizzaAgriturismi();
                    break;
                case 6:
                    eliminaCliente();
                    break;
                case 7:
                    visualizzaClienti();
                    break;
                case 0:
                    continua = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida!");
            }
        }
        scanner.close();
    }

    public void stampaMenuPrincipale() {
        System.out.println("\n=== GESTIONE AGRITURISMI ===");
        System.out.println("1. Registra nuovo cliente");
        System.out.println("2. Cerca cliente");
        System.out.println("3. Calcola preventivo soggiorno");
        System.out.println("4. Visualizza prodotti bottega");
        System.out.println("5. Visualizza agriturismi");
        System.out.println("6. Elimina cliente");
        System.out.println("7. Visualizza clienti");
        System.out.println("0. Esci");
    }

    public void registrazioneCliente() {
        System.out.println("\n=== REGISTRAZIONE NUOVO CLIENTE ===");
        String codFisc = leggiStringa("Codice Fiscale: ");
        String nome = leggiStringa("Nome: ");
        String cognome = leggiStringa("Cognome: ");
        int eta = leggiIntero("Età: ");
        String sesso = leggiStringa("Sesso (M/F): ");
        String indirizzo = leggiStringa("Indirizzo: ");
        int numFamiliari = leggiIntero("Numero familiari: ");

        visualizzaAgriturismi();
        String nomeAgriturismo = leggiStringa("Nome agriturismo scelto: ");
       
        Optional<Agriturismo> agriturismo = database.getAgriturismi().stream()
            .filter(a -> a.getNome().equalsIgnoreCase(nomeAgriturismo))
            .findFirst();

        if (agriturismo.isPresent()) {
            try {
                Cliente nuovoCliente = new Cliente(codFisc, nome, cognome, eta, sesso,
                                                 indirizzo, agriturismo.get(), numFamiliari);
                clienteService.aggiungiCliente(nuovoCliente, database.getAgriturismi());
                System.out.println("Cliente registrato con successo!");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore nella registrazione: " + e.getMessage());
            }
        } else {
            System.out.println("Agriturismo non trovato!");
        }
    }

    public void ricercaCliente() {
        System.out.println("\n=== RICERCA CLIENTE ===");
        String codFisc = leggiStringa("Inserisci il codice fiscale: ");
        Optional<Cliente> cliente = clienteService.trovaCliente(codFisc);
       
        if (cliente.isPresent()) {
            System.out.println("Cliente trovato:");
            System.out.println(cliente.get());
        } else {
            System.out.println("Cliente non trovato!");
        }
    }

    public void calcolaPreventivo() {
        System.out.println("\n=== CALCOLO PREVENTIVO ===");
        String codFisc = leggiStringa("Codice Fiscale cliente: ");
        Optional<Cliente> cliente = clienteService.trovaCliente(codFisc);
       
        if (cliente.isPresent()) {
            int numeroNotti = leggiIntero("Numero di notti: ");
            List<Attivita> attivitaDisponibili = database.getAttivita();
            List<Attivita> attivitaScelte = new ArrayList<>();
           
            System.out.println("\nAttività disponibili:");
            for (int i = 0; i < attivitaDisponibili.size(); i++) {
                System.out.println((i+1) + ". " + attivitaDisponibili.get(i).getNome() +
                                 " - " + attivitaDisponibili.get(i).getPrezzoAgg() + " EUR");
            }
           
            while (true) {
                int scelta = leggiIntero("\nScegli un'attività (0 per terminare): ");
                if (scelta == 0) break;
                if (scelta > 0 && scelta <= attivitaDisponibili.size()) {
                    attivitaScelte.add(attivitaDisponibili.get(scelta-1));
                }
            }
           
            double spesa = clienteService.calcolaSpesa(cliente.get(),
                                                      cliente.get().getAgriturismo(),
                                                      attivitaScelte,
                                                      numeroNotti);
           
            System.out.println("\nPreventivo per " + cliente.get().getNome() +
                             " " + cliente.get().getCognome());
            System.out.println("Totale: " + spesa + " EUR");
        } else {
            System.out.println("Cliente non trovato!");
        }
    }

    public void visualizzaProdottiBottega() {
        System.out.println("\n=== PRODOTTI BOTTEGA ===");
        List<Bottega> botteghe = database.getBotteghe();
        
        if (botteghe.isEmpty()) {
            System.out.println("Nessuna bottega presente nel sistema!");
            return;
        }

        System.out.println("Botteghe disponibili:");
        for (int i = 0; i < botteghe.size(); i++) {
            System.out.println((i+1) + ". " + botteghe.get(i).getNome() + 
                             " (" + botteghe.get(i).getIndirizzo() + ")");
        }

        int scelta = leggiIntero("\nSeleziona il numero della bottega: ");
        if (scelta > 0 && scelta <= botteghe.size()) {
            Bottega bottegaScelta = botteghe.get(scelta-1);
            System.out.println("\nProdotti forniti alla bottega " + bottegaScelta.getNome() + " (" + bottegaScelta.getIndirizzo() + "):");
            bottegaScelta.stampaProdottiForniti();
        } else {
            System.out.println("Scelta non valida!");
        }
    }

    public void visualizzaAgriturismi() {
        System.out.println("\n=== ELENCO AGRITURISMI ===");
        List<Agriturismo> agriturismi = database.getAgriturismi();
        for (Agriturismo agriturismo : agriturismi) {
            System.out.println("- " + agriturismo.getNome() +
                             " (" + agriturismo.getIndirizzo() + ")" +
                             " - " + agriturismo.getCostoNotte() + " EUR/notte");
        }
    }

    public void eliminaCliente() {
        System.out.println("\n=== ELIMINAZIONE CLIENTE ===");
        String codFisc = leggiStringa("Inserisci il codice fiscale del cliente da eliminare: ");
       
        Optional<Cliente> cliente = clienteService.trovaCliente(codFisc);
        if (cliente.isPresent()) {
            System.out.println("Cliente trovato: " + cliente.get());
            String conferma = leggiStringa("Sei sicuro di voler eliminare questo cliente? (s/n): ");
           
            if (conferma.equalsIgnoreCase("s")) {
                clienteService.eliminaCliente(codFisc);
                System.out.println("Cliente eliminato con successo!");
            } else {
                System.out.println("Operazione annullata.");
            }
        } else {
            System.out.println("Cliente non trovato!");
        }
    }

    
    public void visualizzaClienti() {
        System.out.println("\n=== CLIENTI PRENOTANTI ===");
        Map<Agriturismo, List<Cliente>> clientiPerAgriturismo = new HashMap<>();
        
        for (Cliente cliente : clienteService.getElencoClienti()) {
            Agriturismo agriturismo = cliente.getAgriturismo();
            clientiPerAgriturismo
                .computeIfAbsent(agriturismo, k -> new ArrayList<>())
                .add(cliente);
        }
        
        if (clientiPerAgriturismo.isEmpty()) {
            System.out.println("Nessun cliente prenotante nel sistema.");
            return;
        }

        // Visualizza i clienti raggruppati per agriturismo
        for (Map.Entry<Agriturismo, List<Cliente>> entry : clientiPerAgriturismo.entrySet()) {
            Agriturismo agriturismo = entry.getKey();
            List<Cliente> clienti = entry.getValue();
            
            System.out.println("\nAgriturismo: " + agriturismo.getNome());
            System.out.println("Numero di prenotanti: " + clienti.size());
            System.out.println("Elenco clienti:");
            
            for (Cliente cliente : clienti) {
                System.out.println("- " + cliente.getNome() + " " + cliente.getCognome() + 
                                 " (Codice Fiscale: " + cliente.getCodFisc() + ")" +
                                 " - Familiari: " + cliente.getNumFamiliari());
            }
        }
    }

    public String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return scanner.nextLine().trim();
    }

    public int leggiIntero(String messaggio) {
        while (true) {
            try {
                System.out.print(messaggio);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Inserire un numero valido!");
            }
        }
    }
}


