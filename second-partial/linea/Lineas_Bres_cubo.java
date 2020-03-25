import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_Bres_cubo extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_Bres_cubo() {
		super("Lineas_Bres_cubo");
		setSize(400, 430);
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
			try{ Thread.sleep(5); }catch(InterruptedException exept){}
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

	public static void main(String[] args) {
		Lineas_Bres_cubo l = new Lineas_Bres_cubo();

		l.makeCube(50, 50, 300, 200);
	}
}



