import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_Mask extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;
	String mascara = "1111000";
	int pos = 0;

	public Lineas_Mask() {
		super("Lineas_Mask");
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
		for ( int k=1 ; k!=step+1 ; k++ ) {
			try{ Thread.sleep(5); }catch(InterruptedException e){}
			if(pos == mascara.length() ){
				pos = 0;
			}
			if(mascara.substring( pos, pos+1 ).equals("1") ){
				putPixel( Math.round(x), Math.round(y), Color.RED );
			}
			pos ++;
			x = (float)(x + xinc);
			y = (float)(y + yinc);
		}
	}

	public static void main(String[] args) {
		Lineas_Mask l = new Lineas_Mask();
		l.putLine_dda(50 , 200 , 300 , 200);
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



