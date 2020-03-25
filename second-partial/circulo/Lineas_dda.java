import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_dda extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_dda() {
		super("Lineas_dda");
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
		for(float t = 0 ; t<=45 ; t+=.5 ){	//pinta solo 45° (octantes)
			float rad = (float) Math.toRadians(t);
			int x = (int) Math.round( r*Math.sin(rad) );
			int y = (int) Math.round( r*Math.cos(rad) );

			try{
				Thread.sleep(10);
			}catch(InterruptedException e){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
			putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
			putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
			putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
		}
	}

	public static void main(String[] args) {
		Lineas_dda l = new Lineas_dda();
		l.putC(150, 200, 100);
	}
}