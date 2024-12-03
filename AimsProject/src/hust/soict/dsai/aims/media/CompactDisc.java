package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();
    public CompactDisc(String title, String category, float cost, String director, String artist) {
        super(title, category, cost, director, 0); 
        this.artist = artist;
    }
    public CompactDisc(String title, String category, float cost, String artist) {
        super();
        setTitle(title);
        setCategory(category);
        setCost(cost);
        this.artist = artist;
    }
    @Override
    public String toString() {
        return String.format("CD - %s - %s - %s - %s: %.2f $", 
            getTitle(), getCategory(), getDirector(), getArtist(), getCost());
    }
    private void setCost(float cost) {
		// TODO Auto-generated method stub
		
	}

	private void setCategory(String category) {
		// TODO Auto-generated method stub
		
	}

	private void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	public String getArtist() {
        return artist;
    }
    
    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track is already in the CD");
        } else {
            tracks.add(track);
            System.out.println("Track added successfully");
        }
    }
    
    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed successfully");
        } else {
            System.out.println("Track is not in the CD");
        }
    }
    
    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }
    
    @Override
    public void play() {
        System.out.println("Playing CD: " + this.getTitle());
        System.out.println("CD artist: " + this.getArtist());
        System.out.println("CD length: " + this.getLength());
        
        for (Track track : tracks) {
            track.play();
        }
    }
}