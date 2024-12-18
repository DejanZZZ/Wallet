package wallet;
 
import java.io.*;
import java.net.*;
 
 
public class Server {
    public static void main(String[] args) {
        int port = 12345;
        Register r = new Register();
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in attesa di connessioni sulla porta " + port);
 
            while (true) {
                // Accetta un client e delega a un nuovo thread
                Socket clientSocket = serverSocket.accept();
                System.out.println("----------------------------------------------------------------------------------------------");
                System.out.println("Client connesso: " + clientSocket.getInetAddress());
                System.out.println("----------------------------------------------------------------------------------------------");
                new Thread(new Handler(clientSocket, r)).start();
            }
        } catch (IOException e) {
            System.err.println("Errore nell'avviare il server: " + e.getMessage());
        }
    }
}
