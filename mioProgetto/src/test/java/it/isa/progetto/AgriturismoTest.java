package it.isa.progetto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.Size;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@RunWith(JUnitQuickcheck.class)
@TestInstance(Lifecycle.PER_CLASS)
public class AgriturismoTest {

    private Database db;
    private ClienteService clienteService;

    @BeforeAll
    public void setup() {
        db = new Database();
        db.popolaDatiIniziali();
        clienteService = new ClienteService();
    }

    // Unit test
    @Test
    public void testGestioneProdottiBottega() {
        // Creazione di una bottega, di prodotti e di un agriturismo
        Bottega bottega = new Bottega("Bottega del Gusto", "Via Sapori 5");
        Prodotto vino = new Prodotto("Vino Rosso", 15.0);
        Prodotto olio = new Prodotto("Olio Extra Vergine", 12.0);
        Agriturismo agriturismo = new Agriturismo("La Quiete", "Via Verde 12", "info@laquiete.it", 75.0, 2000);

        // Aggiunta delle forniture direttamente alla bottega
        bottega.aggiungiFornitura(agriturismo, vino, 10);
        bottega.aggiungiFornitura(agriturismo, olio, 20);

        // Verifica che i prodotti siano stati correttamente associati alla bottega
        Map<Agriturismo, Map<Prodotto, Integer>> forniture = bottega.getFornitureProdotti();
        assertNotNull(forniture);
        assertEquals(1, forniture.size()); // Una sola fornitura da un agriturismo
        assertTrue(forniture.containsKey(agriturismo));
        assertEquals(10, forniture.get(agriturismo).get(vino));
        assertEquals(20, forniture.get(agriturismo).get(olio));
    }

    @Test
    public void testGestionePrezzoAttivita() {
        Attivita attivita = new Attivita("Equitazione", "Passeggiata a cavallo", 20.0);

        attivita.setPrezzoAgg(30.0);
        assertEquals(30.0, attivita.getPrezzoAgg());
    }

    @Test
    public void testGestionePrezzoProdotti() {
        Prodotto prodotto = new Prodotto("Vino Rosso", 15.0);

        prodotto.setCosto(20.0);
        assertEquals(20.0, prodotto.getCosto());
    }

    @Test
    public void testCalcolaSpesa() {
        System.out.println("testCalcolaSpesa");
        Cliente cliente = new Cliente("1237898580115012", "Mario", "Rossi", 38, "M", "Via Roma 10, Padova", null, 2);
        Agriturismo agriturismo = new Agriturismo("La Quiete", "Via Verde 12", "info@laquiete.it", 75.0, 2000);
        cliente.setAgriturismo(agriturismo);
        List<Attivita> attivita = new ArrayList<>();
        
        //Test con zero notti
        assertThrows(IllegalArgumentException.class, () -> clienteService.calcolaSpesa(cliente, agriturismo, attivita, 0));

        //Test senza attività
        double spesaBase = clienteService.calcolaSpesa(cliente, agriturismo, new ArrayList<>(), 2);        
        assertEquals(450.0, spesaBase, "La spesa calcolata non è corretta");

        //Test con attività
        attivita.add(new Attivita("Equitazione", "Passeggiata a cavallo", 20.0));
        double spesaConAttivita = clienteService.calcolaSpesa(cliente, agriturismo, attivita, 2);
        assertEquals(470.0, spesaConAttivita, "La spesa calcolata non è corretta");

        //Test con attività nulla nella lista
        attivita.add(null);
        double spesa = clienteService.calcolaSpesa(cliente, agriturismo, attivita, 2);
        assertEquals(470.0, spesa, "La spesa calcolata non è corretta");
    }

    @Test
    public void testAggiungiClienteConAgriturismoNonPresente() {
        System.out.println("testAggiungiClienteConAgriturismoNonPresente");
        Cliente cliente = new Cliente("1237898580115012", "Mario", "Rossi", 38, "M", "Via Roma 10, Padova", null, 2);
        
        // Verifico che venga lanciata l'eccezione quando provo ad aggiungere il cliente con un agriturismo non presente (lista vuota)
        assertThrows(IllegalArgumentException.class, () -> clienteService.aggiungiCliente(cliente, List.of()));
    }

    @Test
    public void testTrovaCliente() {
        System.out.println("testTrovaCliente");
        String codiceInesistente = "0000000000000000";
        Agriturismo agriturismo = new Agriturismo("Le Vele", "Via Roma 10", "info@levele.com", 50.0, 2005);
        Cliente cliente = new Cliente("4897561029384756", "Nicoletta", "Verdi", 39, "F", "Via Ragno 8, Milano", agriturismo, 4);
        clienteService.aggiungiCliente(cliente, List.of(agriturismo));
        
        assertTrue(clienteService.trovaCliente("4897561029384756").isPresent());
        assertTrue(clienteService.trovaCliente(codiceInesistente).isEmpty());

    }

    @Test
    public void testEliminaCliente() {
        System.out.println("testEliminaCliente");
        String codiceInesistente = "0000000000000000";
        Agriturismo agriturismo = new Agriturismo("Le Vele", "Via Roma 10", "info@levele.com", 50.0, 2005);
        Cliente cliente = new Cliente("4897561029384756", "Nicoletta", "Verdi", 39, "F", "Via Ragno 8, Milano", agriturismo, 4);
        clienteService.aggiungiCliente(cliente, List.of(agriturismo));
        
        clienteService.eliminaCliente("4897561029384756");       
        assertFalse(clienteService.trovaCliente("4897561029384756").isPresent(), "Il cliente non è stato eliminato correttamente");
        assertEquals(0, clienteService.getElencoClienti().size(), "La lista clienti dovrebbe essere vuota dopo l'eliminazione");
        assertDoesNotThrow(() -> clienteService.eliminaCliente(codiceInesistente));
    }


    // Property-based test
    @Property
    public void testSpesaTotalePositiva(@InRange(min = "1", max = "30") int numNotti, 
                                        @From(AgriturismoGenerator.class) Agriturismo agriturismo, 
                                        @From(ClienteGenerator.class) Cliente cliente,
                                        @Size(min = 0, max = 5) List<@From(AttivitaGenerator.class) Attivita> attivita) {
    cliente.setAgriturismo(agriturismo);
    ClienteService clienteService = new ClienteService();
    double spesa = clienteService.calcolaSpesa(cliente, agriturismo, attivita, numNotti);
    assertTrue(spesa >= 0.0, "La spesa totale non può essere negativa");

    // Verifica che la spesa aumenta con i servizi extra
    if (!attivita.isEmpty()) {
        double spesaSenzaAttivita = clienteService.calcolaSpesa(cliente, agriturismo, List.of(), numNotti);
        assertTrue(spesa > spesaSenzaAttivita, "La spesa con attivita deve essere maggiore della spesa base");
        }
    }

    //Il prezzo per notte deve sempre essere non negativo
    @Property
    public void testPrezzoNotteNonNegativo(@InRange(min = "-1000.0", max = "10000.0") double costoNotte) {
        if (costoNotte < 0)  {
           assertThrows(IllegalArgumentException.class, () -> { 
           new Agriturismo("Nome", "Via Test", "nome@test.com", costoNotte, 2020);
        });
       } else {
           Agriturismo agriturismo = new Agriturismo("Nome", "Via Test", "nome@test.com", costoNotte, 2020);
           assertEquals(costoNotte, agriturismo.getCostoNotte());
       }
    } 

    //Il cliente prenotante deve essere maggiorenne
    @Property
    public void testValidazioneEtaCliente(@InRange(min = "-120", max = "120") int eta) {
        if (eta < 18) {
           assertThrows(IllegalArgumentException.class, () -> { 
           new Cliente("7418521234567845", "Nome", "Cognome", eta, "M", "Via Test 1", null, 2);
        });
       } else {
           Cliente cliente = new Cliente("7418521234567845", "Nome", "Cognome", eta, "M", "Via Test 1", null, 2);
           assertEquals(eta, cliente.getEta());
       }
    }
    
    //Il codice fiscale deve essere lungo 16 cifre (per semplicità)
    @Property
    public void testCodiceFiscaleLunghezza(@InRange(min = "0", max = "30") int lunghezza) {
        String codiceFiscale = "0".repeat(lunghezza);
   
        if (lunghezza != 16) {
           assertThrows(IllegalArgumentException.class, () -> {
           new Cliente(codiceFiscale, "Mario", "Rossi", 30, "M", "Via Roma 10", null, 2);
        });
       } else {
           Cliente cliente = new Cliente(codiceFiscale, "Mario", "Rossi", 30, "M", "Via Roma 10", null, 2);
           assertEquals(16, cliente.getCodFisc().length(), "Il codice fiscale deve avere esattamente 16 caratteri!");
       }
    }

    
}
