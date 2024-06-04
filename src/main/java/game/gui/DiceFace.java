package game.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DiceFace extends StackPane {
    private final Rectangle background;
    private final Circle[] pips;
    private boolean isSelected;

    public DiceFace(int value, Color color) {
        background = new Rectangle(50, 50);
        background.setFill(color);
        background.setArcWidth(10);
        background.setArcHeight(10);
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(isSelected ? 3 : 0); // Set stroke width based on selection

        pips = new Circle[7]; // 6 pips for each side + 1 for the center
        for (int i = 0; i < pips.length; i++) {
            pips[i] = new Circle(5, Color.BLACK); // Ensure pips are visible against the background
            pips[i].setVisible(false);
            getChildren().add(pips[i]);
        }

        setPipPositions();
        getChildren().add(0, background); // Ensure background is behind the pips
        updatePips(value);
    }

    private void setPipPositions() {
        // Positions are set as previously described
        // Example: Place one circle in the center, others around
        pips[0].setTranslateX(25); // Center pip
        pips[0].setTranslateY(25);
        // Set other pips accordingly
    }

    public void updatePips(int value) {
        // Manage pips visibility
    }

    public void select() {
        isSelected = true;
        background.setStrokeWidth(3);
    }

    public void deselect() {
        isSelected = false;
        background.setStrokeWidth(0);
    }

    public boolean isSelected() {
        return isSelected;
    }
}
