

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) {

		try {
			Socket socket = new Socket("127.0.0.1", 1027);

			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);

			String line = "";
			Scanner scan = new Scanner(System.in);

			String str = "";
			System.out.println("_________");
			System.out.println("\nBienvenu \n");
			System.out.println("---------");
			System.out.println("Liste des commandes autorisees :  \n");
			System.out.println("\t$ nb : renvoie le nombre de client connectees ");
			System.out.println("\t$ end : fin de session ");
			
			while ((str = br.readLine()) != null)  {
				
				System.out.println(str);
				System.out.print("\n [Client]$ ");
				
				line = scan.nextLine();		
				line = line.toUpperCase();
				
				pw.println(line);

				System.out.println(br.readLine());
				
				if((line.equals("END")) ){
					System.out.println(br.readLine());
					break;
				}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
