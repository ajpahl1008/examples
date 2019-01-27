package com.pahlsoft.trebuchet;

import org.apache.log4j.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class TrebuchetClient {

    private static String server;
    private static int port;
    private static Socket socket;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;
    private static Logger LOG = Logger.getLogger(TrebuchetClient.class);


    public TrebuchetClient(String server, int port) {
        this.server = server;
        this.port = port;
        connect();
    }

    public void connect() {
        try {
            InetAddress host = InetAddress.getByName(this.server);
            socket = new Socket(host, this.port);

            if (socket.isConnected()) {
                LOG.info("Client: Connected to Server: " + this.server + " on Port: " + this.port);
            }
        } catch (ConnectException ce) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Failed to Connect to Server " + this.server + " on Port: " + this.port);
        } catch (UnknownHostException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: unable to resolve host name or IP: " + this.server);
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) e.printStackTrace();
        }

    }

    public Enum send(int portNumber) {
        Enum response = Dialogue.NULL;

        try {
            initStreams();
            oos.writeObject(portNumber);
            response = (Enum) ois.readObject();
        } catch (UnknownHostException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unable to determine Host name " + server);
        } catch (SocketException se ) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unable to connect to host " + server);
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unable to Send to host" + server);
        } catch (ClassNotFoundException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Bad Message");
        }
        return response;
    }

    public Enum send(Dialogue message) {
        Enum response = Dialogue.NULL;

        try {
            initStreams();
            oos.writeObject(message);
            response = (Enum) ois.readObject();
            LOG.info("Client: Received Response: " + response.toString());
        } catch (UnknownHostException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unknown Host Exception");
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unable to connect to server on port: " + port);
        } catch (ClassNotFoundException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Class Not Found");
        }

        return response;
    }

    public void disconnect()  {
        if (socket.isConnected()) {
                try {

                    if (ois != null) ois.close();
                    if (oos !=null) oos.close();

                        socket.shutdownOutput();
                        socket.shutdownInput();
                        socket.close();
                } catch (EOFException eof) {
                    if (LOG.isDebugEnabled()) LOG.debug("Client: Socket is already closed");
                } catch (Exception e) {
                    if (LOG.isDebugEnabled()) LOG.debug("Client: Client is already disconnected");
                }
        } else {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Client is disconnected");
        }
    }

    private void initStreams() {
        try {
            if (oos == null)
                oos = new ObjectOutputStream(socket.getOutputStream());
            if (ois == null) {
                ois = new ObjectInputStream(socket.getInputStream());
            }
        } catch (IOException ioe) {
            if (LOG.isDebugEnabled()) LOG.debug("Client: Unable to Initialize Streams");
        }

    }

}
