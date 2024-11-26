package hust.soict.dsai.disc;

public class DigitalVideoDisc {
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;
    
    // Add new class member
    private static int nbDigitalVideoDiscs = 0;
    
    // Add instance member for id
    private int id;

    public DigitalVideoDisc(String title) {
        this.title = title;
        // Increment count and set id
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }
    
    public DigitalVideoDisc(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }
    
    public int getId() {
        return id;
    }
    
    // Add setter for testing
    public void setTitle(String title) {
        this.title = title;
    }
    
    // Add toString method
    public String toString() {
        return String.format("DVD - %s - %s - %s - %d: %.2f $", 
            title, category, director, length, cost);
    }
    
    // Add matching method
    public boolean isMatch(String title) {
        return this.title.toLowerCase().contains(title.toLowerCase());
    }
}