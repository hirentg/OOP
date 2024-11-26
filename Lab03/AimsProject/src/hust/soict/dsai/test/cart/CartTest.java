package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.disc.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        // Create sample DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 124, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", "Ron Clements", 90, 18.99f);

        // Create a cart and add DVDs
        Cart cart = new Cart();
        cart.addDigitalVideoDisc(new DigitalVideoDisc[]{dvd1, dvd2});
        cart.addDigitalVideoDisc(new DigitalVideoDisc[]{dvd3});

        // Print cart contents
        System.out.println("Cart after adding DVDs:");
        cart.print();

        // Test removing a DVD
        System.out.println("\nRemoving 'Star Wars' from cart:");
        cart.removeDigitalVideoDisc(dvd2);
        cart.print();

        // Test searching for DVDs by ID
        System.out.println("\nSearching for DVD with ID 1:");
        cart.searchById(1);

        System.out.println("\nSearching for DVD with ID 5:");
        cart.searchById(5);

        // Test searching for DVDs by title
        System.out.println("\nSearching for DVD with title containing 'Lion':");
        cart.searchByTitle("Lion");

        System.out.println("\nSearching for DVD with title containing 'Frozen':");
        cart.searchByTitle("Frozen");
    }
}
