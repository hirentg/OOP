package hust.soict.dsai.aims.media;

import java.util.Comparator;

public abstract class Media {
    private int id;
    public String title;
    public String category;
    public float cost;
    
    public Media() {
    }
    
    public Media(String title) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = 
    	    Comparator.comparing(Media::getTitle)
    	              .thenComparing(Media::getCost, Comparator.reverseOrder());

    	public static final Comparator<Media> COMPARE_BY_COST_TITLE = 
    	    Comparator.comparing(Media::getCost, Comparator.reverseOrder())
    	              .thenComparing(Media::getTitle);
    public Media(String title, String category, float cost) {
        this.title = title;
        this.category = category;
        this.cost = cost;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        try {
            Media media = (Media) obj;
            return this.getTitle().equals(media.getTitle());
        } catch (ClassCastException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }
    
    
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public float getCost() { return cost; }
    
    protected void setId(int id) { this.id = id; }
}