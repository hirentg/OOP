package hust.soict.dsai.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.ObservableList;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

public class CartScreenController {
    private Cart cart;
    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private Label lbTotalCost;

    public CartScreenController(Cart cart) {
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredMedia = new FilteredList<>(cart.getItemsOrdered(), p -> true);
        
        tblMedia.setItems(filteredMedia);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    updateButtonBar(newValue);
                }
            }
        );

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia();
        });

        updateTotalCost();
    }

    private void showFilteredMedia() {
        String filterText = tfFilter.getText().toLowerCase().trim();
        
        filteredMedia.setPredicate(media -> {
            if (filterText == null || filterText.isEmpty()) {
                return true;
            }

            if (radioBtnFilterId.isSelected()) {
                // Filter by ID
                return String.valueOf(media.getId()).contains(filterText);
            } else {
                // Filter by Title
                return media.getTitle().toLowerCase().contains(filterText);
            }
        });
        
        updateTotalCost();
    }

    @FXML
    void btnRemovePressed() {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
            showFilteredMedia(); // Refresh 
            updateTotalCost();
            
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
        }
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }

    private void updateTotalCost() {
        // Calculate total cost based on filtered items
        float total = 0.0f;
        for (Media media : filteredMedia) {
            total += media.getCost();
        }
        lbTotalCost.setText(String.format("%.2f $", total));
    }

    @FXML
    private void placeOrder() {
        if (cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Your cart is empty!");
            alert.showAndWait();
            return;
        }

        float totalCost = cart.totalCost();
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Order Confirmation");
        confirmAlert.setHeaderText("Total amount: $" + String.format("%.2f", totalCost));
        confirmAlert.setContentText("Do you want to place this order?");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            cart.placeOrder();
            showFilteredMedia(); // Refresh 
            updateTotalCost();
            
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Order placed successfully!");
            successAlert.showAndWait();
        }
    }
}