package ir.aut.ceit.logic;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.Queue;

/**
 * Created by Admin on 5/29/2017.
 */
public class NetworkHandler extends Thread {
    private TcpChannel mTcpChannel;
    private Queue<byte[]> mSendQueue;
    private Queue<byte[]> mReceivedQueue;
    private ReceivedMessageConsumer mConsumerThread;

    public NetworkHandler(SocketAddress socketAddress, INetworkHandlerCallback iNetworkHandlerCallback)
    {}

    public NetworkHandler(Socket socket, INetworkHandlerCallback iNetworkHandlerCallback)
    {}

    /**
     * Add serialized bytes of message to the sendQueue.
     */
    public void sendMessage(BaseMessage baseMessage)
    {}

    /**
     * While channel is connected and stop is not called: * if sendQueue is not empty, then poll a message and send it * else if readChannel() is returning bytes, then add it to receivedQueue.
     */
    @Override
    public void run()
    {}

    /**
     * Kill the thread and close the channel.
     */
    public void stopSelf()
    {}

    /**
     * Try to read some bytes from the channel.
     */
    private byte[] readChannel()
    {
        return new byte[0];
    }

    private class ReceivedMessageConsumer extends Thread {
        /**
         * While channel is connected and stop is not called: * if there exists message in receivedQueue, then create a message object * from appropriate class based on message type byte and pass it through onMessageReceived * else if receivedQueue is empty, then sleep 100 ms.
         */
        @Override
        public void run()
        {}
    }

    public interface INetworkHandlerCallback {
        void onMessageReceived(BaseMessage baseMessage);

        void onSocketClosed();
    }
}