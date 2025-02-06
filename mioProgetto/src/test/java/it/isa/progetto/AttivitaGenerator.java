package it.isa.progetto;

import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class AttivitaGenerator extends Generator<Attivita> {
    public AttivitaGenerator() {
        super(Attivita.class);
    }

    @Override
    public Attivita generate(SourceOfRandomness random, GenerationStatus status) {
        String nome = "Attivita" + random.nextChar('A', 'Z');
        String descrizione = "Descrizione attivita " + nome;
        double prezzo = random.nextDouble(10.0, 100.0);

        return new Attivita(nome, descrizione, prezzo);
    }
}

