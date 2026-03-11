import java.io.*;
import java.util.*;

public class Main {

    static String FILE = "inventory.dat";

    static Map<String,Integer> loadInventory() {
        Map<String,Integer> inventory = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
            inventory = (Map<String,Integer>) in.readObject();
            in.close();
        }
        catch(Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory = new HashMap<>();
            inventory.put("Single",5);
            inventory.put("Double",3);
            inventory.put("Suite",2);
        }

        return inventory;
    }

    static void saveInventory(Map<String,Integer> inventory) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
            out.writeObject(inventory);
            out.close();
            System.out.println("Inventory saved successfully.");
        }
        catch(Exception e) {
            System.out.println("Error saving inventory.");
        }
    }

    public static void main(String[] args) {

        System.out.println("System Recovery");

        Map<String,Integer> inventory = loadInventory();

        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.get("Single"));
        System.out.println("Double: " + inventory.get("Double"));
        System.out.println("Suite: " + inventory.get("Suite"));

        saveInventory(inventory);
    }
}