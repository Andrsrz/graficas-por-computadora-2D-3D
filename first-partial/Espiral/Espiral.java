import java.awt.*;
import javax.swing.*;

public class Espiral extends JFrame implements Runnable{

	int contador; //Variable para el ciclo if
	int coordX, coordY, coordX2, coordY2; //X y Y para pocision del arco, X2 y Y2 para el arco más pequeño
	int width, radio; //Ancho, alto y radio
	private Thread hilo; //Hilo

	public Espiral(){ //Constructor
		super("Espiral");
		setSize(400, 400);
		contador = 0;
		width = 3;
		radio = (7); //Tamaño de los arcos
		hilo = new Thread(this);
		hilo.start();
		setLocationRelativeTo(null); //Centrar Frame en la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
		show();
	}

	public void paint(Graphics g){ //Funcion paint, dibuja
		if (contador == 0) {
			coordX = getWidth() / 2; //Iniciar desde el centro
			coordY = getHeight() / 2; //Iniciar desde el centro
			coordX2 = coordX + (radio);
			coordY2 = coordY;
		}
		//Para el arco inicial
		int wh = (radio + 1);
		g.drawArc(coordX, coordY-(wh/2), wh, wh, 180, 180);
		g.drawArc(coordX-width, coordY-width, width*2, width*2, 0, 180);

		if (contador%2 == 0) {
			g.drawArc(coordX-width, coordY-width, width*2, width*2, 0, 180);
		} else {
			g.drawArc(coordX2-width, coordY2-width, width*2, width*2, 180, 180);
			width += radio; //Aumento de la abertura
		}
		contador++; //Para las iteraciones y entrada al ciclo if
	}

	public void run(){ //Funcion run, necesaria para el hilo
		for (int i = 0; i < 60; i++) {
			try{
				Thread.sleep(550);
			}catch(InterruptedException ex){
				System.err.println("Error al detener el hilo");
			}
			repaint(); //Vuelve a ejecutar la funcion paint()
		}
	}

	public static void main(String[] args) { //Funcion main
		new Espiral(); //Crear nueva clase
	}
}