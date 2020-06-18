package GUICinema.Views;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Class for create Buttons that are the seats of the cinema
 * in the panel of choose seats
 * @author Roser
 */
class ButtonSeat extends JButton{
    //Attributes
    private int row;
    private int column;
    private boolean selected;
    private Color bgColor;

    //Constructor
    public ButtonSeat(int row, int column) {
        this.row = row;
        this.column = column;
        this.setSelected(false);
        this.setText(String.format("R%02dC%02d", row, column));
    }
    
    //Getters and setters
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            bgColor= Color.BLUE;
        } else {
            bgColor=Color.GRAY;
        }
        this.selected = selected;
    }

    public Color getBgColor() {
        return bgColor;
    }
    

}
