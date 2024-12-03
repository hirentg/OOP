package hust.soict.dsai.aims.media;

public abstract class Disc extends Media {
    private int length;
    private String director;
    
    public Disc() {
        super();
    }
    
    public Disc(String title, String category, float cost, String director, int length) {
        super(title, category, cost);
        this.director = director;
        this.length = length;
    }
    
    public int getLength() { return length; }
    public String getDirector() { return director; }
}