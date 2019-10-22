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

            ChatServerImpl server = new ChatServerImpl();
            Naming.rebind("Chat", server);

            System.out.println ("ChatServer is ready.");

        } catch (Exception e) {
            System.out.println ("ChatServer failed:");
            e.printStackTrace();
        }
    }
}

