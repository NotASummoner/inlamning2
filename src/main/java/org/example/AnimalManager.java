package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimalManager {
    private ArrayList<Animal> animals;
    private ArrayList<Crop> crops;

    public AnimalManager() {
        this.animals = new ArrayList<>();
    }

    public void animalMenu(ArrayList<Crop> crops) {
        this.crops = crops;
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Animal Menu:");
            System.out.println("1. View Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Remove Animal");
            System.out.println("4. Feed Animals");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAnimals();
                    break;
                case 2:
                    addAnimal();
                    break;
                case 3:
                    removeAnimal();
                    break;
                case 4:
                    feedAnimals();
                    break;
                case 5:
                    System.out.println("Returning to Main Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }


    private void viewAnimals() {
        System.out.println("List of Animals:");
        for (Animal animal : animals) {
            System.out.println(animal.getDescription());
        }
    }

    public void addAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the id of the animal:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the name of the species:");
        String species = scanner.nextLine();

        System.out.println("Enter the name of the animal:");
        String name = scanner.nextLine();

        System.out.println("Enter what crops the animal can eat (comma-separated):");
        String acceptableCropTypesInput = scanner.nextLine();

        // Convert the comma-separated input to a list of acceptable crop types
        ArrayList<String> acceptableCropTypes = new ArrayList<>(Arrays.asList(acceptableCropTypesInput.split(",")));

        // Create the Animal object
        Animal newAnimal = new Animal(id, name, species, acceptableCropTypes);

        // Add the new animal to the list
        animals.add(newAnimal);

        System.out.println("Animal added successfully!");
    }


    private void removeAnimal() {
        Scanner scanner = new Scanner(System.in);

        viewAnimals();

        System.out.println("Enter the ID of the animal to remove:");
        int idToRemove = scanner.nextInt();

        boolean removed = false;

        // Iterate through the animals list to find the animal with the specified ID
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == idToRemove) {
                // Remove the animal from the list
                animals.remove(i);
                removed = true;
                System.out.println("Animal removed successfully!");
                break; // Exit the loop once the animal is found and removed
            }
        }

        // If the animal with the specified ID was not found
        if (!removed) {
            System.out.println("Animal with ID " + idToRemove + " not found.");
        }
    }


    private void feedAnimals() {
        Scanner scanner = new Scanner(System.in);
        int cropIndex = 0;
        boolean cropFound = false;
        boolean animalFound = false;

        System.out.println("List of Crops:");
        for (Crop crop : crops) {
            System.out.println(crop.getDescription());
        }

        System.out.println("Enter ID of crop to feed to animal");

        int cropId = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < crops.size(); i++) {
            if (crops.get(i).getId() == cropId) {
                cropIndex = i;
                cropFound = true;
                System.out.println("Crop chosen!");
                break;
            }
        }
        if (!cropFound) {
            System.out.println("Crop with ID " + cropId + " not found.");
            return;
        }
        viewAnimals();
        System.out.println("Enter ID of animal to feed");

        int animalId = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == animalId) {

                animals.get(i).feed(crops.get(cropIndex));
                animalFound = true;
                break;
            }
        }

        if (!animalFound) {
            System.out.println("Animal with ID " + animalId + " not found.");
        }

    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
}
