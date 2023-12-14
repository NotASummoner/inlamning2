package org.example;

import java.util.ArrayList;

public class Animal extends Entity {

    private String species;
    private ArrayList<String> acceptableCropTypes;

    public Animal(int id, String name, String species, ArrayList<String> acceptableCropTypes) {
        super(id, name);
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Species: " + species + ", Acceptable Crop Types: " + acceptableCropTypes;
    }

    @Override
    public  String getCSV() {
        String acceptableCropsCSV = String.join("@", acceptableCropTypes);
        return super.getCSV() + "," + species + "," + acceptableCropsCSV;
    }

    public boolean feed(Crop crop) {
        String cropType = crop.getName();
        if (acceptableCropTypes.contains(cropType)) {
            if (crop.takeCrop()) {
                System.out.println("Animal fed successfully.");
                return true;
            } else {
                System.out.println("No more of this crop type available.");
            }
        } else {
            System.out.println("Animal does not eat this crop type.");
        }
        return false;
    }


}
