package wallet_c;
 
import java.io.*;
import java.net.*;
 
public class Client {
    public static void main(String[] args) {
        String serverAddress = "192.168.0.61";
        int port = 12345;
 
        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
 
            System.out.println("Connesso al server su " + serverAddress + ":" + port);
            String menu = in.readLine();
            System.out.println(menu);
            System.out.print("Inserisci l'operazione: ");
            String operation = stdIn.readLine();
 
            // Inserisci username e password
            System.out.print("Inserisci username: ");
            String username = stdIn.readLine();
            System.out.print("Inserisci password: ");
            String password = stdIn.readLine();
 
            // Invia i dati al server
            out.println(username);
            out.println(password);
            out.println(operation);
 
            // Riceve la risposta dal server
            String response = in.readLine();
            ControlMessage(response);
            System.out.println("Risposta dal server: " + response);
            String error = in.readLine();
            ControlMessage(error);
            System.out.println(error);
            if("2".equals(operation)) {
            	String OptionsMenu = in.readLine();
            	System.out.println(OptionsMenu);
            	//System.out.print("Inserisci l'opzione: ");
            	String insertOption = in.readLine();
            	ControlMessage(insertOption);
            	System.out.print(insertOption);      // = preleva o deposita      
                String choice = stdIn.readLine();
            	out.println(choice);
            	//System.out.print("Inserisci la somma da Versare/Prelevare: ");
            	String insertChoice = in.readLine();
            	ControlMessage(insertChoice);
            	System.out.print(insertChoice);         //= somma di denaro
                String amount = stdIn.readLine();
            	out.println(amount);
            	System.out.println("Il server ha terminato la connessione.");
            }
 
        } catch (IOException e) {
            System.err.println("Errore di connessione al server: " + e.getMessage());
        }
    }
    private static void ControlMessage(String response) {
    	if (response == null) {
    	    System.out.println("Il server ha terminato la connessione.");
    	    System.exit(0); // Termina il programma client
    	}
    }
}