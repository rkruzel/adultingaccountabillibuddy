package com.CapstoneServerRebuild;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class SocketManager
{
    // init
    static final int portServer = 4444;
    static ServerSocket serverSocket = null;
    static boolean serverListening = false;
    Thread clientThread = null;
    static Socket clientSocket = null;
    static Collection<SocketConnection> socketCollection = null;

    SocketManager()
    {
        try
        {
            serverSocket = new ServerSocket(portServer);
            serverListening = true;
        } catch (IOException ex)
        {
            System.out.println("Server Socket init failed");
            ex.printStackTrace();
        }

        while (serverListening)

        {
            try
            {

                try
                {
                    clientSocket = serverSocket.accept();
                    clientThread = new Thread();
                    clientThread.run();
                    socketCollection.add(new SocketConnection(clientSocket, clientThread));
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
            } finally
            {
                try
                {
                    for (SocketConnection socketConnection : socketCollection)
                    {
                        socketConnection.endConnection();
                    }
                    serverListening = false;
                    serverSocket.close();
                    serverSocket = null;
                    System.out.println("Server socket closed.");
                } catch (IOException ex)
                {
                    serverListening = false;
                    System.out.println("exception in finally");
                    ex.printStackTrace();
                    System.out.print(ex);
                }
            }
        }
    }
}
