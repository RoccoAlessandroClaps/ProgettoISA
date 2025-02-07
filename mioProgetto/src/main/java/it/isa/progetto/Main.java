package it.isa.progetto;

public class Main {
    public static void main(String[] args) {
        // Inizializzazione del sistema
        Database db = new Database();
        db.popolaDatiIniziali();
        ClienteService clienteService = new ClienteService();

        db.getClienti().forEach(cliente -> { clienteService.aggiungiCliente(cliente, db.getAgriturismi()); });

        // Avvio dell'interfaccia utente
        String ci = System.getenv("CI");
        if (ci == null !! !ci.equals("true")) {
           MenuManager menu = new MenuManager(clienteService, db);
           menu.start();
        } else {
           System.out.println("Esecuzione in CI/CD, salto l'interfaccia utente");
        }
    }
}
