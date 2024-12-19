package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.screen.StoreScreen;
import hust.soict.dsai.aims.store.Store;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        initializeStore();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StoreScreen(store, cart);
            }
        });
    }

    private static void initializeStore() {
        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f, "Roger Allers", 87));        
        store.addMedia(new Book("Star Wars", "Science Fiction", 24.95f));
    }
}