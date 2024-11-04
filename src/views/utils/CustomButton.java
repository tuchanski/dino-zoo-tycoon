package views.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton {

    public CustomButton(String imagePath, int x, int y, int width, int height, ActionListener actionListener, Cursor cursor) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(img));

        setBounds(x, y, width, height);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        addActionListener(actionListener);

        setCursor(cursor);
    }
}
