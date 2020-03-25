import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_Bres extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_Bres() {
		super("Lineas_Bres");
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
		int x, y, e;
		x = r;
		y = 0;
		e = 0;

		while(y <= x){
			try{ Thread.sleep(20); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
			putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
			putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
			putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
			
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public static void main(String[] args) {
		Lineas_Bres l = new Lineas_Bres();
		l.putC(200, 200, 100);
	}
}



