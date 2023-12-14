package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Farm {

    private ArrayList<Animal> animals;
    private ArrayList<Crop> crops;
    File saveFolder = new File("save");
    File saveFile = new File("save/saveFile.txt");



    public Farm() {
        this.animals = new ArrayList<>();
        this.crops = new ArrayList<>();
        load();
        if (animals.isEmpty() && crops.isEmpty()) {
            animals.add(new Animal(1, "Cow", "Bessie", new ArrayList<>(Arrays.asList("Wheat", "Corn"))));
            animals.add(new Animal(2, "Chicken", "Lucy", new ArrayList<>(Arrays.asList("Corn", "Seeds"))));
            crops.add(new Crop(1, "Wheat", 100));
            crops.add(new Crop(2, "Corn", 50 ));
        }
    }

    private void load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFile));

            reader.readLine(); // Just to get rid of the first line that is not an object.
            String line = reader.readLine();
            boolean cropList = false;

            while (line != null) {

                if ("cropSaves".equals(line)) {
                    cropList = true;
                    line = reader.readLine();
                }

                if (cropList == false) {
                    String[] variables = line.split(",");

                    int id = Integer.parseInt(variables[0]);
                    String name = variables[1];
                    String species = variables[2];
                    ArrayList<String> acceptableCropTypes = new ArrayList<>(Arrays.asList(variables[3].split("@")));
                    Animal newAnimal = new Animal(id, name, species, acceptableCropTypes);
                    animals.add(newAnimal);
                    line = reader.readLine();
                }
                else {
                        String[] variables = line.split(",");

                        int id = Integer.parseInt(variables[0]);
                        String name = variables[1];
                        int quantity = Integer.parseInt(variables[2]);
                        Crop newCrop = new Crop(id, name, quantity);
                        crops.add(newCrop);
                        line = reader.readLine();
                }

            }

            reader.close();
        } catch (IOException e) {

        }
        animalManager.setAnimals(animals);
        cropManager.setCrops(crops);
    }


    private void save() {
        saveFolder.mkdir();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            writer.write("animalSaves");
            for (Animal animal : animalManager.getAnimals()) {
                writer.newLine();
                writer.write(animal.getCSV());
            }
            writer.newLine();
            writer.write("cropSaves");

            for (Crop crop : crops) {
                writer.newLine();
                writer.write(crop.getCSV());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    AnimalManager animalManager = new AnimalManager();
    CropManager cropManager = new CropManager();

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Main Menu:");
            System.out.println("1. Animal Menu");
            System.out.println("2. Crop Menu");
            System.out.println("3. Save and Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    animalManager.animalMenu(cropManager.getCrops());
                    break;
                case 2:
                    cropManager.cropMenu();
                    break;
                case 3:
                    save();
                    System.out.println("Saved and exited.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);
    }
}


