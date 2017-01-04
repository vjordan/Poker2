package reseau;

public class Start {

	public static void main(String[] args) {
		String host = "127.0.0.1";
	    int port = 1234;
	      
	    Serveur ts = new Serveur(host, port);
	    ts.open();
	      
	    for(int i = 0; i < 1; i++){
	       Thread t = new Thread(new ClientConnexion(host, port));
	       t.start();
	    }
	}
}
