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

	public void putLine_dda(int x0, int y0, int x1, int y1){
		float dx = (x1-x0), dy = (y1-y0), step, x, y, xinc, yinc;

		if(Math.abs(dx) >= Math.abs(dy)){
			step = Math.abs(dx);
		}else{
			step = Math.abs(dy);
		}
		
		xinc = (float) (dx/step);
		yinc = (float) (dy/step);
		x = x0;
		y = y0;

		putPixel( Math.round(x), Math.round(y), Color.BLUE );
		for ( int k=1 ; k!=step ; k++ ) {
			x = (float)(x + xinc);
			y = (float)(y + yinc);
			try{ Thread.sleep(5); }catch(InterruptedException e){}
			putPixel( Math.round(x), Math.round(y), Color.RED );
		}
	}

	public static void main(String[] args) {
		Lineas_dda l = new Lineas_dda();
		l.putLine_dda(100 , 200 , 50 , 100); //POR LO QUE LO OCULTO Y TA ME DEJA TRABAJAR
		l.putLine_dda(250 , 100 , 200 , 200);

		l.putLine_dda(0 , 200 , 100 , 220);//inclinadas
		l.putLine_dda(200 , 220 , 300 , 200);

		l.putLine_dda(200 , 250 , 300 , 250);	//inc espejo
		l.putLine_dda(100 , 250 , 0 , 250);

		l.putLine_dda(100, 300 , 100 , 400);//horizontales
		l.putLine_dda(200 , 400 , 200 , 300);
	}
}



