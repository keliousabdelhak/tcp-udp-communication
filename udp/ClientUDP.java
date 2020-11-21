
import java.io.IOException;
import java.net.*;
import java.util.*;

public class ClientUDP {

	public static void main(String[] args) throws IOException {
		Scanner inFromUser = new Scanner(System.in);
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		System.out.print("[CLIENT] : ");
		String sentence = "[CLIENT] : "+inFromUser.nextLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		
		//Reception message du client
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String msg = new String(receivePacket.getData());
		System.out.println(msg);

	}

}
