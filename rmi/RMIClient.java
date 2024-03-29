/*
 * Created on 01-Mar-2016
 */
package rmi;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import common.MessageInfo;

public class RMIClient {

	public static void main(String[] args) {

		RMIServerI iRMIServer = null;

		// Check arguments for Server host and number of messages
		if (args.length < 2){
			System.out.println("Needs 2 arguments: ServerHostName/IPAddress, TotalMessageCount");
			System.exit(-1);
		}

		String urlServer = new String("rmi://" + args[0] + "/RMIServer");
		int numMessages = Integer.parseInt(args[1]);
		MessageInfo mes = new MessageInfo(numMessages,0);
		// TO-DO: Initialise Security Manager
		if (System.getSecurityManager()==null){
			System.setSecurityManager(new SecurityManager());
		}
		// TO-DO: Bind to RMIServer

			try{
				//Registry registry = LocateRegistry.getRegistry(urlServer,1099);
				iRMIServer = (RMIServerI) Naming.lookup(urlServer);
				System.out.println("connected to server");

			} catch(Exception e){
				System.out.println(e);
			}

		// TO-DO: Attempt to send messages the specified number of times
		try{
			for(int i = 0;i<mes.totalMessages;i++){
			mes.messageNum = i;
			iRMIServer.receiveMessage(mes);
		}
	} catch(RemoteException e){
			System.out.println(e);
		}
	}
}
