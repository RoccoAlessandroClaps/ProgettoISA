package it.isa.progetto;

import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class ClienteGenerator extends Generator<Cliente> {
    public ClienteGenerator() {
        super(Cliente.class);
    }

    @Override
    public Cliente generate(SourceOfRandomness random, GenerationStatus status) {
        String cognome = "Cognome" + random.nextChar('A', 'Z');
        String nome = "Nome" + random.nextChar('A', 'Z');
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
             sb.append(random.nextChar('0', '9'));
        }
        String codiceFiscale = sb.toString();
        int eta = random.nextInt(18,100); 
        String sesso = random.nextBoolean() ? "M" : "F";
        String indirizzo = "Via " + random.nextChar('A', 'Z') + " " + random.nextInt(1, 999);
        int numFamiliari = random.nextInt(0,10);

        // Agriturismo verrà associato nel test
        return new Cliente(codiceFiscale, nome, cognome, eta, sesso, indirizzo, null, numFamiliari);
    }
}

