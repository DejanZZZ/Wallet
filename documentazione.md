# Documentazione progetto Balinzo, Cardellini, Durmishi

### Introduzione: 
L'applicazione consente agli utenti di una ipotetica banca, di iscriversi, accedere e gestire il proprio conto bancario. Le comunicazioni tra Server e Client avvengono tramite il protocollo TCP, utilizzando le socket per lo scambio dei messaggi. 

## Come far partire il programma: 
1. Avviare il server, dalla classe Server, sulla console di Eclipse;
2. Avviare uno o più client, dalla classe Client, sulla console di Eclipse;
3. Inserire i dati 

### Funzionamento: 
Una volta avviato il server, e il client è riuscito a connettersi correttamente con il server, l'utente dovrà scegliere tra:
1. Registrazione: Un nuovo utente deve fornire username (univoco) e password. Dopodichè verrà interrotta la connessione.
2. Accedi: L'utente dovrà fornire username e password per accedere al loro "account" con le credenziali date in fase di registrazione. Una volta fatta la registrazione, e le credenziale inserite sono corrette, l'utente potrà scegliere tra altre due opzioni: 
    1. Preleva: preleva un quantitativo di soldi che l'utente desidera. Se l'utente preleva una quantità maggiore di quella effettiva sul suo conto, l'operazione non verrà eseguita. 
    2. Deposita: deposita una somma di denaro scelta dall'utente. 
