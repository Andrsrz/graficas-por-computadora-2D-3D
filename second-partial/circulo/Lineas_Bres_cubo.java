import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_Bres_cubo extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;
	int time = 15;
	public Lineas_Bres_cubo() {
		super("Lineas_Bres_cubo");
		setSize(800, 400);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		//graPixel = (Graphics2D) buffer.createGraphics();
		graPixel = this.getGraphics();
	}

	public void putPixel(int x, int y, Color c) {
		buffer.setRGB(0, 0, c.getRGB());
		graPixel.drawImage(buffer, x, y, this);
		
	}

	public void putC(int xc, int yc, int r){
		int x, y, e, x2;
		x = r;
		y = 0;
		e = 0;

		while(y <= x*100000){
			try{ Thread.sleep(1); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
			putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
			putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
			putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
			
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*r/y+x))+1 );
			}
		}
	}

	public void putLine_Bresenham(int bx1, int by1, int bx2, int by2){
		float e,ax,ay,temp;
		int s1,s2,intercambio,i,x,y;
		
		x=bx1;
		y=by1;
		ax=Math.abs(bx2-bx1);
		ay=Math.abs(by2-by1);
		s1=signo(bx2-bx1);
		s2=signo(by2-by1);
		if(ay>ax){
			temp=ax;
			ax=ay;
			ay=temp;
			intercambio=1;
		}
		else{
			intercambio=0;
		}
		e=2*ay-ax;
		for(i=1;i<=ax;i++){
			try{ Thread.sleep(time/2); }catch(InterruptedException exept){}
			putPixel((x),(y),Color.BLUE);
			if(e>=0){
				if (intercambio==1){
					x=x+s1;
				}else{
					y=y+s2;
				}
				e=e-(2*ax);
			}
			if (intercambio==1){
          		y=y+s2;
          	}else{
          		x=x+s1;
          	}
			e=e+2*ay;
		}
	}

	public int signo(int num){
		
		if(num<0)
			return -1;
		if(num>0)
			return 1;
		return 0;
	}

	public void makeCube(int x1, int y1, int x2, int y2){
		putLine_Bresenham( x1, y1, x1, y2 );
		putLine_Bresenham( x1, y1, x2, y1 );
		putLine_Bresenham( x2, y2, x2, y1 );
		putLine_Bresenham( x2, y2, x1, y2 );
	}

	public void putCi(int xc, int yc, int r){
		int x, y, e;
		x = r;
		y = 0;
		e = 0;

		while(y <= x){
			try{ Thread.sleep(time*2); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
			putPixel( xc+y, yc-x, Color.RED);	//inverso dr sup
			putPixel( xc-y, yc-x, Color.BLUE);	//inverso iz sup
			putPixel( xc+y, yc+x, Color.BLACK);	//inverso dr inf
			putPixel( xc-y, yc+x, Color.GREEN);	//inverso iz inf
			
			e = e+ (2*y) + 1;
			y = y+1;
			if( 2*e > (2*x)-1 ){
				x = x-1;
				e = Math.round( (e-(2*x))+1 );
			}
		}
	}

	public void elipse(int xc, int yc, int rx, int ry){
		int x, y, p, px, py;
		int rx2, ry2, tworx2, twory2;
		ry2 = ry*ry;
		rx2 = rx*rx;
		twory2 = 2 * ry2;
		tworx2 = 2 * rx2;
		/* región 1 */
		x = 0;
		y = ry;
		try{ Thread.sleep(time/2); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
		p = (int)Math.round(ry2 - rx2*ry + 0.25*rx2);
		px = 0;
		py = tworx2*y;
		while (px < py) { /* se cicla hasta trazar la región 1 */
			x = x + 1;
			px = px + twory2;
			if (p < 0)
				p = p + ry2 + px;
			else {
				y = y - 1;
				py = py - tworx2;
				p = p + ry2 + px - py;
			}
			try{ Thread.sleep(time/2); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
		}
		/* región 2 */
		p = (int)Math.round(ry2*(x+0.5)*(x+0.5) + rx2*(y-1)*(y-1) - rx2*ry2);
		px = 0;
		py = tworx2*y;
		while (y > 0) { /* se cicla hasta trazar la región 2 */
			y = y - 1;
			py = py - tworx2;
			if (p > 0)
				p = p + rx2 - py;
			else {
				x = x + 1;
				px = px + twory2;
				p = p + rx2 + py + px;
			}
			try{ Thread.sleep(time/2); }catch(InterruptedException exept){}
			putPixel( xc+x, yc-y, Color.RED);	//dr sup
			putPixel( xc-x, yc-y, Color.BLUE);	//iz sup
			putPixel( xc+x, yc+y, Color.BLACK);	//dr inf
			putPixel( xc-x, yc+y, Color.GREEN);	//iz inf
		}
	}

	public static void main(String[] args) {
		Lineas_Bres_cubo l = new Lineas_Bres_cubo();
		//l.putC(200, 200, 100);
		l.elipse(600, 300, 40, 10);
		l.elipse(600, 300, 50, 20);
		l.elipse(600, 300, 60, 30);
		l.elipse(600, 300, 70, 40);

		l.putCi(100, 300, 5);
		l.putCi(100, 300, 25);
		l.putCi(100, 300, 45);
		l.putCi(100, 300, 65);

		l.makeCube(200, 250, 500, 350);
		l.makeCube( 450, 330, 250, 280);

		l.putLine_Bresenham( 50, 150, 20, 50 );
		l.putLine_Bresenham( 200, 50, 300, 50 );
		l.putLine_Bresenham( 300, 150, 200, 150);
		l.putLine_Bresenham( 450, 50, 400 , 150 );
	}
}
