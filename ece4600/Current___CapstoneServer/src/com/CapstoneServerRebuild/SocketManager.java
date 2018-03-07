package com.CapstoneServerRebuild;


//Copyright (c) Robert Kruzel 2017 -- Server software for Capstone
//Design course, Wayne State University Fall 2017.




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class SocketManager
{
    // Singelton configuration
    private static SocketManager socketManager;

    private static final int portServer = 4444;
    private static int userId = 0;
    public static synchronized SocketManager getInstance()
    {
        if (socketManager == null)
        {
            socketManager = new SocketManager();
        }
        return socketManager;
    }
    private SocketManager()
    {
        socketManager.startServer();
    }

    public void messageOut(String string)
    {
        String temp = string;

    }

    public static void startServer()
    {
        Runnable server = new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ServerSocket serverSocket = new ServerSocket(portServer);
                    userId++;

                    try
                    {
                        while (true)
                        {
                            Socket serverClient = serverSocket.accept();
                            ClientManager clientManager = new ClientManager(serverClient, userId);
                            clientManager.start();
                        }
                    } finally
                    {
                        serverSocket.close();
                    }

                } catch (IOException e)
                {
                    System.out.println("Caught IOException " + e);
                }
            }
        };
        Thread serverThread = new Thread(server);
        serverThread.start();
    }
    private static class ClientManager extends Thread
    {
        // data
        private int userID;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String messageIn;
        public boolean messageFlag;


        public ClientManager(Socket socket, int userId)
        {
            this.socket = socket;
            this.userID = userId;
        }

        public void messageOut(String message)
        {
            System.out.println("printing message " + message);
            out.write(message);
        }

        public void run()
        {
            try
            {
                in = new BufferedReader((new InputStreamReader(socket.getInputStream())));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true)
                {
                    String message = in.readLine();
                    if (message.charAt(0) == '{' && (message.charAt(5) == '}'))
                    {
                        this.messageIn = message;
                        messageFlag = true;
                    }
                }
            } catch (IOException e)
            {
                System.out.println(e);
            }
        }
    }

}