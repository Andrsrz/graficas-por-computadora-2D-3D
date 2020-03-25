import java.awt.*;
import javax.swing.*;

public class Pantalla extends JPanel{
	private Image fondo;

	public Pantalla(){
		preInit();
		initComponents();
		//setBackground(Color.BLUE);
		//setLayout(null);
	}

	private void initComponents(){

	}

	private void preInit(){
		fondo = new ImageIcon("img.jpg").getImage();
	}

	public void paint(Graphics g){
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	}
}