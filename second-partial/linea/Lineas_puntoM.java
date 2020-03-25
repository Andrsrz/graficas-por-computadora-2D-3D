import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;


public class Lineas_puntoM extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Lineas_puntoM() {
		super("Lineas_puntoM");
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

	public void putLine_PuntoM(int x0, int y0, int x1, int y1){
		int x, y, p, a, b, xinc, yinc;
		int dx = x1 - x0;
		int dy = y1 - y0;

		if (dy < 0) {
			dy = -dy;
			yinc = -1;
		} else {
			yinc = 1;
		}

		if (dx < 0) {
			dx = -dx;
			xinc = -1;
		} else {
			xinc = 1;
		}

		x = x0;
		y = y0;
		putPixel(x, y, Color.GREEN);
		int ini, fin, inc;

		if (dx > dy) {
			p = 2 * dy - dx;
			a = 2 * dy;
			b = 2 * (dy - dx);
			ini = x;
			fin = x1;
			inc = xinc;
		} else {
			p = 2 * dx - dy;
			a = 2 * dx;
			b = 2 * (dx - dy);
			ini = y;
			fin = y1;
			inc = yinc;
		}
		int medio = (a + b) / 2;
		while (ini != fin) {
			ini += inc;
			if (medio > p) {
				p = p + a;
			} else {
				if (dx > dy) {
					y = y + yinc;
				} else {
					x = x + xinc;
				}
				p = p + b;
			}
			try{ Thread.sleep(5); }catch(InterruptedException e){}
			putPixel(dx > dy ? ini : x, dx > dy ? y : ini, Color.GREEN);
		}
	}

	public static void main(String[] args) {
		Lineas_puntoM l = new Lineas_puntoM();

		l.putLine_PuntoM(0, 100, 1, 200); //NO SE POR QUE A LA PRIMERA LINEA LE CAMBIA EL TAMAÑO EN CADA EJECUCION (AUNQUE NO SE COMPILE DE MANDERA DISTINTA)
		
		l.putLine_PuntoM(100 , 200 , 50 , 100); //POR LO QUE LO OCULTO Y TA ME DEJA TRABAJAR
		l.putLine_PuntoM(250 , 100 , 200 , 200);

		l.putLine_PuntoM(0 , 200 , 100 , 220);//inclinadas
		l.putLine_PuntoM(200 , 220 , 300 , 200);

		l.putLine_PuntoM(200 , 250 , 300 , 250);	//inc espejo
		l.putLine_PuntoM(100 , 250 , 0 , 250);

		l.putLine_PuntoM(100, 300 , 100 , 400);//horizontales
		l.putLine_PuntoM(200 , 400 , 200 , 300);
	}
}



