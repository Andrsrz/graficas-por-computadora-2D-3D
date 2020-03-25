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

	public void putLine(int x1, int y1, int x2, int y2){
		float y0 = (y2-y1) , x0 = (x2-x1);
		if(x0 == 0){
			x0= (float) 1;
		}
		//IMPEDIR LA DIVICION POR 0
		float m = y0/x0;
		float b = y1-(m*x1);
		int incremento = 1;
		int dist_inc;
		if(Math.abs(m) < 1){
			dist_inc = (int) x0;
			if( dist_inc < 0 ){
				incremento = -1;
			}
			if(dist_inc != 0)
			for (int x = x1 ; x != x2 ; x+=incremento ) {
				int y = (int) ((m*x) + b);
				try{ Thread.sleep(5); }catch(InterruptedException e){}
				putPixel( x, y, Color.RED );
			}
		}
		else{
			incremento = 1;
			dist_inc = (int) y0;
			if( dist_inc < 0 ){
				incremento = -1;
			}
			for (int x = y1 ; x != y2 ; x+=incremento ) {
				int y = (int) ( (x-b)/m );
				try{ Thread.sleep(5); }catch(InterruptedException e){}
				putPixel( y, x, Color.BLACK );
			}
		}
	}

	public static void main(String[] args) {
		Lineas l = new Lineas();
		l.putLine(100 , 200 , 50 , 100); 
		l.putLine(250 , 100 , 200 , 200);

		l.putLine(0 , 200 , 100 , 220);//inclinadas
		l.putLine(200 , 220 , 300 , 200);

		l.putLine(200 , 250 , 300 , 250);	//inc espejo
		l.putLine(100 , 250 , 0 , 250);

		l.putLine(100, 300 , 100 , 400);//horizontales
		l.putLine(200 , 400 , 200 , 300);
	}
}



