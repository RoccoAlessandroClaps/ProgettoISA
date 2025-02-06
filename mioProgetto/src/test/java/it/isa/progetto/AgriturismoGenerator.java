package it.isa.progetto;

import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class AgriturismoGenerator extends Generator<Agriturismo> {
    public AgriturismoGenerator() {
        super(Agriturismo.class);
    }

    @Override
    public Agriturismo generate(SourceOfRandomness random, GenerationStatus status) {
        String nome = "Agriturismo" + random.nextChar('A', 'Z');
        String indirizzo = "Via " + random.nextChar('A', 'Z') + " " + random.nextInt(1, 999);
        String email = "info@" + nome.toLowerCase() + ".com";
        double prezzoBase = random.nextDouble(0.0, 1000.0);
        int annoApertura = random.nextInt(1945, 2025);

        return new Agriturismo(nome, indirizzo, email, prezzoBase, annoApertura);
    }
}

