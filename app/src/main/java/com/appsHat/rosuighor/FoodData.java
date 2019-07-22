package com.appsHat.rosuighor;

public class FoodData {
    private String itemName;
    private String itemDescription;
    private String itemCategory;
    private String itemImage;

    public FoodData(){

    }


    public FoodData(String itemName, String itemDescription, String itemCategory, String itemImage) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;

    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }


}
