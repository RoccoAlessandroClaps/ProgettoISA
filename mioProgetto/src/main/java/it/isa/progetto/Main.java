package it.isa.progetto;

public class Main {
    public static void main(String[] args) {
        // Inizializzazione del sistema
        Database db = new Database();
        db.popolaDatiIniziali();
        ClienteService clienteService = new ClienteService();

        db.getClienti().forEach(cliente -> { clienteService.aggiungiCliente(cliente, db.getAgriturismi()); });

        // Avvio dell'interfaccia utente
        MenuManager menu = new MenuManager(clienteService, db);
        menu.start();
    }
}
