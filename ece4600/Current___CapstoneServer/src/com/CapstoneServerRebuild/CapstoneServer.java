package com.CapstoneServerRebuild;

import sun.rmi.runtime.Log;

import javax.swing.*;
import java.awt.*;

public class CapstoneServer
{
    public static JFrame frame = null;
    public static InventoryManager    InventoryManager;
    public static SocketManager       SocketManager;

    public CapstoneServer()
    {
        InventoryManager = new InventoryManager();
        //SocketManager = new SocketManager();

        frame = new JFrame("GUI Container");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(600,800);
        frame.getContentPane().add(new MainGUI());
        frame.pack();
        frame.setVisible(true);
    }



    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new CapstoneServer();
            }
        });
    }
}
