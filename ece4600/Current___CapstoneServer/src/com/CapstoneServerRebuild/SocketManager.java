package com.CapstoneServerRebuild;


//Copyright (c) Robert Kruzel 2017 -- Server software for Capstone
//Design course, Wayne State University Fall 2017.


import com.sun.security.ntlm.Server;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketManager
{
    // init vars

    private static final int portServer = 4444;
    private static int userId = 0;

    public SocketManager()
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

        }
        catch (IOException e)
        {
            System.out.println("Caught IOException " + e);
        }
    }

    private static class ClientManager extends Thread
    {
        // data
        private int userID;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

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
                        switch (message.charAt(1))
                        {
                            case 'a':
                            {
                                // function 1
                                break;
                            }

                            case 'b':
                            {
                                // function 2
                            }

                            case 'c':
                            {
                                // function 3
                            }

                            case 'd':
                            {
                                // function 4
                            }
                            default:
                            {
                                break;
                            }

                        }




                    }
                }
            } catch (IOException e)
            {
                System.out.println(e);
            }
        }
    }

}