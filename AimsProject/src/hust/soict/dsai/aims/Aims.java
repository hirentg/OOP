package hust.soict.dsai.aims;

import java.util.List;
import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.screen.StoreScreen;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeStore();
        showMenu();
    }

    private static void initializeStore() {
        // Add some sample items to the store
    	store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", 19.95f, "Roger Allers", 87));        
    	store.addMedia(new Book("Star Wars", "Science Fiction", 24.95f));
        store.addMedia(new CompactDisc("Greatest Hits", "Pop", 19.95f, "Michael Jackson"));
    }

    private static void showMenu() {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("4. Open GUI Store");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 4:
                    openGUIStore();
                    break;
                case 0:
                    System.out.println("Thanks for using our software!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewStore() {
        store.print();
        storeMenu();
    }

    private static void storeMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. See a media's details");
            System.out.println("2. Add a media to cart");
            System.out.println("3. Play a media");
            System.out.println("4. See current cart");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    cart.print();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void updateStore() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add new media");
            System.out.println("2. Remove media from store");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMediaToStore();
                    break;
                case 2:
                    removeMediaFromStore();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMediaToStore() {
        System.out.println("Enter media type (1: Book, 2: CD, 3: DVD): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Media media;
        switch (type) {
            case 1:
                media = new Book(title, category, cost);
                System.out.print("Enter authors (comma-separated): ");
                String authors = scanner.nextLine();
                for (String author : authors.split(",")) {
                    ((Book)media).addAuthor(author.trim());
                }
                break;
            case 2:
                System.out.print("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.print("Enter director: ");
                String director = scanner.nextLine();
                media = new CompactDisc(title, category, cost, director, artist);
                break;
            case 3:
                System.out.print("Enter director: ");
                director = scanner.nextLine();
                System.out.print("Enter length: ");
                int length = scanner.nextInt();
                media = new DigitalVideoDisc(title, category, cost, director, length);
                break;
            default:
                System.out.println("Invalid media type!");
                return;
        }
        store.addMedia(media);
        System.out.println("Media added successfully.");
    }

    private static void removeMediaFromStore() {
        System.out.print("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = store.searchByTitle(title);
        
        if (media != null) {
            store.removeMedia(media);
            System.out.println("Media removed successfully");
        } else {
            System.out.println("Media not found!");
        }
    }
    private static void seeCurrentCart() {
        cart.print();
        cartMenu();
    }

    private static void cartMenu() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Filter medias in cart");
            System.out.println("2. Sort medias in cart");
            System.out.println("3. Remove media from cart");
            System.out.println("4. Play a media");
            System.out.println("5. Place order");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.print("Please choose a number: 0-1-2-3-4-5: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    filterMediasInCart();
                    break;
                case 2:
                    sortMediasInCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void openGUIStore() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StoreScreen(store, cart);
            }
        });
    }
    // Implement the following methods:
    private static void seeMediaDetails() {
        System.out.print("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        if (media != null) {
            System.out.println(media.toString());
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void addMediaToCart() {
        System.out.print("Enter the title of the media to add to cart: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Added " + media.getTitle() + " to cart.");
        } else {
            System.out.println("Media not found.");
        }
    }

    private static void playMedia() {
        System.out.print("Enter the title of the media to play: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.out.println("Error playing media: " + e.getMessage());
            }
        } else {
            System.out.println("This media is not playable.");
        }
    }

    private static void filterMediasInCart() {
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter filter value: ");
        String filterValue = scanner.nextLine();

        List<Media> filteredMedia;
        if (choice == 1) {
            filteredMedia = cart.searchById(Integer.parseInt(filterValue));
        } else {
            filteredMedia = cart.searchByTitle(filterValue);
        }

        for (Media media : filteredMedia) {
            System.out.println(media.toString());
        }
    }

    private static void sortMediasInCart() {
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Cost");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            cart.sortByTitleCost();
        } else {
            cart.sortByCostTitle();
        }
        cart.print();
    }

    private static void removeMediaFromCart() {
        System.out.print("Enter the title of the media to remove from cart: ");
        String title = scanner.nextLine();
        Media media = (Media) cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Removed " + media.getTitle() + " from cart.");
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    private static void playMediaFromCart() {
        System.out.print("Enter the title of the media to play from cart: ");
        String title = scanner.nextLine();
        Media media = (Media) cart.searchByTitle(title);
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                System.out.println("Error playing media: " + e.getMessage());
            }
        } else {
            System.out.println("This media is not playable or not found in cart.");
        }
    }

    private static void placeOrder() {
        System.out.println("Order placed successfully. Total cost: $" + cart.totalCost());
        cart.empty();
    }
}