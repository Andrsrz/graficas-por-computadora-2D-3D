import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Circle_An extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;
	String mascara =  "1111000";
	String mascarar = "0001111";
	int pos = 0;

	public Circle_An() {
		super("Circle_An");
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
				putPixel( xc+x, yc-y, Color.GRAY);	//dr sup
				putPixel( xc+x, yc+y, Color.RED);	//dr inf
				putPixel( xc+y, yc-x, Color.BLUE);	//inverso dr sup
				putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf

				putPixel( xc-x, yc-y, Color.GRAY);	//iz sup
				putPixel( xc-x, yc+y, Color.RED);	//iz inf
				putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
				putPixel( xc-y, yc+x, Color.BLACK);	//inverso iz inf
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public void drawC(int xc, int yc, int r, int gross){
		int x, y, e;
		x = r;
		y = 0;
		e = 0;
		while(y <= x){
			pos = 0;
			//try{ Thread.sleep(5); }catch(InterruptedException exept){}
			for( int i=1 ; i<=gross ; i+=1){
				putC( xc+x, yc-y, pos );//dr sup
				putC( xc-x, yc+y, pos );//iz inf
				putC( xc+y, yc+x, pos );//inverso dr inf
				putC( xc-y, yc-x, pos );//inverso iz sup
				putC( xc+y, yc-x, pos );//inverso dr sup
				putC( xc-y, yc+x, pos );//inverso iz inf
				putC( xc+x, yc+y, pos );//dr inf
				putC( xc-x, yc-y, pos );//iz sup
				if(pos<0)
					pos = Math.abs(pos)+1;
				else if(pos == 0)
					pos++;
				else
					pos = -pos;
			}
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public static void main(String[] args) {
		Circle_An l = new Circle_An();
		l.drawC(200, 200, 150, 30);
		l.drawC(200, 200, 100, 15);
		l.drawC(200, 200, 50, 10);
		l.drawC(200, 200, 10, 2);
	}
}



