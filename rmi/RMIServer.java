/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.registry.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

import common.*;

public class RMIServer extends UnicastRemoteObject implements RMIServerI {

	private int totalMessages = -1;
	private int[] receivedMessages;

	public RMIServer() throws RemoteException {
	}

	public void receiveMessage(MessageInfo msg) throws RemoteException {

		// TO-DO: On receipt of first message, initialise the receive buffer
		int[] buff = new int[msg.totalMessages];
		// TO-DO: Log receipt of the message

		// TO-DO: If this is the last expected message, then identify
		//        any missing messages

	}


	public static void main(String[] args) {

		RMIServer rmis = null;

		// TO-DO: Initialise Security Manager
		if (System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}
		// TO-DO: Instantiate the server class
		try {
			RMIServer server = new RMIServer();
		// TO-DO: Bind to RMI registry
			rebindServer("rmi://localhost/RMIServer",server);
		}
		catch(Exception e) {
			System.err.println("Server Exception: " + e.toString());
			e.printStackTrace();
		}
	}

	protected static void rebindServer(String serverURL, RMIServer server) {
		try {
		// TO-DO:
		// Start / find the registry (hint use LocateRegistry.createRegistry(...)
		// If we *know* the registry is running we could skip this (eg run rmiregistry in the start script)
			Registry foundreg = LocateRegistry.createRegistry(1099);
		// TO-DO:
		// Now rebind the server to the registry (rebind replaces any existing servers bound to the serverURL)
		// Note - Registry.rebind (as returned by createRegistry / getRegistry) does something similar but
		// expects different things from the URL field.
			foundreg.rebind(serverURL,server);
			System.out.println("server started");
		} catch(RemoteException e){
				System.err.println("Server Exception: " + e.toString());
				e.printStackTrace();
			}
		}
}
