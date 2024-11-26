   public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%d. %s\n", i + 1, itemsOrdered[i].toString());
        }
        System.out.printf("Total cost: %.2f $\n", totalCost());
        System.out.println("***********************************************");
    }
