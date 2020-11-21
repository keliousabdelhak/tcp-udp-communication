
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

public class Server extends Thread{

	int cpt = 0;
	int numClient = 0;
	
	public void run() {
		try {
			ServerSocket ss = new ServerSocket(1027);
			
			while(true) {
				Socket s = ss.accept();
				++cpt;
				++numClient;
				
				new Conversation(s, numClient).start();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	class Conversation extends Thread{
		
		private Socket socket;
		public int num;

		public Conversation(Socket socket, int j) {
			super();
			this.socket = socket;
			this.num = j;
		}

		public void run() {
			try {
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = null;
				pw = new PrintWriter(os, true);
				
				String ip = socket.getRemoteSocketAddress().toString();
				System.out.println("Connexion du client n " + this.num + " IP : "+ ip);
				
				pw.println("Vous etes le client n " + this.num + ".  \n");

				String req ="";
				String toSend = "";
	
				while(!socket.isClosed()) {
					req = br.readLine();
			
					switch(req.toUpperCase()) {
						
						case "NB" :	
							pw.println("Nombre de client = "+ cpt + ".\n");
							break;
						
						case "END":
							System.out.println("\n[CLIENT "+ this.num +"] : Communication terminee. \n");
							cpt--;
							pw.println("[SERVEUR] : Communication terminee.\n");
							pw.close();
							br.close();
							socket.close();
							break;
							
						default :
							pw.println("try again ...");
							break;
					}
				
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}	
	}
	
	public static void main(String[] args) {
		new Server().start();
	}


}
