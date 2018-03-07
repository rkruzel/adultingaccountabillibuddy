package com.CapstoneServerRebuild;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import static java.awt.GridBagConstraints.CENTER;


public class CapstoneServer extends Frame
{
    // manager classes
    public static InventoryManager    InventoryManager;
    public static SocketManager       SocketManager;

    private Label partTitle, queueTitle;
    private TextField nextJob, partNum;
    private Button getPart, putPart, executeJob, exit, showDetials;
    private Checkbox autoRun;

    public LinkedList<String> jobQueue;




    public CapstoneServer()
    {
        // instantiate singletons and get instance
        InventoryManager inventoryManager = InventoryManager.getInstance();
        SocketManager socketManager = SocketManager.getInstance();

        jobQueue = new LinkedList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        partTitle = new Label("Part #");
        constraints.anchor = CENTER;
        partTitle.setFont(new Font("Serif",Font.BOLD,20));
        partTitle.setPreferredSize(new Dimension(100,20));
        constraints.insets = new Insets(20,5,5,5);
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(partTitle, constraints);

        autoRun = new Checkbox("Auto-Run Job",false);
        constraints.anchor = CENTER;
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 3;
        constraints.gridy = 1;
        autoRun.setEnabled(false);
        add(autoRun,constraints);

        queueTitle = new Label("Next Job");
        constraints.anchor = CENTER;
        queueTitle.setFont(new Font("Serif",Font.BOLD,20));
        queueTitle.setPreferredSize(new Dimension(100,20));
        constraints.insets = new Insets(20,5,5,5);
        constraints.gridx = 4;
        constraints.gridy = 0;
        add(queueTitle,constraints);

        nextJob = new TextField("Queue Empty", 10);
        nextJob.setEditable(false);
        constraints.anchor = CENTER;
        nextJob.setPreferredSize(new Dimension(100,10));
        constraints.insets = new Insets(5,5,5,5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 1;
        add(nextJob, constraints);

        partNum = new TextField("", 10);
        constraints.anchor = CENTER;
        partNum.setPreferredSize(new Dimension(100,10));
        constraints.insets = new Insets(5,5,5,5);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(partNum, constraints);


        getPart = new Button("Retrieve Part");
        constraints.anchor = CENTER;
        getPart.setPreferredSize(new Dimension(100,60));
        constraints.insets = new Insets(5,5,0,5);
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
                    StringBuilder temp = new StringBuilder("GET:");
                    temp.append(partNum.getText());
                    jobQueue.add(temp.toString());
                    partNum.setText("");
                    refreshQueue();
                }
            }
        });

        putPart = new Button("Store Part");
        constraints.insets = new Insets(5,0,0,0);
        putPart.setPreferredSize(new Dimension(100,60));
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
                    StringBuilder temp = new StringBuilder("STORE:");
                    temp.append(partNum.getText());
                    jobQueue.add(temp.toString());
                    refreshQueue();
                    partNum.setText("");
                }
            }
        });

        showDetials = new Button("Show Details");
        constraints.insets = new Insets(5,0,0,0);
        showDetials.setPreferredSize(new Dimension(100,60));
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
        constraints.anchor = CENTER;
        exit.setPreferredSize(new Dimension(100,60));
        constraints.insets = new Insets(5,0,0,0);
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
        constraints.insets = new Insets(5,0,0,0);
        constraints.anchor = CENTER;
        constraints.gridx = 3;
        executeJob.setPreferredSize(new Dimension(100,60));
        constraints.gridy = 2;
        add(executeJob,constraints);
        executeJob.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jobQueue.isEmpty())
                {
                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "Queue empty.");
                }

                else
                {
                    String tempMsg = jobQueue.poll();
                    String[] temp = formatString(tempMsg);

                    switch(temp[0])
                    {
                        case ("GET"):
                            String message = inventoryManager.getItem(temp[1]);
                            socketManager.messageOut(message);
                            break;
                        case ("STORE"):
                            inventoryManager.putItem(temp[1]);
                            break;
                        default:
                            break;
                    }

                    final JOptionPane popupMenu = new JOptionPane();
                    popupMenu.showMessageDialog(null, "Started Job: " + tempMsg);
                }
                refreshQueue();
            }
        });

        setTitle("Warehouse Manager - G6 ECE4600");
        setMinimumSize(new Dimension(500,200));
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

    public String[] formatString(String string)
    {
        String[] temp = string.split(":");
        return temp;
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
