import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_puntoM extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_puntoM() {
		super("Lineas_puntoM");
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
		int x, y, p;
		x = 0;
		y = r;
		p = (5/4) - r;

		while(x <= y){
			try{ Thread.sleep(20); }catch(InterruptedException e){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
			putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
			putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
			putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
			x++;
			if (p < 0) 
				p = p + 2*x + 1;
			else {
				y = y - 1;
				p = p + 2*(x - y) + 1;
			}
		}
	}

	public static void main(String[] args) {
		Lineas_puntoM l = new Lineas_puntoM();
		l.putC(150, 200, 100);
	}
}