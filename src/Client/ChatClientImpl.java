package Client;

import Server.ChatServerInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;

class ChatClientImpl implements ChatClientInterface {
    public static void main (String[] argv) {
        try {
            ChatServerInterface chat = (ChatServerInterface) Naming.lookup ("//localhost/chat");
            System.out.println ("Chat Client");
        } catch (Exception e) {
            System.out.println ("ChatClient failed:");
            e.printStackTrace();
        }
    }

    @Override
    public int message(String message) throws RemoteException {
        return 0;
    }

    @Override
    public int kick() throws RemoteException {
        return 0;
    }
}


