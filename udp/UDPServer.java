/*
 * Created on 01-Mar-2016
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.net.InetAddress;

import common.MessageInfo;

public class UDPServer {

	private DatagramSocket recvSoc;
	private int totalMessages = -1;
	private int[] receivedMessages;
	private boolean close;

	private void run() {
		int				pacSize;
		byte[]			pacData;
		DatagramPacket 	pac;

		// TO-DO: Receive the messages and process them by calling processMessage(...).
		//        Use a timeout (e.g. 30 secs) to ensure the program doesn't block forever
		while(!close){
				
				pacSize = 1024;
				pacData = new byte[pacSize];
				pac = new DatagramPacket(pacData, pacSize);
				
				try{
						recvSoc.setSoTimeout(30000);
						recvSoc.receive(pac);
				}catch(IOException e){
						System.out.println("Time out. Closing server");
						LOGprint(msg);
						System.exit(-1);
				}
				
				processMessage(new string(pac.getData()));
				
		}
		

	}

	public void processMessage(String data) {

		MessageInfo msg = null;

		// TO-DO: Use the data to construct a new MessageInfo object
		System.out.println("message data: " + data);
		
		try{
			msg = new MessageInfo(data);
		} catch(Exception e){
				System.out.println("messageInfo Error: "+ data);
				System.out.println("Error: "+e);
		}

		// TO-DO: On receipt of first message, initialize the receive buffer
		
		if(receivedMessages == null){
				totalMessages = msg.totalMessages;
				receivedMessages = new int[totalMessages];
		}

		// TO-DO: Log receipt of the message
		
		receivedMessages[msg.messageNum]=1;

		// TO-DO: If this is the last expected message, then identify
		//        any missing messages
		
		if(msg.messageNum + 1 == msg.totalMessages){
				LOGprint(msg);
		}
		

	}


	public UDPServer(int rp) {
		// TO-DO: Initialise UDP socket for receiving data
		
		try{
			
			this.rp = rp;
			this.recvSoc = new DatagramSocket(this.rp);
		} catch(Exception e){
				System.out.println("Could not create datagramsocket");
				System.exit(-1);
		}
			

		// Done Initialisation
		System.out.println("UDPServer ready");
	}

	public static void main(String args[]) {
		int	recvPort;

		// Get the parameters from command line
		if (args.length < 1) {
			System.err.println("Arguments required: recv port");
			System.exit(-1);
		}
		recvPort = Integer.parseInt(args[0]);
		
		UDPServer myServer = new UDPServer(recvPort);
		
		try{
				myServer.run();
		} catch (SocketTimeoutException e){}
		
	}

		// TO-DO: Construct Server object and start it by calling run().
	private void run() throws SocketTimeoutException {
			int pacSize;
			byte [] pacData;
			DatagramPacket pac;
			
			System.out.println("Waiting...");
			
			while(!close){

			pacSize = 1024;
			pacData = new byte[pacSize];
			pac = new DatagramPacket(pacData, pacSize);

			try{
				recvSoc.setSoTimeout(30000);
				recvSoc.receive(pac);

			} catch (IOException e) {
				System.out.println("Time out. Closing server");
				LOGprint(msg);
				System.exit(-1);
			}

			processMessage(new String(pac.getData()));

		}

	}

	
	private void LOGprint(MessageInfo msg){
			
			close = true;
			
			String s = "Lost packet numbers: ";
			int count - 0;
			
			for(int i = 0; i<totalMessages;i++){
					
				if(receivedMessages[i] !=1){
					
					count++;
					s = s+(i+1)-",";
				}
			}
			
			if(count == 0){
					
					s = s + "None";
			}
			System.out.println("LOG:");
			System.out.println("Number of messages received successfully: " + (msg.totalMessages - count));
			System.out.println("of a total of " + msg.totalMessages);
			System.out.println("Number of messages not received: " + count);
			System.out.println(s);		
			System.out.println("END OF LOG.");
	}		
			
			
					
		
	
}


