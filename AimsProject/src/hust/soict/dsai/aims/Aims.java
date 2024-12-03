package hust.soict.dsai.aims;

import java.util.Scanner;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.media.*;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        showMenu();
    }
    
    public static void showMenu() {
        while (true) {
            System.out.println("AIMS: ");
            System.out.println("--------------------------------");
            System.out.println("1. View store");
            System.out.println("2. Update store");
            System.out.println("3. See current cart");
            System.out.println("0. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");
            
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
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
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
            System.out.println("Please choose a number: 0-1-2-3-4");

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
                    seeCurrentCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void seeMediaDetails() {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        
        if (media != null) {
            System.out.println(media.toString());
            mediaDetailsMenu(media);
        } else {
            System.out.println("Media not found!");
        }
    }

    private static void mediaDetailsMenu(Media media) {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add to cart");
            System.out.println("2. Play");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    break;
                case 2:
                    if (media instanceof Playable) {
                        ((Playable) media).play();
                    } else {
                        System.out.println("This media is not playable");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addMediaToCart() {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        
        if (media != null) {
            cart.addMedia(media);
            System.out.println("Number of items in cart: " + cart.getSize());
        } else {
            System.out.println("Media not found!");
        }
    }

    private static void playMedia() {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        
        if (media != null) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            } else {
                System.out.println("This media is not playable");
            }
        } else {
            System.out.println("Media not found!");
        }
    }

    private static void updateStore() {
        while (true) {
            System.out.println("Options: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add media to store");
            System.out.println("2. Remove media from store");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            
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
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addMediaToStore() {
        System.out.println("Enter media type (1: Book, 2: CD, 3: DVD): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter title: ");
        String title = scanner.nextLine();
        
        System.out.println("Enter category: ");
        String category = scanner.nextLine();
        
        System.out.println("Enter cost: ");
        float cost = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Media media;
        switch (type) {
            case 1:
                media = new Book(title, category, cost);
                break;
            case 2:
                System.out.println("Enter artist: ");
                String artist = scanner.nextLine();
                System.out.println("Enter director: ");
                String director = scanner.nextLine();
                media = new CompactDisc(title, category, cost, director, artist);
                break;
            case 3:
                System.out.println("Enter director: ");
                director = scanner.nextLine();
                System.out.println("Enter length: ");
                int length = scanner.nextInt();
                media = new DigitalVideoDisc(title, category, cost, director, length);
                break;
            default:
                System.out.println("Invalid media type!");
                return;
        }
        store.addMedia(media);
    }

    private static void removeMediaFromStore() {
        System.out.println("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = store.search(title);
        
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
            System.out.println("Please choose a number: 0-1-2-3-4-5");

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
                    playMediaInCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void filterMediasInCart() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by title");
        System.out.println("--------------------------------");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Enter ID: ");
                int id = scanner.nextInt();
                cart.searchById(id);
                break;
            case 2:
                System.out.println("Enter title: ");
                String title = scanner.nextLine();
                cart.searchByTitle(title);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void sortMediasInCart() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Sort by title");
        System.out.println("2. Sort by cost");
        System.out.println("--------------------------------");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                break;
            case 2:
                cart.sortByCostTitle();
                break;
            default:
                System.out.println("Invalid choice!");
        }
        cart.print();
    }

    private static void removeMediaFromCart() {
        System.out.println("Enter the title of the media to remove: ");
        String title = scanner.nextLine();
        Media media = cart.search(title);
        
        if (media != null) {
            cart.removeMedia(media);
            System.out.println("Media removed successfully");
        } else {
            System.out.println("Media not found!");
        }
    }

    private static void playMediaInCart() {
        System.out.println("Enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = cart.search(title);
        
        if (media != null) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            } else {
                System.out.println("This media is not playable");
            }
        } else {
            System.out.println("Media not found!");
        }
    }

    private static void placeOrder() {
        System.out.println("Order is created");
        cart.empty();
    }}