package hust.soict.dsai.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PainterController {
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private ToggleGroup toolGroup;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(javafx.scene.input.MouseEvent event) {
        RadioButton selectedRadioButton = (RadioButton) toolGroup.getSelectedToggle();
        String tool = selectedRadioButton.getText();

        Circle newCircle = new Circle(event.getX(), event.getY(), 4, 
            tool.equals("Pen") ? Color.BLACK : Color.WHITE);
        drawingAreaPane.getChildren().add(newCircle);
    }
}