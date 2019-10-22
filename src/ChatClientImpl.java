
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

class ChatClientImpl implements ChatClientInterface {
    public static void main(String[] argv) {
        try {
            Scanner s = new Scanner(System.in);

            ChatServerInterface chat = (ChatServerInterface) Naming.lookup ("rmi://localhost/Chat");

            System.out.println("Digite seu nome: ");
            String name = s.nextLine().trim();

            ChatServerInterface client = new ChatServerImpl(name);
            chat.setClient(client);

            while(true) {
                if (chat.getClient() != null) {
                    clear();

                    List<String> messages = chat.getMessages();

                    for (String message : messages) {
                        System.out.println(message);
                    }

                    System.out.println("Digite sua mensagem: ");
                    String msg = s.nextLine().trim();

                    switch(msg) {
                        case "/list":
                            System.out.println(chat.list());
                            break;
                        default:
                            String message = chat.message(client.getIdentifier(), "asdad", msg);
                            break;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println ("ChatClient failed:");
            e.printStackTrace();
        }
    }

    public static void message(String message) throws RemoteException {
        System.out.println(message);
    }

    public int kick() throws RemoteException {
        return 0;
    }

    public static void clear() {
        for (int i = 0; i < 100; ++i)
            System.out.println();
    }
}


