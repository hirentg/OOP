package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import java.util.List;

import hust.soict.dsai.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();
    
    public void addMedia(Media media) {
        if (media != null && !itemsInStore.contains(media)) {
            itemsInStore.add(media);
            System.out.println("The media has been added to the store");
        }
    }
    
    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("The media has been removed from the store");
        } else {
            System.out.println("The media was not found in the store");
        }
    }

    public Media search(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle() != null && media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return media;
            }
        }
        return null;
    }
    public List<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore);
    }
    public Media searchByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return media;
            }
        }
        return null;
    }
    public void print() {
        System.out.println("***********************STORE***********************");
        System.out.println("Store Items:");
        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
        }
        System.out.println("***********************************************");
    }}