package org.example;

public class Crop extends Entity{

    private int quantity;

    public Crop(int id, String name, int quantity) {
        super(id, name);
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Quantity: " + quantity;
    }

    @Override
    public  String getCSV() {
        return super.getCSV() + "," + quantity;
    }

    public boolean takeCrop() {
        if (quantity >= 1 ){
            quantity --;
            return true;
        } else {
            return false;
        }
    }

    public void addCrop(int quantity) {
        this.quantity += quantity;

    }

    public String getName() {
        return name;
    }
}
