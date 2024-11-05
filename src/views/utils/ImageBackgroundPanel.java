package views.utils;

import javax.swing.*;
import java.awt.*;

public class ImageBackgroundPanel extends JPanel {
    private Image bgImage;

    public ImageBackgroundPanel(String imgPath) {
        bgImage = Toolkit.getDefaultToolkit().getImage(imgPath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}
