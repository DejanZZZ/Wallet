package wallet;
 
 
import java.io.*;
import java.net.*;
 
class Handler implements Runnable {
    private final Socket clientSocket;
    private final Register r;
 
    public Handler(Socket clientSocket, Register r) {
        this.clientSocket = clientSocket;
        this.r = r;
    }
 
    @Override
    public void run() {
        int saldo = 0;
        int saldoRichiesto;
 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
 
            sendMenu(out);
 
            String username = in.readLine();
            String password = in.readLine();
            String operation = in.readLine();
 
            if (username != null && password != null) {
                out.println("Credenziali ricevute: Username = " + username + ", Password = " + password);
 
                if ("1".equals(operation)) {
                    if (r.CheckUser(username)) {
                        r.addUser(username, password);
                        r.usersList();
                        out.println("");
                        System.out.println(username + " si è iscritto adesso");
                    } else {
                        System.out.println("L'Username che ha inserito è già in uso.");
                        out.println("Errore: Username già in uso.");
                        r.usersList();
                    }
                } else if ("2".equals(operation)) {
                    if (r.CheckPassword(username, password)) {
                        System.out.println(username + " si è connesso.");
                        out.println("");
 
                        sendOptions(out);
                        out.println("Inserisci l'opzione da selezionare:");
 
                        String choice = in.readLine();
                        if ("1".equals(choice)) {
                            saldo = r.GetSaldo(username);
                            out.println("Il tuo saldo è: " + saldo + "$. Inserisci la somma da prelevare (se inserisci una somma maggiore al tuo saldo l'operazione non sarà effettuata):");
 
                            String amount = in.readLine();
                            saldoRichiesto = r.cast(amount);
                            if (saldo > saldoRichiesto) {
                                System.out.println(username + " ha prelevato: " + amount + "$");
                                r.Preleva(username, saldoRichiesto);
                            } else {
                                System.out.println(username + " non ha potuto prelevare.");
                            }
                        } else if ("2".equals(choice)) {
                            saldo = r.GetSaldo(username);
                            out.println("Il tuo saldo è: " + saldo + "$. Inserisci la somma da depositare:");
 
                            String amount = in.readLine();
                            System.out.println(username + " ha depositato: " + amount + "$");
                            r.Deposita(username, r.cast(amount));
                        } else {
                            out.println("Opzione inesistente.");
                            System.out.println("Opzione sbagliata.");
                        }
                    } else {
                        System.out.println(username + " ha sbagliato l'Username o la Password.");
                        out.println("Password o Username sbagliata.");
                    }
                } else {
                    System.out.println(username + " ha selezionato un'opzione inesistente.");
                    out.println("L'operazione che hai selezionato non esiste.");
                }
            } else {
                out.println("Errore: credenziali non valide.");
            }
        } catch (IOException e) {
            System.err.println("Errore durante la comunicazione con il client: " + e.getMessage());
        }
    }
 
    private void sendMenu(PrintWriter out) {
        out.println("Scegli l'operazione da inviare: 1) Iscriviti 2) Accedi ");
    }
 
    private void sendOptions(PrintWriter out) {
        out.println("Scegli l'opzione: 1) Preleva 2) Deposita");
    }
}