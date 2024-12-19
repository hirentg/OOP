package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.store.Store;

import java.awt.*;

public class AddBookToStoreScreen extends JFrame {
    private Store store;
    private StoreScreen storeScreen;
    private JTextField tfTitle, tfCategory, tfCost, tfAuthors;

    public AddBookToStoreScreen(Store store, StoreScreen storeScreen) {
        this.store = store;
        this.storeScreen = storeScreen;

        Container cp = getContentPane();
        cp.setLayout(new GridLayout(5, 2));

        cp.add(new JLabel("Title: "));
        tfTitle = new JTextField(20);
        cp.add(tfTitle);

        cp.add(new JLabel("Category: "));
        tfCategory = new JTextField(20);
        cp.add(tfCategory);

        cp.add(new JLabel("Cost: "));
        tfCost = new JTextField(20);
        cp.add(tfCost);

        cp.add(new JLabel("Authors (comma-separated): "));
        tfAuthors = new JTextField(20);
        cp.add(tfAuthors);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addBook());
        cp.add(addButton);

        setTitle("Add Book");
        setSize(300, 200);
        setVisible(true);
    }

    private void addBook() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String[] authors = tfAuthors.getText().split(",");

        Book book = new Book(title, category, cost);
        for (String author : authors) {
            book.addAuthor(author.trim());
        }

        store.addMedia(book);
        storeScreen.refreshStoreDisplay();
        JOptionPane.showMessageDialog(this, "Book added successfully");
        dispose();
    }
}