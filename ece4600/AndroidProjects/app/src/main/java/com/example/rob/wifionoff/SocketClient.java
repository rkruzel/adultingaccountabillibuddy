package com.example.rob.wifionoff;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Rob on 9/8/2017.
 */






public class SocketClient extends AsyncTask<?, ?, ?>
{

    static final String targetIP = "10.0.10.10";
    static final String targetPort = "8080";
    static String messageOut = "";
    static char turn = '\r';
    static boolean isRunning = false;
    private BufferedReader in;
    private PrintWriter out;
    private StringBuilder inMessage;
    private String TAG = "Client";
    private Socket socket;



    @Override
    protected void onCreate() {

        try {
            InetAddress arduinoIP = InetAddress.getByName(targetIP);

            socket = new Socket(arduinoIP, targetPort);

            try
            {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (isRunning) {

                    char temp;
                    temp = ((char) in.read());
                    if (temp == '/r') {

                    }
                    else if (temp != ' ') {

                    }
                }
            }
            catch (IOException e)
            {
                Log.e(TAG, "Caught IOException");
                e.printStackTrace();
            } finally {
                in = null;
                out.flush();
                out = null;
                socket = null;
            }
        }
    }
public void stopClient()
{
    isRunning = false;
    out.flush();
    in = null;
    out = null;

}
}
