package wallet;
 
public class Register {
	private int id;
	private String[] Users;
	private String[] Passwords;
	private int[] amount;

	Register(){
		id = 0;
		this.Users = new String[100];
		this.Passwords = new String[100];
		this.amount = new int[100];
	}
	public void addUser(String user, String password) {       //aggiunge un user
		Users[id] = user;
		Passwords[id] = password;
		amount[id]= 0;
		id++;
	}
	public boolean CheckUser(String u) {    //lo so è il controllo peggiore del mondo ma non è importante
		boolean check = true;
		for(int i = 0; i < id; i++) {
			if(Users[i].equals(u)) {
				check = false;
			}
		}
		return check;
	}
	public void usersList() { 
		System.out.println("");                       //stampa gli user
		System.out.println("Lista degli Utenti:");
		System.out.println("");
		for(int i = 0; i < id; i++) {
			System.out.println(Users[i] + " " + amount[i] + "$");
		}
		System.out.println("");
	}
	public boolean CheckPassword(String us, String pass) {
	    int app = -1; // Indice inizializzato a un valore non valido
	    boolean check = false;
	    for (int i = 0; i < id; i++) {
	        if (Users[i].equals(us)) { 
	            app = i;
	        }
	    }
	    if (app != -1 && Passwords[app].equals(pass)) { // Verifica se l'utente esiste e la password corrisponde
	        check = true;
	    }
	    return check;
	}
	public int GetSaldo(String us) {
		int app=-1;
		int saldo;
		for(int i = 0; i < id; i++) {
			if (Users[i].equals(us)) { 
	            app = i;
	        }
		}
		saldo = amount[app];
		return saldo;
	}
	public void Preleva(String us, int value) {
		int app=-1;
		for(int i = 0; i < id; i++) {
			if (Users[i].equals(us)) { 
	            app = i;
	        }
		}
		amount[app]= amount[app] - value;
	}
	public void Deposita(String us, int value) {
		int app=-1;
		for(int i = 0; i < id; i++) {
			if (Users[i].equals(us)) { 
	            app = i;
	        }
		}
		amount[app]= amount[app] + value;
	}
	public int cast(String value) {
	    int result = 0; // Valore di default
	    try {
	        result = Integer.parseInt(value); // Converte la stringa in intero
	    } catch (NumberFormatException e) {
	        System.out.println("Errore: impossibile convertire la stringa in un intero.");
	    }
	    return result;
	}
 
}