import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
import java.util.Calendar;

public class Reloj extends JFrame implements Runnable{

	private Image fondo, buffer;
	private int hrs, min, sec;
	public ImageIcon img = new ImageIcon("clock.jpg");
	private Date dte;
	Calendar cal = Calendar.getInstance();
	public File hrssound = new File("navi.wav");
	public File secsound = new File("gota.wav");

	//Hilo
	private Thread thr;

	public Reloj(){
		super("Analog Clock");
		setResizable(false);
		setSize(590, 590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		thr = new Thread(this);
		thr.start();
	}

	public void run(){
		while(true){
			try{
				cal = Calendar.getInstance();
				dte = new Date();
				cal.setTime(dte);
				thr.sleep(400);
				if (cal.get(Calendar.SECOND) == 0) {
					PlaySound(hrssound, 1050);
					thr.sleep(100);
				}/*else if (cal.get(Calendar.SECOND)%2 == 0 || cal.get(Calendar.SECOND)%2 != 0) {
					PlaySound(secsound, 400);
					thr.sleep(100);
				}*/
			}catch(InterruptedException ex){
				//Nope
			}
			repaint();
		}	
	}

	public void paint(Graphics g){
		if(fondo == null){
			fondo = createImage(getWidth(), getHeight());
			fondo = img.getImage();
		}
		update(g);
	}

	public void update(Graphics g){
		g.setClip(0, 0, getWidth(), getHeight());
		cal = Calendar.getInstance();
		dte = new Date();
		cal.setTime(dte);

		if(cal.get(Calendar.MINUTE) != min){
			//Regenerar la imagen de fondo
			hrs = cal.get(Calendar.HOUR);
			min = cal.get(Calendar.MINUTE);
			//Crear la imagen estática
			buffer = createImage(getWidth(), getHeight());
			Graphics gbuffer = buffer.getGraphics();
			gbuffer.setClip(0, 0, getWidth(), getHeight());
			gbuffer.drawImage(fondo, 0, 0, this);
			gbuffer.setColor(Color.WHITE);
			gbuffer.fillArc((getWidth()-200)/2+5, (getHeight()-200)/2+5, 200, 200, angulo12(hrs) + 90, 4);
			gbuffer.setColor(Color.WHITE);
			gbuffer.fillArc((getWidth()-300)/2+5, (getHeight()-300)/2+5, 300, 300, angulo60(min) + 90, 3);
		}
		//Pintar buffer
		g.drawImage(buffer, 0, 0, this);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("12", 280, 70);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("3", 530, 315);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("6", 290, 550);
		g.setFont(new Font("SansSerif", Font.PLAIN, 30));
		g.setColor(Color.WHITE);
		g.drawString("9", 55, 315);
		g.setColor(Color.RED);
		g.fillOval(293, 295, 15, 15);
		sec = cal.get(Calendar.SECOND);
		//Pintar movil
		g.setColor(Color.RED);
		g.fillArc((getWidth()-350)/2+5, (getHeight()-350)/2+5, 350 , 350, angulo60(sec) + 90, 2);

	}

	public int angulo12(int las_horas){
		int grado_final = 0;
		grado_final = Math.round((360*las_horas)/12);
		return -grado_final;
	}

	public int angulo60(int los_min_sec){
		int grado_final = 0, grado_total = 0;
		grado_total = 360/60;
		grado_final = Math.round(grado_total * los_min_sec) - 360;
		return -grado_final;
	}

	public void PlaySound(File sound, int stime){
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			Thread.sleep(stime);
			clip.close();
		}catch(Exception e){
			//Nope
		}
	}

	public static void main(String[] args) {
		new Reloj();
	}
}