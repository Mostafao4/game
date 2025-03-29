package game.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DiceFace extends StackPane {
    private Rectangle background;
    private Text valueText;
    private boolean selected;
    private static DiceFace selectedDiceFace; // Track the selected DiceFace

    public DiceFace(int value, Paint color) {
        background = new Rectangle(50, 50);
        background.setFill(color);
        valueText = new Text(String.valueOf(value));
        valueText.setFont(new Font(20)); // Make the text larger
        valueText.setFill(Color.BLACK);
        getChildren().addAll(background, valueText);

        setOnMouseClicked(event -> toggleSelection());
    }

    public DiceFace(int value) {
        //TODO Auto-generated constructor stub
    }

    public void updateFace(int value) {
        valueText.setText(String.valueOf(value));
    }

    public void select() {
        selected = true;
        background.setStroke(Color.BLACK);
        background.setStrokeWidth(3);
        if (selectedDiceFace != null && selectedDiceFace != this) {
            selectedDiceFace.deselect(); // Deselect the previously selected DiceFace
        }
        selectedDiceFace = this; // Set this as the selected DiceFace
    }

    public void deselect() {
        selected = false;
        background.setStroke(null);
        if (selectedDiceFace == this) {
            selectedDiceFace = null; // Reset the selectedDiceFace if this was selected
        }
    }

    public boolean isSelected() {
        return selected;
    }

    private void toggleSelection() {
        if (isSelected()) {
            deselect();
        } else {
            select();
        }
    }
}
