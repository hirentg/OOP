package aims;

public class Store {
    private static final int MAX_ITEMS = 100;
    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_ITEMS];
    private int itemCount = 0;

    public void addDVD(DigitalVideoDisc disc) {
        if (itemCount < MAX_ITEMS) {
            itemsInStore[itemCount] = disc;
            itemCount++;
            System.out.println("DVD added to store successfully");
        } else {
            System.out.println("Store is full");
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        for (int i = 0; i < itemCount; i++) {
            if (itemsInStore[i] == disc) {
                // Shift elements to remove the DVD
                for (int j = i; j < itemCount - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }
                itemsInStore[itemCount - 1] = null;
                itemCount--;
                System.out.println("DVD removed from store successfully");
                return;
            }
        }
        System.out.println("DVD not found in store");
    }

    public void print() {
        System.out.println("***********************STORE***********************");
        System.out.println("Items in store:");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%d. %s\n", i + 1, itemsInStore[i].toString());
        }
        System.out.println("***********************************************");
    }
}
