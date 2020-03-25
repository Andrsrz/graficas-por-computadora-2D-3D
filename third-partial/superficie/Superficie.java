import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Superficie extends JFrame implements KeyListener {
	private BufferedImage buffer;	
	private Graphics graPixel, gBuffer;
	int ancho = 500, alto = 500;
	int xp=1, yp=1, zp=1;
	int x=10,y=10,z=1;
	int enmayado = 20, puntosMaya = 20, ra = 10;
	int aumento = 1;
	double tetax=0, tetay=0, tetaz=0;
	Slinea linea[];

	public Superficie() {
		super("Superficie");
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

	public void cls(){
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(0, 0, ancho, alto);
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
			}else if (sy<500) {
				buffer.setRGB(sx, sy, Color.BLACK.getRGB()	);
			}
		}
			//buffer.setRGB(sx, sy, c.getRGB());
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
	//terminado
	public void makeCube(){
		this.linea = new Slinea[enmayado];

		double cosenox = Math.cos(tetax);
		double cosenoy = Math.cos(tetay);
		double cosenoz = Math.cos(tetaz);
		
		double senox = Math.sin(tetax);
		double senoy = Math.sin(tetay);
		double senoz = Math.sin(tetaz);

		
		for (int i = 0 ; i<enmayado ; i++) {
			//trasladaorigen();
			int sx = ((int)((x*cosenoy)+(x*senoy))	+(int)((x*cosenoz)-(x*senoz)));
			int sy = ((int)((y*cosenox)-(y*senox))	+(int)((y*cosenoz)+(y*senoz)));
			int sz = ((int)((z*senox)+	(z*cosenox))+(int)((z*cosenoy)+(z*senoy)));

			linea[i] = new Slinea(sx, (int)Math.pow(sy-i,2) , sz, puntosMaya, ra);
		}
		print();
		//graPixel.drawImage(buffer, 0, 0, this);
	}

	public void print(){
		cls();
		for(int i = 0 ; i<enmayado ; i++){

			int x2dup[] = new int[puntosMaya];
			int y2dup[] = new int[puntosMaya];

			int x2d[] = new int[puntosMaya];
			int y2d[] = new int[puntosMaya];
			
			for(int j = 0 ; j<puntosMaya ; j++){
				x2d[j] = linea[i].getx2d( linea[i].point[j] );
				y2d[j] = linea[i].gety2d( linea[i].point[j] );
				if(i<enmayado-1){
					x2dup[j] = linea[i].getx2d( linea[i+1].point[j] );
					y2dup[j] = linea[i].gety2d( linea[i+1].point[j] );
				}
			}

			if(i<enmayado-1)
				putLine_Bresenham(x2d[puntosMaya-1], y2d[puntosMaya-1], x2dup[puntosMaya-1], y2dup[puntosMaya-1], Color.BLACK);

			for (int j = 0 ; j<puntosMaya-1 ; j++ ) { //dibuja lineas
					putLine_Bresenham(x2d[j], y2d[j], x2d[j+1], y2d[j+1], Color.BLACK);
				if(i<enmayado-1){
					putLine_Bresenham(x2d[j], y2d[j], x2dup[j], y2dup[j], Color.BLACK);
					putLine_Bresenham(x2d[j], y2d[j], x2dup[j+1], y2dup[j+1], Color.BLACK);
				}
			}

		}
		graPixel.drawImage(buffer, 0, 0, this);
	}


	public static void main(String[] args) {
		Superficie l = new Superficie();
		l.makeCube();
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		int av = 16;
		if(c == e.VK_X){
			cls();
			tetax += (int)Math.PI/16;
			makeCube(); 
		} else if(c == e.VK_Y){
			cls();
			tetay += (int)Math.PI/16;
			makeCube();
		} else if(c == e.VK_Z){
			cls();
			tetaz += (int)Math.PI/16;
			makeCube();
		} else if(c == e.VK_R){
			cls();
			ra+=aumento;
			makeCube();
		} else if(c == e.VK_E){
			cls();
			enmayado+=aumento;
			makeCube();
		} else if(c == e.VK_W){
			cls();
			puntosMaya+=aumento;
			makeCube();
		} else if(c == e.VK_I ){
			aumento = -aumento;
		}else if (c == e.VK_DOWN){
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
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}