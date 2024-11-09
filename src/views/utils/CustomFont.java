package views.utils;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    public static Font useCustomFont(float size){
        try{
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/fonts/LEMONMILK-Bold.otf"));
            customFont = customFont.deriveFont(size);
            return customFont;
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
        }
        return new Font("Arial", Font.PLAIN, (int) size);
    }
}