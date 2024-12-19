package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Track;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;

public class AddCompactDiscToStoreScreen extends JFrame {
    private Store store;
    private StoreScreen storeScreen;

    private JTextField tfTitle, tfCategory, tfCost, tfDirector, tfArtist, tfTracks;

    public AddCompactDiscToStoreScreen(Store store, StoreScreen storeScreen) {
        this.store = store;
        this.storeScreen = storeScreen;

        Container cp = getContentPane();
        cp.setLayout(new GridLayout(7, 2));

        cp.add(new JLabel("Title: "));
        tfTitle = new JTextField(20);
        cp.add(tfTitle);

        cp.add(new JLabel("Category: "));
        tfCategory = new JTextField(20);
        cp.add(tfCategory);

        cp.add(new JLabel("Cost: "));
        tfCost = new JTextField(20);
        cp.add(tfCost);

        cp.add(new JLabel("Director: "));
        tfDirector = new JTextField(20);
        cp.add(tfDirector);

        cp.add(new JLabel("Artist: "));
        tfArtist = new JTextField(20);
        cp.add(tfArtist);

        cp.add(new JLabel("Tracks (comma-separated): "));
        tfTracks = new JTextField(20);
        cp.add(tfTracks);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addCompactDisc());
        cp.add(addButton);

        setTitle("Add Compact Disc");
        setSize(300, 250);
        setVisible(true);
    }

    private void addCompactDisc() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String director = tfDirector.getText();
        String artist = tfArtist.getText();
        String[] tracks = tfTracks.getText().split(",");

        CompactDisc cd = new CompactDisc(title, category, cost, director, artist);
        for (String track : tracks) {
            cd.addTrack(new Track(track.trim(), 0)); 
        }

        store.addMedia(cd);
        storeScreen.refreshStoreDisplay();
        JOptionPane.showMessageDialog(this, "Compact Disc added successfully");
        dispose();
    }
}