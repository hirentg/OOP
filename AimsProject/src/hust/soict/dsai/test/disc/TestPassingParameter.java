package hust.soict.dsai.test.disc;

import hust.soict.dsai.aims.media.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        // Use an array to hold the two objects
        DigitalVideoDisc[] dvds = {jungleDVD, cinderellaDVD};

        // Thực hiện hàm swap
        swap(dvds);
        System.out.println("Jungle dvd title: " + dvds[0].getTitle());
        System.out.println("Cinderella dvd title: " + dvds[1].getTitle());

        // Thực hiện hàm changeTitle
        changeTitle(dvds[0], dvds[1].getTitle());
        System.out.println("Jungle dvd title: " + dvds[0].getTitle());
    }

    public static void swap(DigitalVideoDisc[] dvds) {
        DigitalVideoDisc temp = dvds[0];
        dvds[0] = dvds[1];
        dvds[1] = temp;
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}