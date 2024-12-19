package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0;
    
    public DigitalVideoDisc(String title) {
        super();
        setTitle(title);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    
    public DigitalVideoDisc(String title, String category, float cost, String director, int length) {
        super();
        setTitle(title);
        setCategory(category);
        setCost(cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setCategory(String category) {
        this.category = category;
    }

    private void setCost(float cost) {
        this.cost = cost;
    }

	public DigitalVideoDisc(String title, String category, float cost2, String director, float cost) {
        super();
        setTitle(title);
        setCategory(category);
        setCost(cost);
        nbDigitalVideoDiscs++;
        setId(nbDigitalVideoDiscs);
    }
    
    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }
    public String toString() {
        return String.format("DVD - %s - %s - %s - %d: %.2f $", 
            getTitle(), getCategory(), getDirector(), getLength(), getCost());
    }
    
    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }
}