import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Circle_Mask extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;
	String mascara =  "1111000";
	String mascarar = "0001111";
	int pos = 0;

	public Circle_Mask() {
		super("Circle_Mask");
		setSize(400, 430);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//System.out.println( mascara + ": " + mascarar);
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
			if(pos == mascara.length() ){
				pos = 0;
			}
			if(mascara.substring( pos, pos+1 ).equals("1") ){
				putPixel( xc+x, yc-y, Color.RED);	//dr sup
				putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
				putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
				putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			}if(mascarar.substring( pos, pos+1 ).equals("1") ){
				putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
				putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
				putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
				putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			}
			pos ++;
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public static void main(String[] args) {
		Circle_Mask l = new Circle_Mask();
		l.putC(200, 200, 150);
		l.putC(200, 200, 100);
		l.putC(200, 200, 50);
		l.putC(200, 200, 10);
	}
}



