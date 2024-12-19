package hust.soict.dsai.aims.screen;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import hust.soict.dsai.aims.cart.Cart;

import javax.swing.*;
import java.io.IOException;

public class CartScreen extends JFrame {
    private Cart cart;

    public CartScreen(Cart cart) {
        super("Cart");
        
        this.cart = cart;
        
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        
        this.setSize(1024, 768);
        this.setVisible(true);
        
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
                CartScreenController controller = new CartScreenController(cart);
                loader.setController(controller);
                Parent root = loader.load();
                fxPanel.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}