package hust.soict.dsai.aims.screen;

import javax.swing.*;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;
    private JPanel center;
    

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        center = createCenter();
        cp.add(center, BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    JPanel createNorth() {
        JPanel north = new JPanel(new BorderLayout());
        
        JMenuBar menuBar = createMenuBar();
        north.add(menuBar, BorderLayout.NORTH);
        
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
        
        JButton viewCartButton = new JButton("View cart");
        viewCartButton.addActionListener(e -> new CartScreen(cart));
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(viewCartButton, BorderLayout.EAST);
        
        north.add(headerPanel, BorderLayout.CENTER);
        
        return north;
    }
    
    
    JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu optionsMenu = new JMenu("Options");
        
        JMenu updateStoreMenu = new JMenu("Update Store");
        JMenuItem addBookItem = new JMenuItem("Add Book");
        JMenuItem addCDItem = new JMenuItem("Add CD");
        JMenuItem addDVDItem = new JMenuItem("Add DVD");
        
        updateStoreMenu.add(addBookItem);
        updateStoreMenu.add(addCDItem);
        updateStoreMenu.add(addDVDItem);
        
        JMenuItem viewStoreItem = new JMenuItem("View store");
        JMenuItem viewCartItem = new JMenuItem("View cart");
        
        optionsMenu.add(updateStoreMenu);
        optionsMenu.add(viewStoreItem);
        optionsMenu.add(viewCartItem);
        
        menuBar.add(optionsMenu);
        
        // Add action listeners
        addBookItem.addActionListener(e -> new AddBookToStoreScreen(store));
        addCDItem.addActionListener(e -> new AddCompactDiscToStoreScreen(store));
        addDVDItem.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store));
        viewStoreItem.addActionListener(e -> {
            // Refresh the current store view
            this.getContentPane().removeAll();
            this.add(createNorth(), BorderLayout.NORTH);
            this.add(createCenter(), BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
        viewCartItem.addActionListener(e -> new CartScreen(cart));
        
        return menuBar;
    }
    
    
    public void refreshStoreDisplay() {
        Container cp = getContentPane();
        cp.remove(1); // Remove the current center panel
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.revalidate();
        cp.repaint();
    }
    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
        
        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);
        
        JButton viewCartButton = new JButton("View cart");
        viewCartButton.addActionListener(e -> new CartScreen(cart));
        
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(viewCartButton);
        header.add(Box.createRigidArea(new Dimension(10, 10)));
        
        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));

        List<Media> mediaInStore = store.getItemsInStore();

        for (int i = 0; i < Math.min(mediaInStore.size(), 9); i++) {
            MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
            center.add(cell);
        }

        // Fill the remaining cells with empty panels
        for (int i = mediaInStore.size(); i < 9; i++) {
            center.add(new JPanel()); // Placeholder for empty cells
        }

        return center;
    }


    public void updateCenter() {
        remove(center);
        center = createCenter();
        add(center, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    private void addToCart(Media media) {
        cart.addMedia(media);
        JOptionPane.showMessageDialog(this, "The media has been added to the cart");
    }
  
}