import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Cubo_Perspectiva extends JFrame implements KeyListener {
	private BufferedImage buffer;	
	private Graphics graPixel, gBuffer;
	int ancho = 500, alto = 500;
	int xp = 10, yp=100, zp=30, ra=50, x=30, y=100, z=1;
	int xc=1, yc=1, zc=500;
	double[] cx, cy, cz;

	public Cubo_Perspectiva() {
		super("Cubo_Perspectiva");
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
		putLine_Bresenham(x2d[0], y2d[0], x2d[1], y2d[1]); //fondo no visible (cara 1)
		putLine_Bresenham(x2d[0], y2d[0], x2d[2], y2d[2]);
		putLine_Bresenham(x2d[2], y2d[2], x2d[3], y2d[3]);
		putLine_Bresenham(x2d[1], y2d[1], x2d[3], y2d[3]); //fondo no visible (cara 1)
		putLine_Bresenham(x2d[4], y2d[4], x2d[5], y2d[5]);
		putLine_Bresenham(x2d[5], y2d[5], x2d[7], y2d[7]);
		putLine_Bresenham(x2d[6], y2d[6], x2d[7], y2d[7]);
		putLine_Bresenham(x2d[4], y2d[4], x2d[6], y2d[6]);
		putLine_Bresenham(x2d[0], y2d[0], x2d[4], y2d[4]);
		putLine_Bresenham(x2d[1], y2d[1], x2d[5], y2d[5]); //fondo no visible (cara 1)
		putLine_Bresenham(x2d[2], y2d[2], x2d[6], y2d[6]);
		putLine_Bresenham(x2d[3], y2d[3], x2d[7], y2d[7]);
		graPixel.drawImage(buffer, 0, 0, this);
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

	public double getpersp(double x, double u, int aux){
		double res = aux+((x-aux)*u);
		return res;
	}

	public void makeCube(){
		cx = new double [8];
		cy = new double [8];
		cz = new double [8];

		double u = (double)  -zc/(z-zc);
		cx[0] = getpersp(x, u, xc);
		cy[0] = getpersp(y, u, yc);
		cz[0] = getpersp(z, u, zc);
		
		cx[1] = getpersp(x+ra, u, xc);
		cy[1] = getpersp(y, u, yc);
		cz[1] = getpersp(z, u, zc);

		cx[2] = getpersp(x, u, xc);
		cy[2] = getpersp(y-ra, u, yc);
		cz[2] = getpersp(z, u, zc);

		cx[3] = getpersp(x+ra, u, xc);
		cy[3] = getpersp(y-ra, u, yc);
		cz[3] = getpersp(z, u, zc);
		
		u = (double) -zc/(z-(ra+zc));
		cx[4] = getpersp(x, u, xc);
		cy[4] = getpersp(y, u, yc);
		cz[4] = getpersp(z-ra, u, zc);


		cx[5] = getpersp(x+ra, u, xc);
		cy[5] = getpersp(y, u, yc);
		cz[5] = getpersp(z-ra, u, zc);

		cx[6] = getpersp(x, u, xc);
		cy[6] = getpersp(y-ra, u, yc);
		cz[6] = getpersp(z-ra, u, zc);
		
		cx[7] = getpersp(x+ra, u, xc);
		cy[7] = getpersp(y-ra, u, yc);
		cz[7] = getpersp(z-ra, u, zc);

		print();
	}

	public static void main(String[] args) {
		Cubo_Perspectiva l = new Cubo_Perspectiva();
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