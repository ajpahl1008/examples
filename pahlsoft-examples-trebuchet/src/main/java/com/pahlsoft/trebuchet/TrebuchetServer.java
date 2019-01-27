package com.pahlsoft.trebuchet;

import org.apache.log4j.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TrebuchetServer implements Runnable {
    private static ServerSocket server;
    private static int port;
    private static Socket socket;
    private static ObjectInputStream ois;
    private static ObjectOutputStream oos;
    private static Dialogue message;
    private static long initTime;
    private static boolean keepRunning = true;
    private static long TIMEOUTMAX = 30000;
    private static Logger LOG = Logger.getLogger(TrebuchetServer.class);


    public  TrebuchetServer(int port) {
        TrebuchetServer.port = port;
        if (isServerListening("localhost",port)) {
           LOG.info("Server: Already Running");
        } else {
            Thread t = new Thread(this);
            t.start();
        }
    }

    public void run() {
        try {
            initTime = System.currentTimeMillis();

            server = new ServerSocket(port);
            socket = server.accept();

            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            message = (Dialogue) ois.readObject();
            while (underMaxTime() && keepRunning) {

                switch (message) {
                    case SALUTATION:
                        LOG.info("Server: Received Salutation on Port " + port);
                        oos.writeObject(Dialogue.SALUTATION);
                        break;
                    case TERMINATE:
                        LOG.info("Server: Received Terminate Request");
                        oos.writeObject(Dialogue.ACK_TERMINATE);
                        keepRunning = false;
                        break;
                    case START_TEST:
                        oos.writeObject(Dialogue.ACK_START_TEST);
                        int targetPort = (int) ois.readObject();
                        TrebuchetServer ts = new TrebuchetServer(targetPort);
                        oos.writeObject(Dialogue.ACK_TARGET_PORT);
                        LOG.info("Server: Starting Test on Port: " + targetPort);
                        break;
                    default:
                        LOG.info("Server: Unrecognized Command");
                        oos.writeObject(Dialogue.BAD_COMMAND);
                        break;

                }
                message = (Dialogue) ois.readObject();

            }
        } catch (SocketException se) {
            if (LOG.isDebugEnabled()) LOG.debug("Server: Sockets Closed");
        } catch (EOFException eofe) {
            if (LOG.isDebugEnabled()) LOG.debug("Server: No More Input Data on Port: " + port);
        } catch (IOException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Server: IO Exception");
        } catch (ClassNotFoundException e) {
            if (LOG.isDebugEnabled()) LOG.debug("Server: No Class Def Found");
        }
    }

    private boolean underMaxTime() {
        if ( System.currentTimeMillis() < (initTime + TIMEOUTMAX )) {
            return true;
        }
        LOG.info("Server: Max Time Reached");
        keepRunning=false;
        return false;
    }

    public void stop() {
        if (LOG.isDebugEnabled()) LOG.debug("Server: Stopping Server Socket");
        try {
            if (LOG.isDebugEnabled()) LOG.debug("Server: Closing Server and Sockets");
            oos.close();
            ois.close();
            server.close();
            socket.close();
        } catch (NullPointerException npe) {
            if (LOG.isDebugEnabled()) LOG.debug("Server: Already Stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isListening() {
        try {
            if (ois.readObject() == null) {
                return false;
            }
        } catch (EOFException eofe ) {
            return false;
        } catch (NullPointerException npe) {
            return false;
        } catch (IOException e) {
            if (LOG.isDebugEnabled())  e.printStackTrace();
        } catch (ClassNotFoundException e) {
            if (LOG.isDebugEnabled()) e.printStackTrace();
        }
        return true;
    }

    public static boolean isServerListening(String host, int port)
    {
        Socket s = null;
        try
        {
            s = new Socket(host, port);
            s.sendUrgentData(1);
        }
        catch (Exception e)
        {
            return false;
        }
        finally
        {
            if(s != null) {
                try {s.close();}
                catch(Exception e){}
            }
        }
        return true;
    }

}
