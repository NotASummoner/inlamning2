package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> crops;

    public void cropMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Crop Menu:");
            System.out.println("1. View Crops");
            System.out.println("2. Add Crop");
            System.out.println("3. Remove Crop");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCrops();
                    break;
                case 2:
                    addCrop();
                    break;
                case 3:
                    removeCrop();
                    break;
                case 4:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);
    }

    private void viewCrops() {
        System.out.println("List of Crops:");
        for (Crop crop : crops) {
            System.out.println(crop.getDescription());
        }
    }

    public void addCrop() {
        Scanner scanner = new Scanner(System.in);
        boolean added = false;

        System.out.println("Enter the id of the crop:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character left in the buffer

        for (int i = 0; i < crops.size(); i++) {
            if (crops.get(i).getId() == id) {

                System.out.println("Enter the quantity:");

                int quantity = scanner.nextInt();

                crops.get(i).addCrop(quantity);

                added = true;

                System.out.println("Crop added successfully!");

                break;
            }
        }
        if (!added) {

            System.out.println("Enter the name of the crop:");
            String name = scanner.nextLine();

            System.out.println("Enter the quantity:");
            int quantity = scanner.nextInt();

            // Create the Crop object
            Crop newCrop = new Crop(id, name, quantity);

            // Add the new crop to the list
            crops.add(newCrop);

            System.out.println("Crop added successfully!");
        }

    }

    private void removeCrop() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the crop to remove:");
        int idToRemove = scanner.nextInt();

        boolean removed = false;

        // Iterate through the crops list to find the crop with the specified ID
        for (int i = 0; i < crops.size(); i++) {
            if (crops.get(i).getId() == idToRemove) {

                // Remove the crop from the list
                crops.remove(i);

                removed = true;

                System.out.println("Crop removed successfully!");

                break; // Exit the loop once the crop is found and removed
            }
        }

        // If the crop with the specified ID was not found
        if (!removed) {
            System.out.println("Crop with ID " + idToRemove + " not found.");
        }
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public void setCrops(ArrayList<Crop> crops) {
        this.crops = crops;
    }

}
