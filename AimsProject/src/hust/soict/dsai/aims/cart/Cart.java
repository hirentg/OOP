package hust.soict.dsai.aims.cart;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import hust.soict.dsai.aims.media.Media;

public class Cart {
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();
    
    public void addMedia(Media media) {
        if (media != null) {
            itemsOrdered.add(media);
            System.out.println("The media has been added");
        }
    }
    
    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed");
        } else {
            System.out.println("The media was not found");
        }
    }
    
    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }
    
    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i).toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***********************************************");
    }

    public void empty() {
        itemsOrdered.clear();
        System.out.println("Cart has been emptied");
    }

    public Media search(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return media;
            }
        }
        return null;
    }

    public String getSize() {
        return String.valueOf(itemsOrdered.size());
    }

    public void sortByTitleCost() {
        FXCollections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        FXCollections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public List<Media> searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println(media.toString());
                return itemsOrdered;
            }
        }
        System.out.println("No media found with ID: " + id);
        return itemsOrdered;
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }
    
    public void placeOrder() {
        itemsOrdered.clear();
        System.out.println("An order has been created");
    }
    
    public List<Media> searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No media found with title: " + title);
        }
        return itemsOrdered;
    }
}