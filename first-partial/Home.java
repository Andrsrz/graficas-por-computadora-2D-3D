import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.*;

public class Home extends JFrame implements KeyListener {
	public BufferedImage buffer, img;	
	public Graphics gMain ,gBuffer;
	public static int ancho = 500, alto = 500;
	public boolean init = false;
	private String mascara = "111110000";
	public int vel = 5;
	public int carx = 20, cary = 250, cartx = 60, carty=20;

	public Home(){
		super("Bicicleta");
		setSize(ancho, 438);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		buffer = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
		gBuffer = buffer.getGraphics();
		gBuffer.setColor(Color.GRAY);
		gBuffer.fillRect(0, 0, ancho, alto);
		gMain = this.getGraphics();
		this.addKeyListener(this);
	}


	public static void main(String[] args) {
		Home s = new Home();
	}

	public void putPixelx(int x, int y, Color c) {
		if( x>0 && x<ancho && y>0 && y<alto ){
			if( /*(x<350 || x>400) && */( y<300 || y>350 ) )	//recorte super cabron pixel por pixel
				buffer.setRGB(x, y, c.getRGB()); // PARA DIBUJAR EL BUFER SE DEBE IMPLEMENTAR DONDE SE NECESITE
			//else
				//buffer.setRGB(x, y, Color.CYAN.getRGB());
		}
	}

	public void putPixel(int x, int y, Color c) {
		if( x>0 && x<ancho && y>0 && y<alto ){
			if(/* (x<350 || x>400) && */( y<300 || y>350 ) ){	//recorte super cabron pixel por pixel
				buffer.setRGB(x, y, c.getRGB()); // PARA DIBUJAR EL BUFER SE DEBE IMPLEMENTAR DONDE SE NECESITE
			}
		}
	}

	public void inun(int y){
		int x = 10;
		while( y < alto-70 ){
			while( x < ancho-10 ){
					for (int i = y ; i<y+40 ; i++) {
						putPixelx(x,i,Color.BLACK);
					}
					// try { Thread.sleep(1); }catch (InterruptedException ex) { Thread.currentThread().interrupt();}
					gMain.drawImage(buffer, 0, 0, this);
				x++;
			}
			y+=80;
			x=10;
		}
		gBuffer.setColor(Color.BLACK);
		//gMain.drawString("linea mascara, \"x\" -- circuo relleno \"C\" -- sline \"R\" ", 10, 450);
		gBuffer.setColor(Color.GRAY);
	}

	public void cls(){
		int x = 10;
		int y = 30;
		while( y < alto-70 ){
			while( x < ancho-10 ){
					for (int i = y ; i<y+40 ; i++) {
						putPixel(x,i,Color.BLACK);
					}
					// try { Thread.sleep(1); }catch (InterruptedException ex) { Thread.currentThread().interrupt();}
				x++;
			}
			y+=40;
			x=10;
		}
		gBuffer.setColor(Color.BLACK);
		//gBuffer.drawString("linea mascara, \"x\" -- circuo relleno \"C\" -- sline \"R\" ", 10, 450);
		gBuffer.setColor(Color.GRAY);
	}

	public void putC(int xc, int yc, int r){
		int x, y, e;
		x = r;
		y = 0;
		e = 0;
		while(y <= x){
				putPixel( xc+x, yc-y, Color.RED);	//dr sup
				putPixel( xc+x, yc+y, Color.RED);	//dr inf
				putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
				putPixel( xc+y, yc+x, Color.RED);	//inverso dr inf

				putPixel( xc-x, yc-y, Color.RED);	//iz sup
				putPixel( xc-x, yc+y, Color.RED);	//iz inf
				putPixel( xc-y, yc-x, Color.RED);	//inverso iz sup
				putPixel( xc-y, yc+x, Color.RED);	//inverso iz inf
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public void drawC(int xc, int yc, int r, int gross){
		int pos = 0;
		int x, y, e;
		x = r;
		y = 0;
		e = 0;
		while(y <= x){
			pos = 0;
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

	public void putLine_dda(int x0, int y0, int x1, int y1){
		int pos = 0;
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
			gMain.drawImage(buffer, 0, y0, this);
		}
		try{ Thread.sleep(1000/60); }catch(InterruptedException exept){}
		cls();
	}

	public void putLine_dda_gross(int x0, int y0, int x1, int y1, int gross, Color c){
		int pos = 0;
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
			for( int i=1 ; i<=gross ; i++){
				if(Math.abs(m)<1)
					putPixel( Math.round(x), Math.round(y+pos), c );
				else
					putPixel( Math.round(x+pos), Math.round(y), c );

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

	public void makeCube(int x1, int y1, int x2, int y2){
		//dbg.fillRect(0, 0, ancho, alto);
		putLine_dda_gross( x1, y1, x1, y2, 5, Color.WHITE );
		putLine_dda_gross( x1, y1, x2, y1, 5, Color.WHITE );
		putLine_dda_gross( x2, y2, x2, y1, 5, Color.WHITE );
		putLine_dda_gross( x2, y2, x1, y2, 5, Color.WHITE ); //caja principal

		drawC(x1+5, y2 , 20, 10);	
		drawC(x2-2, y2 , 20, 10);	//llantas

		gMain.drawImage(buffer, 0, 0, this);	//dibujo el bufer
	}

	public void makeCube2(int x1, int y1, int x2, int y2){
		putLine_dda_gross( x1, y1, x1, y2, 5, new Color(0, 0, 255));
		putLine_dda_gross( x1, y1, x2, y1, 5, new Color(0, 0, 255));
		putLine_dda_gross( x2, y2, x2, y1, 5, new Color(0, 0, 255));
		putLine_dda_gross( x2, y2, x1, y2, 5, new Color(0, 0, 255)); 
	}

	public void makeCube3(int x1, int y1, int x2, int y2){
		putLine_dda_gross( x1, y1, x1, y2, 5, new Color(200, 0, 100));
		putLine_dda_gross( x1, y1, x2, y1, 5, new Color(200, 0, 100));
		putLine_dda_gross( x2, y2, x2, y1, 5, new Color(200, 0, 100));
		putLine_dda_gross( x2, y2, x1, y2, 5, new Color(200, 0, 100)); 
	}

	public void makecar(){
		escena();
		makeCube(carx, cary, carx+cartx, cary+carty);
	}

	public void escena(){
		cls();
		putLine_dda_gross(10,110,125,110,5, new Color(0, 0, 255));
		putLine_dda_gross(125,110,125,300,5, new Color(0, 0, 255));
		makeCube2(20, 130, 70, 170);
		makeCube2(80, 130, 115, 170);
		makeCube2(20, 180, 70, 215);
		makeCube2(80, 230, 110, 300); //Ed Azul

		putLine_dda_gross(130,80,130,300,5, new Color(200, 0, 100));
		putLine_dda_gross(130, 80 ,300,80,5, new Color(200, 0, 100));
		putLine_dda_gross(300,80, 300,300,5, new Color(200, 0, 100));
		makeCube3(140, 90, 190, 120);
		makeCube3(160, 130, 235, 165);
		makeCube3(200, 90, 250, 120);
		makeCube3(260, 90, 290, 165);
		makeCube3(185, 200, 245, 300);
		putLine_dda_gross(215,200,215,300,5, new Color(200, 0, 100)); //Ed Rosa

		putLine_dda_gross(350,80, 350,300,5, new Color(150, 150, 150));
		putLine_dda_gross(350,80, 400,80,5, new Color(150, 150, 150));
		putLine_dda_gross(400,80, 400,110,5, new Color(150, 150, 150));
		putLine_dda_gross(400,110, 415,125,5, new Color(150, 150, 150));
		putLine_dda_gross(385,125, 400,110,5, new Color(150, 150, 150));
		putLine_dda_gross(385,125, 415,125,5, new Color(150, 150, 150)); //Faro

		putLine_dda_gross(375,160,385,130,5, new Color(255, 255, 0)); 
		putLine_dda_gross(400,160, 400,130,5, new Color(255, 255, 0)); 
		putLine_dda_gross(415,130, 425,160,5, new Color(255, 255, 0)); //Luces

		putLine_dda_gross(10,300,490,300,15, Color.GRAY); // Calle

		gMain.drawImage(buffer, 0, 0, this);
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		/*if (c == e.VK_DOWN){
			if(init){
				cartx --;
				carty --;
				cary++;
				makecar();
			}
		} else if(c == e.VK_UP){
			if(init){
				cartx ++;
				carty ++;
				cary--;
				makecar();
			}
		} else*/ if(c == e.VK_LEFT){
			if(init){
				if(carx>10){
					carx -= vel;
					makecar();
				}	
			}		
		} else if(c == e.VK_RIGHT){
			if(init){
				if((carx+cartx+10)<ancho){
					carx += vel;
					makecar();
				}
			}
		}/*else if(c == e.VK_PLUS){
			if(init){
				makecar();
			}
		}*/else if(c == e.VK_ENTER){
			init = false;
			buffer = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
			gBuffer = buffer.getGraphics();
			gBuffer.setColor(Color.GRAY);
			gBuffer.fillRect(0, 0, ancho, alto);

			gMain.drawImage(buffer, 0, 0, this);
			inun(30);//FONDO INTERLINEADO para recargar
			inun(70);
			init = true;
		}/*else if(c == e.VK_C){
			if(init){
				int j = 5, increment = 5;
				for(int i = 50 ; i < 450 ; i+=10){
					drawC(i, j*7 , j, (int)(j/2));
					gMain.drawImage(buffer, 0, 0, this);
					try{ Thread.sleep(1000/10); }catch(InterruptedException exept){}
					cls();
					if(j == 0 || j == 50){
						increment = -increment;
					}
					j+= increment;
				}
			}
		}else if(c == e.VK_X){
			if(init){
				int j = 5, increment = 10;
				for(int i = 50 ; i < 200 ; i+=10){
					putLine_dda( 50, i , 50, j );
					if(j == 0 || j == 50){
						increment = -increment;
					}
					j+= increment;
				}
				init = false;
				buffer = new BufferedImage(ancho,alto, BufferedImage.TYPE_INT_RGB);
				gBuffer = buffer.getGraphics();
				gBuffer.setColor(Color.GRAY);
				gBuffer.fillRect(0, 0, ancho, alto);

				gMain.drawImage(buffer, 0, 0, this);
				inun(30);//FONDO INTERLINEADO para recargar
				inun(70);
				init = true;
			}
		}*/
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}