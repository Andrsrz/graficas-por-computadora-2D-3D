import java.awt.*;
import java.awt.image.*;
import javax.swing.*;


public class Lineas extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas() {
		super("Lineas");
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

	public void putC(int xc, int yc, int r){
		for(float t = 0 ; t<360 ; t+=.5 ){
			float rad = (float) Math.toRadians(t);
			int x = xc+(int) Math.round( r*Math.sin(rad) );
			int y = yc+(int) Math.round( r*Math.cos(rad) );
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){}
			putPixel( x, y, Color.BLACK);
		}
	}

	public static void main(String[] args) {
		Lineas l = new Lineas();
		l.putC(150, 200, 100);
	}
}



