/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import common.MessageInfo;

public class UDPClient {

	private DatagramSocket sendSoc;

	public static void main(String[] args) {
		InetAddress	serverAddr = null;
		int			recvPort;
		int 		countTo;
		String 		message;

		// Get the parameters
		if (args.length < 3) {
			System.err.println("Arguments required: server name/IP, recv port, message count");
			System.exit(-1);
		}

		try {
			serverAddr = InetAddress.getByName(args[0]);
		} catch (UnknownHostException e) {
			System.out.println("Bad server address in UDPClient, " + args[0] + " caused an unknown host exception " + e);
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[1]);
		countTo = Integer.parseInt(args[2]);


		// TO-DO: Construct UDP client class and try to send messages
		UDPClient myClient = new UDPClient(recvPort);
		myClient.testLoop(serverAddr,recvPort,countTo);
		
	}

	public UDPClient() {
		// TO-DO: Initialise the UDP socket for sending data
		try{
				sendSoc = new DatagramSocket(recvPort);
		}catch(Exception e){ 												//exception E is empty catch clause, or silent catch clause
				System.out.println(" could not create datagramsocket");
		}
		
	}

	private void testLoop(InetAddress serverAddr, int recvPort, int countTo) {
		int				tries = 0;

		// TO-DO: Send the messages to the server
		for(int i = 0; i<countTo;i++){
				
				String m = new String( (Integer.toString(countT)) + ";" + (Integer.toString(i)));
				System.out.println(m);
				this.send(m,serverAddr,recvPort);
		}
	}

	private void send(String payload, InetAddress destAddr, int destPort) {
		int				payloadSize;
		byte[]				pktData;
		DatagramPacket		pkt;

		// TO-DO: build the datagram packet and send it to the server
		payloadSize = payload.length();
		pktData = payload.getBytes();
		
		try{
				DatagramPacket pkt = new DatagramPacket(pktData, payloadsize , destAddr, destPort);
				sendSoc.send(pkt);
				
		} catch(Exception e){
				System.out.println(e.toString());
				System.out.println("message not sent");
		}
	}
}
		
		
	}
}
