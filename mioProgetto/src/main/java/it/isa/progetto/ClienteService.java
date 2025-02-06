package it.isa.progetto;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ClienteService {

    private List<Cliente> clienti;

    public ClienteService() {
        this.clienti = new ArrayList<>();
    }

    // Metodo per aggiungere un nuovo cliente
    public void aggiungiCliente(Cliente cliente, List<Agriturismo> agriturismi) {
        boolean agriturismoEsiste = agriturismi.stream()
                .anyMatch(ag -> ag.getNome().equals(cliente.getAgriturismo().getNome()));

        if (agriturismoEsiste) {
            clienti.add(cliente);
        } else {
            throw new IllegalArgumentException("L'agriturismo specificato non esiste!");
        }
    }

    // Metodo per ottenere un cliente in base al codice fiscale
    public Optional<Cliente> trovaCliente(String codFisc) {
        return clienti.stream()
                .filter(c -> c.getCodFisc().equals(codFisc))
                .findFirst();
    }

    public double calcolaSpesa(Cliente cliente, Agriturismo agriturismo, List<Attivita> attivitaScelte, int numeroNotti) { 
       if (numeroNotti == 0) {
          throw new IllegalArgumentException("Occorre inserire almeno una notte");
    }

    // Costo totale notti per cliente e familiari 
    double costoNotti = agriturismo.getCostoNotte() * (cliente.getNumFamiliari() + 1) * numeroNotti; 
    
    // Costo totale delle attività scelte 
    double costoAttivita = attivitaScelte.stream() 
       .filter(att -> att != null) 
       .mapToDouble(Attivita::getPrezzoAgg) 
       .sum(); 

    // Costo totale (notti + attività) 
    return costoNotti + costoAttivita; 

    }

    // Metodo per eliminare un cliente
    public void eliminaCliente(String codFisc) {
        clienti.removeIf(c -> c.getCodFisc().equals(codFisc));
    }

    public List<Cliente> getElencoClienti() {
        return new ArrayList<>(clienti);
    }
}
