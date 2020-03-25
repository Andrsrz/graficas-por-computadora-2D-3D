import java.awt.*;
import javax.swing.*;

public class Visor extends JFrame{
	Pantalla pantalla;

	public Visor(){
		super("Visor de imagen");
		setBounds(400, 400, 1080, 720);
		Container contentpane = getContentPane();
		contentpane.setLayout(new GridLayout(1,1));
		pantalla = new Pantalla();
		contentpane.add(pantalla);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		show();
	}

	public static void main(String[] args) {
		Visor visor = new Visor();
	}
}