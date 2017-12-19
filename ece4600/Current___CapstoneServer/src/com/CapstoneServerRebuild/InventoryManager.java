package com.CapstoneServerRebuild;

import java.util.LinkedList;
import java.util.Vector;

public class InventoryManager
{
    // location dimensions, x = # of rows, y = # of segments per shelf, z = # of vertical shelves
    // defaults for debugging.
    private static int x = 2;
    private static int y = 4;
    private static int z = 2;

    private static InventoryPosition[][][] inventoryStock;
    private WorkQueue workQueue;

    // def constructor
    InventoryManager()
    {
        inventoryStock = new InventoryPosition[x][y][z];
        workQueue = new WorkQueue();

        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++) {

                    inventoryStock[i][j][k] = new InventoryPosition(i, j, k);
                }
            }
        }
    }
    public boolean jobAvailable()
    {
        if (workQueue.queueEmpty())
        {
            return false;
        }
        else
            return true;
    }

    public void setWorking(int userId)
    {
        String temp = Integer.toString(userId);

    }







    public int[] findItem(int partNum)
    {
        // searches for item matching partNum
        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++) {
                    if (inventoryStock[i][j][k].getOccupied()) {
                        if (inventoryStock[i][j][k].getPartNumber() == partNum) {
                            int[] success = {i, j, k};
                            return success;
                        }
                    }
                }
            }
        }
        // no item with partNum exists
        return null;
    }

    void putItem(int part) {
        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++)
                {
                    if (!inventoryStock[i][j][k].getOccupied())
                    {
                        inventoryStock[i][j][k].setPartNumber(part);

                    }
                }
            }
        }
    }

    void getItem(int part)
    {
        int[] loc = findItem(part);

        // send location to robot

        String temp = Integer.toString(part);
        workQueue.addToQueue(temp, loc.toString());
    }
}

class WorkQueue
{
    LinkedList<String> workQueue;


    WorkQueue()
    {
        workQueue = new LinkedList<>();
    }

    public boolean queueEmpty()
    {
        return workQueue.isEmpty();
    }

    void addToQueue(String job, String value)
    {
        workQueue.addLast(job + "," + value);
    }

    public String getJobFromQueue()
    {
        // called by
        String newJob = workQueue.poll();
        return newJob;
    }

    LinkedList<String> getWorkQueue()
    {
        return workQueue;
    }
}

class InventoryPosition
{
    static int[] location = new int[3];
    static boolean isOccupied = false;
    static int partNumber = 0;

    InventoryPosition(int x, int y, int z)
    {
        location[0] = x;
        location[1] = y;
        location[2] = z;
    }

    boolean getOccupied()
    {
        return isOccupied;
    }

    int getPartNumber()
    {
        return partNumber;
    }

    void setPartNumber(int PartNumber)
    {
        partNumber = PartNumber;

        if (partNumber == 0)
        {
            isOccupied = false;
        }
        else
            isOccupied = true;
    }
}