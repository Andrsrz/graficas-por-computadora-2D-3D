import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Ventana extends JFrame {
   private BufferedImage buffer;
   private Graphics graPixel;

   public Ventana() {
      
      buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
      graPixel = (Graphics2D) buffer.createGraphics();
      
   }

   public void putPixel(int x, int y, Color c) {
      buffer.setRGB(0, 0, c.getRGB()); 
      this.getGraphics().drawImage(buffer, x, y, this);
   }
   
}
