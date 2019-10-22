package Server;

import com.sun.xml.internal.xsom.XSUnionSimpleType;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

class ChatServer {
    public static void main (String[] argv) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready.");
        } catch (RemoteException e) {
            System.out.println("RMI registry already running.");
        }
        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Your name and press Enter:");
            String name = s.nextLine().trim();

            ChatServerImpl server = new ChatServerImpl(name);

            Naming.rebind("Chat", server);

            System.out.println ("ChatServer is ready.");

            while(true) {
                System.out.println("Digite seu comando: ");
                String msg = s.nextLine().trim();

                System.out.println(server.getClientList());
            }

        } catch (Exception e) {
            System.out.println ("ChatServer failed:");
            e.printStackTrace();
        }
    }
}

