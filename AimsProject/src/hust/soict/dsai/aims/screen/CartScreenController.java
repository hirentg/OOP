package hust.soict.dsai.aims.screen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

import java.awt.event.ActionEvent;
import java.util.stream.Collectors;

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
        tblMedia.setItems(FXCollections.observableArrayList(cart.getItemsOrdered()));

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    updateButtonBar(newValue);
                }
            }
        );

        filteredMedia = new FilteredList<>(FXCollections.observableArrayList(cart.getItemsOrdered()));
        tblMedia.setItems(filteredMedia);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            showFilteredMedia();
        });
    }
    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }

    void showFilteredMedia() {
        String filter = tfFilter.getText().toLowerCase();
        boolean filterById = radioBtnFilterId.isSelected();

        filteredMedia.setPredicate(media -> {
            if (filter == null || filter.isEmpty()) {
                return true;
            }
            
            if (filterById) {
                return String.valueOf(media.getId()).contains(filter);
            } else {
                return media.getTitle().toLowerCase().contains(filter);
            }
        });

        updateTotalCost();
    }
    
    @FXML
    private void btnRemovePressed() {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            updateTotalCost();
        }
    }
    @FXML
    void btnPlayPressed(ActionEvent event) throws PlayerException {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            ((Playable) media).play();
        }
    }

    private void updateTotalCost() {
        lbTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }
    
    @FXML
    private void placeOrder() {
        cart.placeOrder();
        tblMedia.setItems(FXCollections.observableArrayList());
        updateTotalCost();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText(null);
        alert.setContentText("Your order has been placed successfully!");
        alert.showAndWait();
    }

}