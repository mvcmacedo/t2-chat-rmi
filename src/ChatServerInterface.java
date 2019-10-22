import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface ChatServerInterface extends Remote {
    public String getIdentifier() throws RemoteException;
    public void setName(String name) throws RemoteException;
    public String getName() throws RemoteException;
    public List<String> getMessages() throws RemoteException;
    public List<ChatServerInterface> getClient() throws RemoteException;
    public void setClient(ChatServerInterface client) throws RemoteException;
    public String register(String ipAddress, String nickname) throws RemoteException;
    public String nick(String id, String nickname) throws RemoteException;
    public Set<String> list() throws RemoteException;
    public String create(String id, String channel) throws RemoteException;
    public String remove(String id, String channel) throws RemoteException;
    public String join(String id, String channel) throws RemoteException;
    public String part(String id, String channel) throws RemoteException;
    public List<String> names(String id, String channel) throws RemoteException;
    public String kick(String id, String channel, String nickname) throws RemoteException;
    public int msg(String id, String channel, String nickname, String message) throws RemoteException;
    public String message(String id, String channel, String message) throws RemoteException;
    public int quit(String id) throws RemoteException;
}
