

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServeurUDP {

	public static void main(String[] args) throws IOException {

		DatagramSocket serverSocket;

		serverSocket = new DatagramSocket(9876);

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while (true) {
			// TODO Auto-generated method stub
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);

			//modifier le code pour afficher l'adresse de la source et le port
			InetAddress adrClient = receivePacket.getAddress();//Recupere l'adresse IP du datagramme
			int port = receivePacket.getPort();//Recupere le port du datagramme
			System.out.println("[SERVEUR] : adresse IP = " + adrClient.getHostName() + ", port = " + port);

			if(receivePacket.getData() != null) {
				String toSend = "[SERVEUR] : OK.";

				String sentence = new String(receivePacket.getData());
				System.out.println(sentence);

				sendData = toSend.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adrClient, port);
				serverSocket.send(sendPacket);
			}

		}
	}
}
