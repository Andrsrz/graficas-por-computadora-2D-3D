import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_An extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;
	float pos;

	public Lineas_An() {
		super("Lineas_An");
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

	public void putLine_dda(int x0, int y0, int x1, int y1, int gross){
		float dx = (x1-x0), dy = (y1-y0), step, x, y, xinc, yinc;

		if(Math.abs(dx) >= Math.abs(dy)){
			step = Math.abs(dx);
		}else{
			step = Math.abs(dy);
		}
		float m = dy/dx;
		xinc = (float) (dx/step);
		yinc = (float) (dy/step);
		x = x0;
		y = y0;
		for ( int k=1 ; k!=step+1 ; k++ ) {
			pos = 0;
			try{ Thread.sleep(5); }catch(InterruptedException e){}
			for( int i=1 ; i<=gross ; i++){
				if(Math.abs(m)<1)
					putPixel( Math.round(x), Math.round(y+pos), Color.RED );
				else
					putPixel( Math.round(x+pos), Math.round(y), Color.RED );

				if(pos<0)
					pos = Math.abs(pos)+1;
				else if(pos == 0)
					pos++;
				else
					pos = -pos;
			}
			x = (float)(x + xinc);
			y = (float)(y + yinc);
		}
	}

	public static void main(String[] args) {
		Lineas_An l = new Lineas_An();

		//l.putLine_dda(50 , 200 , 300 , 200, 5);
		l.putLine_dda(100 , 200 , 50 , 100, 5); //POR LO QUE LO OCULTO Y TA ME DEJA TRABAJAR
		l.putLine_dda(250 , 100 , 200 , 200, 5);

		l.putLine_dda(0 , 200 , 100 , 220, 5);//inclinadas
		l.putLine_dda(200 , 220 , 300 , 200, 5);

		l.putLine_dda(200 , 250 , 300 , 250, 5);	//inc espejo
		l.putLine_dda(100 , 250 , 0 , 250, 5);

		l.putLine_dda(100, 300 , 100 , 400, 5);//horizontales
		l.putLine_dda(200 , 400 , 200 , 300, 5);
	}
}



