package hust.soict.dsai.aims.media;

import java.util.ArrayList;
import java.util.Arrays;

public class Book extends Media {
    private ArrayList<String> authors = new ArrayList<String>();
    
        
    public Book(String title) {
        super(title);
    }
    
    public void addAuthor(String author) {
        if (author != null && !author.trim().isEmpty() && !authors.contains(author)) {
            authors.add(author);
        }
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