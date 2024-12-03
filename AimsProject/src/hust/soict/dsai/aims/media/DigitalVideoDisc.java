package hust.soict.dsai.aims.media;

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

    private void setCost(float cost) {
		// TODO Auto-generated method stub
		
	}

	private void setCategory(String category) {
		// TODO Auto-generated method stub
		
	}

	private void setTitle(String title) {
		// TODO Auto-generated method stub
		
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
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
    
    public String toString() {
        return String.format("DVD - %s - %s - %s - %d: %.2f $", 
            getTitle(), getCategory(), getDirector(), getLength(), getCost());
    }
    
    public boolean isMatch(String title) {
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }
}