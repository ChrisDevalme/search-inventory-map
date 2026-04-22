package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class SearchInventoryMap {

    public static final String fileName = "inventory.csv";

    public static void main(String[] args) {
        Map<Integer, String> inventory = loadInventory();
        LookUpById(inventory);

    }

    public static void LookUpById(Map<Integer, String> inventory) {
        System.out.print("Enter Item ID :");
        Scanner scanner = new Scanner(System.in);
        int itemId = scanner.nextInt();
        for (Integer i : inventory.keySet()) {
            if (i == itemId) {
                System.out.println(inventory.get(i));
            }
        }
    }

    public static Map<Integer, String> loadInventory() {
        Map<Integer, String> inventory = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = bf.readLine()) != null) {
                String[] productString = line.split("\\|");
                int productID = Integer.parseInt(productString[0]);
                String productName = productString[1];
                double productPrice = Double.parseDouble(productString[2]);
                Product newProduct = new Product(productID, productName, productPrice);
                inventory.put(productID,newProduct.getName());
            }
            bf.close();

        } catch (Exception e) {
            System.out.println("Invalid File.");
        }

        return inventory;
    }
}
