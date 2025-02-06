# ProgettoISA

# Sistema gestione agriturismi

Questo progetto gestisce un sistema di prenotazioni per agriturismi, offrendo funzionalità per la gestione di clienti, soggiorni, attività ricreative e prodotti tipici.

## Funzionalità principali

- Gestione agriturismi: creazione, modifica e gestione di agriturismi e delle relative attività.
- Gestione clienti e prenotazioni: registrazione, ricerca, eliminazione clienti e gestione delle prenotazioni.
- Calcolo delle spese: generazione di preventivi basati su numero di notti, attività scelte e componenti familiari.
- Tracciamento prodotti locali: monitoraggio della fornitura di prodotti dagli agriturismi alle botteghe vicine.
- Interfaccia interattiva: menu guidato per l'inserimento e la gestione dei dati.

## Struttura del progetto

- src/main/java/it/isa/progetto/: Codice sorgente del sistema.
- src/test/java/it/isa/progetto/: Test unitari, di integrazione e property-based.
- pom.xml: Configurazione Maven per dipendenze e build.
- Dockerfile: Configurazione per l’esecuzione in container.
- bitbucket-pipelines.yml: Pipeline CI/CD per build, test e deploy su DockerHub.

## Tecnologie utilizzate

- Java (linguaggio di programmazione)
- Maven (gestione dipendenze e build)
- JUnit + QuickCheck (test unitari, property-based e di integrazione)
- JaCoCo (copertura del codice)
- GitHub & BitBucket (versionamento e gestione del codice)
- Docker (containerizzazione e deploy)
- BitBucket Pipelines (integrazione e deployment automatico)
