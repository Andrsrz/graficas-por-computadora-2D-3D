import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class Linea extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Linea() {
		super("Linea");
		setSize(400, 430);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		graPixel = (Graphics2D) buffer.createGraphics();
	}

	public void putPixel(int x, int y, Color c) {
		buffer.setRGB(0, 0, c.getRGB()); 
		this.getGraphics().drawImage(buffer, x, y, this);
	}

	public void putC(int xc, int yc, int radio){
		int limit = xc+radio;
		float y;
		for (int x = xc-radio ; x<=limit ; x++ ) {
			y = (float)Math.sqrt( Math.pow(radio, 2) - Math.pow(x-xc, 2) );
			try{ Thread.sleep(5); }catch(InterruptedException e){}
			putPixel(x, Math.round(yc+y), Color.BLACK);
			putPixel(x, Math.round(yc-y), Color.BLACK);
		}
	}

	public static void main(String[] args) {
		Linea l = new Linea();
		l.putC(150, 200, 100);
	}
}



