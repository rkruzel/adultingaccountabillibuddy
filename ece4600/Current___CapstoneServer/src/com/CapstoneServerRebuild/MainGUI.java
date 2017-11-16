package com.CapstoneServerRebuild;

import javafx.scene.layout.Pane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MainGUI extends JFrame
{

    JButton                     DebugAdd;
    JButton                     DebugRemove;
    JButton                     DebugClear;
    JButton                     Quit;
    JButton                     InventoryButton;
    JButton                     ConnectionsButton;

    String                      UserText;
    JTextField                  TextInput;
    JPanel                      guiContainer;
    JPanel                      ButtonJPanel;
    JLabel                      DebugLabel;
    JList                       WorkQueue;
    JList                       WorkQueueList;
    DefaultListModel            WorkQueueDataList;

    public MainGUI()
    {
        initMainGui();
    }

    protected void initMainGui()
    {
        // containers
        WorkQueueDataList = new DefaultListModel();
        WorkQueueList = new JList(WorkQueueDataList);
        setSize(600,800);



        DebugAdd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                WorkQueueDataList.add(0, UserText);
                UserText = "";
            }
        });

        DebugRemove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WorkQueueDataList.remove(0);
            }
        });

        DebugClear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                WorkQueueDataList.clear();
            }
        });

        InventoryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //TODO stuff
            }
        });

        ConnectionsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //TODO stuff
            }
        });

        Quit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        TextInput.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                UserText = TextInput.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                UserText = TextInput.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                // not used
            }
        });
    }
}