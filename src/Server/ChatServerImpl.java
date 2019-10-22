package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    private static final long serialVersionUID = 5179276649815567504L;

    public Map<String, List<String>> getChannels() {
        return channels;
    }

    private int identifier;
    private String name;
    private List<ChatServerInterface> clientList;
    private Map<String, List<String>> channels;


    public ChatServerImpl(String n) throws RemoteException {
        name = n;
    }

    public ChatServerImpl(String n, int id) throws RemoteException {
        name = n;
        identifier = id;
        channels = new HashMap<>();
        clientList = new ArrayList<ChatServerInterface>();
    }

    public List<ChatServerInterface> getClientList() {
        return clientList;
    }

    public void setClientList(List<ChatServerInterface> clientList) {
        this.clientList = clientList;
    }

    @Override
    public int getIdentifier() throws RemoteException {
        return identifier;
    }

    @Override
    public void setName(String n) throws RemoteException {
        name = n;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public int register(String ipAddress, String nickname) throws RemoteException {
        int identifier = new Integer(UUID.randomUUID().toString());
        ChatServerInterface client = new ChatServerImpl(nickname, identifier);

        clientList.add(client);

        return identifier;
    }

    @Override
    public String nick(int id, String nickname) throws RemoteException {
        String result = "Erro ao atualizar ID: " + id;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier() == id) {
                client.setName(nickname);
                result = "Nickname do ID " + client.getIdentifier() + "atualizado para: " + nickname;
            }
        }

        return result;
    }

    @Override
    public Set<String> list() throws RemoteException {
        return channels.keySet();
    }

    @Override
    public String create(int id, String channel) throws RemoteException {
        String result = "Erro ao criar canal: " + channel;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier() == id) {
                channels.put(channel, new ArrayList<String>() {{
                    add("*" + client.getName());
                }});

                result = "Canal " + channel + "criado!";
            }
        }

        return result;
    }

    @Override
    public String remove(int id, String channel) throws RemoteException {
        channels.remove(channel);

        return "Canal " + channel + "removido!";
    }

    @Override
    public String join(int id, String channel) throws RemoteException {
        String result;
        List channelClients = channels.get(channel);

        ChatServerImpl cli = null;

        for (ChatServerInterface client : clientList) {
            if (client.getIdentifier() == id) {
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

    @Override
    public String part(int id, String channel) throws RemoteException {
        String result = " ";
        List channelClients = channels.get(channel);
        // ChatServerImpl client = clientList.

        // ChatServerImpl cli = null;

//        for (String channelClient : channelClients) {
//            if (channelClient.equalsIgnoreCase())
//        }
//
//        if (cli != null && !channelClients.contains(cli.getName())) {
//            channelClients.add(cli.getName());
//            result = "Cliente " + id + " inserido no canal " + channel;
//        } else {
//            result = "Erro ao adicionar cliente " + id + "ao canal " + channel;
//        }

        return result;
    }

    @Override
    public String[] names(int id, String channel) throws RemoteException {
        return new String[0];
    }

    @Override
    public int kick(int id, String channel, String nickname) throws RemoteException {
        return 0;
    }

    @Override
    public int msg(int id, String channel, String nickname, String message) throws RemoteException {
        return 0;
    }

    @Override
    public int message(int id, String channel, String message) throws RemoteException {
        return 0;
    }

    @Override
    public int quit(int id) throws RemoteException {
        return 0;
    }
}
