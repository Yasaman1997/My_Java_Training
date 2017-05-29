package ir.aut.ceit.logic;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Admin on 5/29/2017.
 */
public class TcpChannel {
    private Socket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    public TcpChannel(SocketAddress socketAddress, int timeout)
    {}
    public TcpChannel(Socket socket, int timeout)
    {}

    /**
     * Try to read specific count from input stream.
     */
    public byte[] read(final int count)
    {
        return new byte[0];
    }
    /**
     * Write bytes on output stream.
     */
    public void write(byte[] data)
    {}

    /**
     * Check socket’s connectivity.
     */
    public boolean isConnected()
    {
        return false;
    }
    /**
     * Try to close socket and input-output streams.
     */
    public void closeChannel()
    {}
}


