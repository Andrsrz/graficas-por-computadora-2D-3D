import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_Bres extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_Bres() {
		super("Lineas_Bres");
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

	public static void main(String[] args) {
		Lineas_Bres l = new Lineas_Bres();

		l.putLine_Bresenham(0, 100, 0, 200); //NO SE POR QUE A LA PRIMERA LINEA LE CAMBIA EL TAMAÑO EN CADA EJECUCION (AUNQUE NO SE COMPILE DE MANDERA DISTINTA)
		
		l.putLine_Bresenham(100 , 200 , 50 , 100); //POR LO QUE LO OCULTO Y TA ME DEJA TRABAJAR
		l.putLine_Bresenham(250 , 100 , 200 , 200);

		l.putLine_Bresenham(0 , 200 , 100 , 220);//inclinadas
		l.putLine_Bresenham(200 , 220 , 300 , 200);

		l.putLine_Bresenham(200 , 250 , 300 , 250);	//inc espejo
		l.putLine_Bresenham(100 , 250 , 0 , 250);

		l.putLine_Bresenham(100, 300 , 100 , 400);//horizontales
		l.putLine_Bresenham(200 , 400 , 200 , 300);
	}
}



