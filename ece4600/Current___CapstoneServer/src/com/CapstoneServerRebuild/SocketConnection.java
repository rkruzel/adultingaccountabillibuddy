package com.CapstoneServerRebuild;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class SocketConnection extends SocketManager implements Runnable
{
    String readData = null;
    BufferedReader bReader = null;
    PrintWriter pWriter = null;
    Socket threadSocket = null;
    Thread clientThread = null;
    boolean isListening = false;
    boolean hasMessage = false;
    String messageIn = null;
    String messsageOut = null;
    boolean isRobot;
    String clientIP = null;

    SocketConnection(Socket socket, Thread thread)
    {
        this.clientThread = thread;
        this.threadSocket = socket;
        clientIP = this.threadSocket.getRemoteSocketAddress().toString();

        switch(clientIP)
        {
            case "10.0.10.10":
            case "10.0.10.11":
            case "10.0.10.12":
            case "10.0.10.13":
            case "10.0.10.14":
            case "10.0.10.15":
            {
                isRobot = true;
                break;
            }
            case "10.0.10.20":
            case "10.0.10.21":
            case "10.0.10.22":
            case "10.0.10.23":
            case "10.0.10.24":
            case "10.0.10.25":
            {
                isRobot = false;
                break;
            }
            default:
                break;
        }
    }

    public void run()
    {
        try
        {
            // instantiate thread local IO
            bReader = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
            pWriter = new PrintWriter(threadSocket.getOutputStream(), true);
            isListening = true;

        } catch (IOException ex)
        {
            // TODO error handling
            System.out.println("IO error:");
            System.out.println(ex);
        }


        try
        {
            while (this.isListening)
            {
                // blocks until /r is received
                readData = this.bReader.readLine();

                // if message received
                if (this.readData != null)
                {
                    // is message proper format
                    if ((readData.charAt(0) == '{') && (readData.charAt(readData.length()) == '}'))
                    {
                        this.messageIn = readData;
                        this.hasMessage = true;
                    }
                    readData = null;
                }
            }
        }

        catch (IOException ex)
        {
            System.out.println("Socket error.");
            System.out.println(ex);
        }

        catch (NullPointerException ex)
        {
            System.out.println("Null Pointer error.");
            System.out.println(ex);
        }

        finally
        {
            try
            {
                this.bReader.close();
                this.pWriter.close();
                this.threadSocket.close();
            }

            catch(IOException ex)
            {
                System.out.println("IOException in closing socket");
                System.out.println(ex);
            }
            catch(NullPointerException ex)
            {
                System.out.println("Null Pointer in closing socket.");
                System.out.println(ex);
            }
        }
    }

    void parseIncoming()
    {
        String[] temp = messageIn.split("[{},]");

        //TODO fill in with complete message handling
    }

    void sendMessage(String message)
    {
        this.pWriter.print(message);
    }

    void endConnection()
    {
        this.isListening = false;
        socketCollection.remove(clientIP);
    }
}
