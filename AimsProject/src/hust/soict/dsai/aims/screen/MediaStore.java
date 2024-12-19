package hust.soict.dsai.aims.screen;

import javax.swing.*;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.cart.Cart;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()));
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(e -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added to cart: " + media.getTitle());
        });
        container.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                try {
                    ((Playable) media).play();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error playing media: " + ex.getMessage());
                }
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}