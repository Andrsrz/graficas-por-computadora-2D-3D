import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Cubo_escalacion extends JFrame implements KeyListener {
	private BufferedImage buffer;	
	private Graphics graPixel, gBuffer;
	int ancho = 500, alto = 500;
	int xp = 5, yp=5, zp=10, ra=50, x=50, y=150, z=1;
	int xc=1, yc=1, zc=500;
	double rax=1, ray=1, raz=1;
	double[] cx, cy, cz;
	int ls = 1;

	public Cubo_escalacion() {
		super("Cubo_escalacion");
		setSize(ancho, alto);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		gBuffer = buffer.getGraphics();
		graPixel = this.getGraphics();
		cls();
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
		graPixel.drawImage(buffer, 0, 0, this);
		//System.out.println(rax+"\t\t"+ray+"\t\t"+raz+"\t\t"+ra);
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
		cx = new double [8];
		cy = new double [8];
		cz = new double [8];

		cx[0] = x;
		cy[0] = (y*ray);
		cz[0] = z;
		
		cx[1] = (x*rax)+ra;
		cy[1] = (y*ray);
		cz[1] = z;

		cx[2] = x;
		cy[2] = y-ra;
		cz[2] = z;

		cx[3] = (x*rax)+ra;
		cy[3] = y-ra;
		cz[3] = z;
		
		cx[4] = x;
		cy[4] = (y*ray);
		cz[4] = (z*raz)-ra;

		cx[5] = (x*rax)+ra;
		cy[5] = (y*ray);
		cz[5] = (z*raz)-ra;
		
		cx[6] = x;
		cy[6] = y-ra;
		cz[6] = (z*raz)-ra;
		
		cx[7] = (x*rax)+ra;
		cy[7] = y-ra;
		cz[7] = (z*raz)-ra;

		print();
	}

	public static void main(String[] args) {
		Cubo_escalacion l = new Cubo_escalacion();
		l.makeCube();
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		
		if(c == e.VK_X){
			cls();
			if(ls > 0)
				rax+=0.1;
			else
				rax-= 0.1;
			makeCube(); 
		} else if(c == e.VK_Y){
			cls();
			if(ls > 0)
				ray+=0.05;
			else
				ray-= 0.05;
			makeCube();
		} else if(c == e.VK_Z){
			cls();
			if(ls > 0)
				raz-=2;
			else
				raz+= 2;
			makeCube();
		} else if(c == e.VK_R){
			cls();
			if(ls > 0)
				ra++;
			else
				ra--;
			makeCube();
		} else if(c == e.VK_I ){
			ls = -ls;
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}