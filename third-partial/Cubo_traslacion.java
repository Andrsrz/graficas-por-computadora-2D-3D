import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Cubo_traslacion extends JFrame implements KeyListener {
	private BufferedImage buffer;	
	private Graphics graPixel, gBuffer;
	int ancho = 500, alto = 500;
	int xp = 10, yp=10, zp=30, ra=50, x=10, y=100, z=10;
	double[] cx, cy, cz;

	public Cubo_traslacion() {
		super("Cubo_traslacion");
		setSize(ancho, alto);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		gBuffer = buffer.getGraphics();
		graPixel = this.getGraphics();
		cls();
		cx = new double [8];
		cy = new double [8];
		cz = new double [8];
		this.addKeyListener( this );
	}

	public void putPixel(int sx, int sy, Color c) {
		if(sx<ancho && sx>0 && sy<alto && sy>20){
			if (sy<100) {
				buffer.setRGB(sx, sy, Color.RED.getRGB()	);
			}else if (sy<200) {
				buffer.setRGB(sx, sy, Color.ORANGE.getRGB()	);
			}else if (sy<300) {
				buffer.setRGB(sx, sy, Color.CYAN.getRGB()	);
			}else if (sy<400) {
				buffer.setRGB(sx, sy, Color.BLUE.getRGB()	);
			}else{
				buffer.setRGB(sx, sy, Color.BLACK.getRGB()	);
			}
			//buffer.setRGB(sx, sy, c.getRGB());
		}
	}

	public void print(){
		cls();
		int x2d[] = new int[8];
		int y2d[] = new int[8];
		for(int i = 0 ; i<8 ; i++){
			double u = -cz[i]/zp;
			x2d[i] = (int)(cx[i]+(xp*u));
			y2d[i] = (int)(cy[i]+(yp*u));
		}

		putLine_Bresenham(x2d[0], y2d[0], x2d[1], y2d[1]);
		putLine_Bresenham(x2d[0], y2d[0], x2d[2], y2d[2]);
		putLine_Bresenham(x2d[2], y2d[2], x2d[3], y2d[3]);
		putLine_Bresenham(x2d[1], y2d[1], x2d[3], y2d[3]);
		putLine_Bresenham(x2d[4], y2d[4], x2d[5], y2d[5]);
		putLine_Bresenham(x2d[5], y2d[5], x2d[7], y2d[7]);
		putLine_Bresenham(x2d[6], y2d[6], x2d[7], y2d[7]);
		putLine_Bresenham(x2d[4], y2d[4], x2d[6], y2d[6]);
		putLine_Bresenham(x2d[0], y2d[0], x2d[4], y2d[4]);
		putLine_Bresenham(x2d[1], y2d[1], x2d[5], y2d[5]);
		putLine_Bresenham(x2d[2], y2d[2], x2d[6], y2d[6]);
		putLine_Bresenham(x2d[3], y2d[3], x2d[7], y2d[7]);

		graPixel.drawImage(buffer, 0, 0, this);
	}

	public void putLine_Bresenham(int x0, int y0, int x1, int y1){
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
			putPixel( Math.round(x), Math.round(y), Color.RED );
		}
	}

	public void cls(){
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0, 0, ancho, alto);
	}

	public void makeCube(){
		cx[0] = x;
		cy[0] = y;
		cz[0] = z;
		
		cx[1] = x+ra;
		cy[1] = y;
		cz[1] = z;

		cx[2] = x;
		cy[2] = y-ra;
		cz[2] = z;

		cx[3] = x+ra;
		cy[3] = y-ra;
		cz[3] = z;
		
		cx[4] = x;
		cy[4] = y;
		cz[4] = z-ra;

		cx[5] = x+ra;
		cy[5] = y;
		cz[5] = z-ra;
		
		cx[6] = x;
		cy[6] = y-ra;
		cz[6] = z-ra;
		
		cx[7] = x+ra;
		cy[7] = y-ra;
		cz[7] = z-ra;

		print();
	}

	public static void main(String[] args) {
		Cubo_traslacion l = new Cubo_traslacion();
		l.makeCube();
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == e.VK_DOWN){
			cls();
			y++;
			makeCube();
		} else if(c == e.VK_UP){
			cls();
			y--;
			makeCube();
		} else if(c == e.VK_LEFT){
			cls();
			x--;
			makeCube();
		} else if(c == e.VK_RIGHT){
			cls();
			x++;
			makeCube();
		} else if(c == e.VK_X){
			cls();
			z-=3;
			makeCube(); 
		} else if(c == e.VK_Z){
			cls();
			z+=3;
			makeCube();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}