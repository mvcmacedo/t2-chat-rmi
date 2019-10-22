import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    private static final long serialVersionUID = 5179276649815567504L;

    public Map<String, List<String>> getChannels() {
        return channels;
    }

    private String identifier;
    private String name;
    private List<ChatServerInterface> clientList = new ArrayList<ChatServerInterface>();
    private Map<String, List<String>> channels;
    private List<String> messages = new ArrayList<String>();

    public ChatServerImpl() throws RemoteException {
        identifier = UUID.randomUUID().toString();
        channels = new HashMap<>();
    }

    public ChatServerImpl(String n) throws RemoteException {
        name = n;
        identifier = UUID.randomUUID().toString();
        channels = new HashMap<>();
    }

    public ChatServerImpl(String n, String id) throws RemoteException {
        name = n;
        identifier = id;
        channels = new HashMap<>();
    }

    public String getIdentifier() throws RemoteException {
        return identifier;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setName(String n) throws RemoteException {
        name = n;
    }

    public String getName() throws RemoteException {
        return name;
    }

    public List<ChatServerInterface> getClient() throws RemoteException {
        return clientList;
    }

    public void setClient(ChatServerInterface client) throws RemoteException {
        clientList.add(client);
    }

    public String register(String ipAddress, String nickname) throws RemoteException {
        String identifier = UUID.randomUUID().toString();
        ChatServerInterface client = new ChatServerImpl(nickname, identifier);

        clientList.add(client);

        return identifier;
    }

    public String nick(String id, String nickname) throws RemoteException {
        String result = "Erro ao atualizar ID: " + id;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                client.setName(nickname);
                result = "Nickname do ID " + client.getIdentifier() + "atualizado para: " + nickname;
            }
        }

        return result;
    }

    public Set<String> list() throws RemoteException {
        return channels.keySet();
    }

    public String create(String id, String channel) throws RemoteException {
        String result = "Erro ao criar canal: " + channel;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                channels.put(channel, new ArrayList<String>() {{
                    add("*" + client.getName());
                }});

                result = "Canal " + channel + "criado!";
            }
        }

        return result;
    }

    public String remove(String id, String channel) throws RemoteException {
        channels.remove(channel);

        return "Canal " + channel + "removido!";
    }

    public String join(String id, String channel) throws RemoteException {
        String result;
        List channelClients = channels.get(channel);

        ChatServerImpl cli = null;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                cli = (ChatServerImpl) client;
            }
        }

        if (cli != null && !channelClients.contains(cli.getName())) {
            channelClients.add(cli.getName());
            result = "Cliente " + id + " inserido no canal " + channel;
        } else {
            result = "Erro ao adicionar cliente " + id + "ao canal " + channel;
        }

        return result;
    }

    public String part(String id, String channel) throws RemoteException {
        String result = "Cliente " + id + " não encontrado no canal " + channel;

        List channelClients = channels.get(channel);

        String clientName = "";

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                clientName = client.getName();
            }
        }

        if (clientName.isEmpty()) {
            result = "Cliente " + id + " não encontrado";
        }

        if (channelClients.contains(clientName)) {
            channelClients.remove(clientName);

            result = "Cliente " + id + " removido do canal " + channel;
        }

        return result;
    }

    public List<String> names(String id, String channel) throws RemoteException {
        String clientName = "";

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                clientName = client.getName();
            }
        }

        List channelClients = channels.get(channel);

        if (!channelClients.contains(clientName)) {
            return new ArrayList<String>();
        }

        return channelClients;
    }

    public String kick(String id, String channel, String nickname) throws RemoteException {
        String result = "Usuário não autorizado a remover cliente " + nickname + " do canal " + channel;
        String adminName = "";

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                adminName = client.getName();
            }
        }

        List channelClients = channels.get(channel);

        if (channelClients.contains("*" + adminName)) {
            channelClients.remove(nickname);
            result = "Cliente " + nickname + " removido do canal " + channel;
        }

        return result;
    }

    public int msg(String id, String channel, String nickname, String message) throws RemoteException {
//        String adminName = "";
//
//        for (ChatServerInterface client : clientList) {
//            if (client.getIdentifier() == id) {
//                adminName = client.getName();
//            }
//        }
//
//
        return 0;
    }

    public String message(String id, String channel, String message) throws RemoteException {
        String clientName = "";

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier().equalsIgnoreCase(id)) {
                clientName = client.getName();
            }
        }

        String msg = "<" + clientName + "> " + message;
        System.out.println(msg);
        messages.add(msg);

        return msg;
    }

    public int quit(String id) throws RemoteException {
        return 0;
    }
}
