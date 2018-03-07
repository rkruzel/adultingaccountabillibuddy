package com.CapstoneServerRebuild;

import javax.swing.*;


public class InventoryManager
{
    private static InventoryManager inventoryManager;


    // location dimensions, x = # of rows, y = # of segments per shelf, z = # of vertical shelves
    // defaults for debugging.
    private static int x = 1;
    private static int y = 2;
    private static int z = 3;

    private static InventoryPosition[][][] inventoryStock;


    // Singleton implimentation of static class
    public static synchronized InventoryManager getInstance()
    {
        if (inventoryManager == null)
        {
            inventoryManager = new InventoryManager();
        }
        return inventoryManager;
    }

    private InventoryManager()
    {
        // create 3 dimension array container for inventory position objects
        inventoryStock = new InventoryPosition[x][y][z];

        // fill array according to size with defaults
        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++) {

                    inventoryStock[i][j][k] = new InventoryPosition(i, j, k);
                }
            }
        }
    }


    public StringBuilder findItem(String partNum)
    {
        // searches for item matching partNum
        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++) {
                    if (inventoryStock[i][j][k].getOccupied()) {
                        if (inventoryStock[i][j][k].getPartNumber() == partNum) {
                            StringBuilder temp = new StringBuilder();
                            temp.append(String.valueOf(i));
                            temp.append(String.valueOf(j));
                            temp.append(String.valueOf(k));
                            temp.append("}");
                            return temp;
                        }
                    }
                }
            }
        }
        // no item with partNum exists

        return null;
    }

    public void putItem(String part) {
        for (int i = 0; i < inventoryStock.length; i++) {
            for (int j = 0; j < inventoryStock[i].length; j++) {
                for (int k = 0; k < inventoryStock[i][j].length; k++)
                {
                    if (!inventoryStock[i][j][k].getOccupied())
                    {
                        inventoryStock[i][j][k].setPartNumber(part);
                        break;
                    }
                }
            }
        }
    }



    public String getItem(String Part)
    {
        StringBuilder temp = new StringBuilder();
        temp.append("{a");

        try
        {
            temp.append(findItem(Part));
            return temp.toString();
        }
        catch (NullPointerException e)
        {
            final JOptionPane popupMenu = new JOptionPane();
            popupMenu.showMessageDialog(null, "No Item Found");
            return "0";
        }
    }
}



class InventoryPosition
{
    static int[] location = new int[3];
    static boolean isOccupied = false;
    static String partNumber = "";

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

    String getPartNumber()
    {
        return partNumber;
    }

    void setPartNumber(String PartNumber)
    {
        partNumber = PartNumber;

        if (partNumber.equals(""))
        {
            isOccupied = false;
        }
        else
            isOccupied = true;
    }
}