package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface ChatServerInterface extends Remote {
    public int getIdentifier() throws RemoteException;
    public void setName(String name) throws RemoteException;
    public String getName() throws RemoteException;
    public int register(String ipAddress, String nickname) throws RemoteException;
    public String nick(int id, String nickname) throws RemoteException;
    public Set<String> list() throws RemoteException;
    public String create(int id, String channel) throws RemoteException;
    public String remove(int id, String channel) throws RemoteException;
    public String join(int id, String channel) throws RemoteException;
    public String part(int id, String channel) throws RemoteException;
    public String[] names(int id, String channel) throws RemoteException;
    public int kick(int id, String channel, String nickname) throws RemoteException;
    public int msg(int id, String channel, String nickname, String message) throws RemoteException;
    public int message(int id, String channel, String message) throws RemoteException;
    public int quit(int id) throws RemoteException;
}
