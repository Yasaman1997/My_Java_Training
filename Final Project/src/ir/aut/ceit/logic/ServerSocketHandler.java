package ir.aut.ceit.logic;

/**
 * Created by Admin on 5/29/2017.
 */
public class ServerSocketHandler extends Thread {
    public ServerSocketHandler(int port, NetworkHandler.INetworkHandlerCallback iNetworkHandlerCallback, IServerSocketHandlerCallback iServerSocketHandlerCallback)
    {}

    /**
     * While server socket is connected and stop is not called: * if a connection receives, then create a network handler and pass it through onNewConnectionReceived * else sleep for 100 ms.
     */
    @Override
    public void run() {

    }

    /**
     * Kill the thread and close the server socket.
     */
    public void stopSelf() {
    }

    public interface IServerSocketHandlerCallback {
        void onNewConnectionReceived(NetworkHandler networkHandler);
    }
}