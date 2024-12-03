
package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        // Create sample DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "Ron Clements", 90, 18.99f);

        // Create a store and add DVDs
        Store store = new Store();
        store.addDVD(dvd1);
        store.addDVD(dvd2);

        // Print store contents
        System.out.println("Store contents after adding DVDs:");
        store.print();

        // Test removing a DVD
        System.out.println("\nRemoving 'Star Wars' from store:");
        store.removeDVD(dvd2);
        store.print();

        // Attempt to remove a DVD not in store
        System.out.println("\nAttempting to remove 'Aladdin' from store:");
        store.removeDVD(dvd3);
        store.print();

        // Add another DVD
        System.out.println("\nAdding 'Aladdin' to store:");
        store.addDVD(dvd3);
        store.print();
    }
}
