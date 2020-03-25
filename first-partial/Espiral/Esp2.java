import java.awt.*;
import javax.swing.*;

public class Esp2 extends JFrame implements Runnable{

	private Thread thr;
	public int centerX2;
	public int centerX;
	public int centerY;
	public int centerY2;
	public int arcWidth;
	public int arcGrowDelta; //13
	public int espirales;//90
	public int dormir;
	public int cont;

	public Esp2(){
		super("espiral");
		setBounds(400, 100, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		arcWidth = 1; //ancho del primer arco
		arcGrowDelta = (5); // tamaño de arco
		espirales = 100; //cantidad de espirales
		dormir = (500); //tiempo a dormir
		cont = 0;
		thr = new Thread(this);
		thr.start();
		show();//	lanzar frame
	}

	public void run(){
		for (int i = 1; i < espirales; i++) {
			try{
				thr.sleep(dormir);
			}
			catch(Exception e){}
			repaint();
		}
	}

	public void paint(Graphics g) {
		if(cont == 0){
			centerX = getWidth() / 2;
			centerY = getHeight() / 2;
			centerX2 = centerX + ( arcGrowDelta);
			centerY2 = centerY;
		}
			int md = (arcGrowDelta + 1); //arco inicial iz
			g.drawArc(centerX, centerY-(md/2), md, md, 180, 180); //arco inicial dr
			g.drawArc(centerX - arcWidth, centerY - arcWidth, arcWidth * 2, arcWidth * 2, 0, 180);
			//g.drawArc(centerX-md, centerY-(md/2), md, md, 0, 180);
		if(cont%2 == 0){
			g.drawArc(centerX - arcWidth, centerY - arcWidth, arcWidth * 2, arcWidth * 2, 0, 180);
		}
		else{
			g.drawArc(centerX2 - arcWidth, centerY2 - arcWidth, arcWidth * 2, arcWidth * 2, 180, 180);
			arcWidth += arcGrowDelta;
		}
		cont++;

	}

	public static void main(String[] args) {
		Esp2 panel = new Esp2();
	}
}