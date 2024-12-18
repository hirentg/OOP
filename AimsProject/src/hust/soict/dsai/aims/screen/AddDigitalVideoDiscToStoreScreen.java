package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;

public class AddDigitalVideoDiscToStoreScreen extends JFrame {
    private Store store;
    private StoreScreen storeScreen;
    private JTextField tfTitle, tfCategory, tfCost, tfDirector, tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        this.store = store;
        this.storeScreen = storeScreen;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(6, 2));

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

        cp.add(new JLabel("Length: "));
        tfLength = new JTextField(20);
        cp.add(tfLength);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addDigitalVideoDisc());
        cp.add(addButton);

        setTitle("Add Digital Video Disc");
        setSize(300, 200);
        setVisible(true);
    }

    private void addDigitalVideoDisc() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String director = tfDirector.getText();
        int length = Integer.parseInt(tfLength.getText());

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, cost, director, length);

        store.addMedia(dvd);
        storeScreen.refreshStoreDisplay();
        JOptionPane.showMessageDialog(this, "Digital Video Disc added successfully");
        dispose();
    }
}