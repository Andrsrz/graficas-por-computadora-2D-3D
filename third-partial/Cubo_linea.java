import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Cubo_linea extends JFrame implements KeyListener {
	private BufferedImage buffer;	
	private Graphics graPixel, gBuffer;
	int ancho = 500, alto = 500;
	int xp = 50, yp=50, zp=100, ra=(int)10, x=(int)50, y=(int)200, z=1;
	int xc=1, yc=1, zc=500;
	double rax=1, ray=1, raz=1;
	double tetax=0, tetay=0, tetaz=0;
	double[] cx, cy, cz;
	int ls = 1;

	public Cubo_linea() {
		super("Cubo_linea");
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
			}
		}
			//buffer.setRGB(sx, sy, c.getRGB());
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
		putLine_Bresenham(x2d[0], y2d[0], x2d[1], y2d[1], Color.BLACK);
		putLine_Bresenham(x2d[1], y2d[1], x2d[2], y2d[2], Color.BLACK);
		putLine_Bresenham(x2d[2], y2d[2], x2d[3], y2d[3], Color.BLACK);
		putLine_Bresenham(x2d[3], y2d[3], x2d[4], y2d[4], Color.BLACK);

		putLine_Bresenham(x2d[4], y2d[4], x2d[5], y2d[5], Color.BLACK);
		putLine_Bresenham(x2d[5], y2d[5], x2d[6], y2d[6], Color.BLACK);
		putLine_Bresenham(x2d[6], y2d[6], x2d[7], y2d[7], Color.BLACK);

		graPixel.drawImage(buffer, 0, 0, this);
		graPixel.drawImage(buffer, 0, 0, this);
		//System.out.println(rax+"\t\t"+ray+"\t\t"+raz+"\t\t"+ra);
	}

	public void putLine_Bresenham(int x0, int y0, int x1, int y1, Color c){
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
			putPixel( Math.round(x), Math.round(y), c );
		}
	}

	public void cls(){
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0, 0, ancho, alto);
	}

	public void trasladaorigen(){
		double xce = ra/2;
		cx[1] = xce;
		cy[1] = xce;
		cz[1] = 0;

		cx[2] = -xce*2;
		cy[2] = -xce*2;
		cz[2] = 0;

		cx[3] = -xce*2;
		cy[3] = -xce*2;
		cz[3] = 0;
		
		cx[4] = xce;
		cy[4] = xce;
		cz[4] = -0*2;

		cx[5] = xce;
		cy[5] = xce;
		cz[5] = -0*2;
		
		cx[6] = -xce*2;
		cy[6] = -xce*2;
		cz[6] = -0*2;
		
		cx[7] = -xce*2;
		cy[7] = -xce*2;
		cz[7] = -0*2;
	}

	public void makeCube(){

		cx = new double [8];
		cy = new double [8];
		cz = new double [8];

		double cosenox = Math.cos(rax);
		double cosenoy = Math.cos(ray);
		double cosenoz = Math.cos(raz);
		
		double senox = Math.sin(rax);
		double senoy = Math.sin(ray);
		double senoz = Math.sin(raz);

		trasladaorigen();

		for (int i = 0 ; i<8 ; i++ ) {
						//x 							y 										z
			cx[i] = (( 1 )							*( (cx[i]*cosenoy) + (cx[i]*senoy) )	*( (cx[i]*cosenoz) - (cx[i]*senoz) ) );
			cy[i] = ((cy[i]*cosenox)-(cy[i]*senox)	*( 1 )									*( (cy[i]*cosenoz) + (cy[i]*senoz) ) );
			cz[i] = ((cz[i]*senox)+(cz[i]*cosenox)	*( (cz[i]*cosenoy) + (cz[i]*senoy) )	*( 1 ));
		}

		cx[0] += x;
		cy[0] += y;
		cz[0] += z;
		
		cx[1] += x+(ra*2);
		cy[1] += y;
		cz[1] += z;

		cx[2] += x+(ra*3);
		cy[2] += y;
		cz[2] += z;

		cx[3] += x+(ra*4);
		cy[3] += y;
		cz[3] += z;
		
		cx[4] += x+(ra*5);
		cy[4] += y;
		cz[4] += z;

		cx[5] += x+(ra*6);
		cy[5] += y;
		cz[5] += z;
		
		cx[6] += x+(ra*7);
		cy[6] += y;
		cz[6] += z;
		
		cx[7] += x+(ra*8);
		cy[7] += y;
		cz[7] += z;
		print();
	}

	public static void main(String[] args) {
		Cubo_linea l = new Cubo_linea();
		l.makeCube();
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		int av = 16;
		if(c == e.VK_X){
			cls();
			if(ls > 0)
				rax+= Math.PI/av;
			else
				rax-= Math.PI/av;
			makeCube(); 
		} else if(c == e.VK_Y){
			cls();
			if(ls > 0)
				ray+= Math.PI/av;
			else
				ray-= Math.PI/av;
			makeCube();
		} else if(c == e.VK_Z){
			cls();
			if(ls > 0)
				raz-= Math.PI/av;
			else
				raz+= Math.PI/av;
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