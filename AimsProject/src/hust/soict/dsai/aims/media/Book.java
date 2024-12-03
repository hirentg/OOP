package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<String>();
    
    public Book(String title) {
        super(title);
    }
    
    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }
    
    public Book(String title, String category, float cost, ArrayList<String> authors) {
        super(title, category, cost);
        if (authors != null) {
            this.authors = new ArrayList<String>(authors);
        }
    }
}