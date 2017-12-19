package com.CapstoneServerRebuild;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.PAGE_END;

public class CapstoneServer extends Frame
{
    // manager classes
    public static InventoryManager    InventoryManager;
    public static SocketManager       SocketManager;

    private Label partTitle, queueTitle;
    private TextField nextJob, partNum;
    private Button getPart, putPart, executeJob, exit, showDetials, closePopup;
    private Checkbox autoRun;

    public LinkedList<String> jobQueue;


    public CapstoneServer()
    {
        //InventoryManager = new InventoryManager();
        //SocketManager = new SocketManager();

        jobQueue = new LinkedList<>();
        JList<String> queueList;
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        partTitle = new Label("Part Number");
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = PAGE_END;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(partTitle, constraints);

        autoRun = new Checkbox("Auto-Run Job",false);
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 1;
        autoRun.setEnabled(false);
        add(autoRun,constraints);

        queueTitle = new Label("Next Job");
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 0;
        add(queueTitle,constraints);

        nextJob = new TextField("Queue Empty", 10);
        nextJob.setEditable(false);
        constraints.anchor = CENTER;
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 1;
        add(nextJob, constraints);

        partNum = new TextField("", 10);
        partNum.setEditable(true);
        constraints.anchor = CENTER;
        constraints.weightx = 1;
        constraints.weighty = 1d;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(partNum, constraints);


        getPart = new Button("Retrieve Part");
        constraints.weightx = .5;
        constraints.anchor = CENTER;
        constraints.weighty = .33;
        constraints.insets = new Insets(10,5,5,5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(getPart, constraints);
        getPart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (partNum.getText().equals(""))
                {
                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "You must enter a part number.");

                }
                else if (jobQueue == null)
                {
                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "Job queue not properly initialized.");
                }

                else
                {
                    StringBuilder temp = new StringBuilder("GET: ");
                    temp.append(partNum.getText());
                    jobQueue.add(temp.toString());
                    partNum.setText("");
                    refreshQueue();
                }
            }
        });

        putPart = new Button("Store Part");
        constraints.weightx = .5;
        constraints.weighty = .33;

        constraints.insets = new Insets(10,5,5,5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(putPart, constraints);
        putPart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (partNum.getText().equals(""))
                {
                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "You must enter a part number.");

                }
                else if (jobQueue == null)
                {
                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "Job queue not properly initialized.");
                }
                else
                {
                    StringBuilder temp = new StringBuilder("STORE: ");
                    temp.append(partNum.getText());
                    jobQueue.add(temp.toString());
                    refreshQueue();
                    partNum.setText("");
                }
            }
        });

        showDetials = new Button("Show Details");
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(showDetials, constraints);
        showDetials.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StringBuilder temp = new StringBuilder();

                if (jobQueue.isEmpty())
                {
                    temp.append("No jobs in queue.");
                }

                else
                {
                    temp.append("Job Queue:" + "\n");

                    for (int i = 0; i < jobQueue.size(); i++)
                    {
                        temp.append((i+1) + ": " + jobQueue.get(i) + "\n");
                    }
                }

                final JOptionPane popupMenu = new JOptionPane();
                popupMenu.showMessageDialog(null, temp.toString());

            }
        });

        exit = new Button("Exit");
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = PAGE_END;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 2;
        add(exit,constraints);
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        executeJob = new Button("Run Job");
        constraints.weightx = .5;
        constraints.weighty = 1d;
        constraints.anchor = PAGE_END;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 2;
        add(executeJob,constraints);
        executeJob.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String temp = jobQueue.poll();

                final JOptionPane popupMenu = new JOptionPane();
                popupMenu.showMessageDialog(null, "Started Job: " + temp);

                refreshQueue();
            }
        });

        setTitle("Warehouse Manager");

        pack();

        setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    public void refreshQueue()
    {
        if (!jobQueue.isEmpty())
        {
            nextJob.setText(jobQueue.peek());
        }
        else
        {
            nextJob.setText("Queue Empty");
        }
    }

    public static void main(String[] args)
    {
        new CapstoneServer();
    }
}
