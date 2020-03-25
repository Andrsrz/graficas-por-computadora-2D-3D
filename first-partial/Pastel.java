import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pastel extends JFrame {

    private JPanel contentPane;
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private boolean bandera=false;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Pastel frame = new Pastel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Pastel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblPartido = new JLabel("Dato 1:");
        lblPartido.setBounds(46, 39, 61, 14);
        contentPane.add(lblPartido);
        
        JLabel lblPartido_1 = new JLabel("Dato 2:");
        lblPartido_1.setBounds(46, 69, 61, 14);
        contentPane.add(lblPartido_1);
        
        JLabel lblPartido_2 = new JLabel("Dato 3:");
        lblPartido_2.setBounds(46, 103, 61, 14);
        contentPane.add(lblPartido_2);
        
        tf1 = new JTextField();
        tf1.setBounds(117, 36, 86, 20);
        contentPane.add(tf1);
        tf1.setColumns(10);
        
        tf2 = new JTextField();
        tf2.setBounds(117, 66, 86, 20);
        contentPane.add(tf2);
        tf2.setColumns(10);
        
        tf3 = new JTextField();
        tf3.setBounds(117, 97, 86, 20);
        contentPane.add(tf3);
        tf3.setColumns(10);
        
        JButton btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                bandera=true;
                repaint();
            }
        });
        btnGraficar.setBounds(45, 138, 107, 37);
        contentPane.add(btnGraficar);
    }
    
    
    public void paint(Graphics g)
    {
        super.paint(g);
        if (bandera==true)
        {
            String s1=tf1.getText();
            String s2=tf2.getText();
            String s3=tf3.getText();
            int v1=Integer.parseInt(s1);
            int v2=Integer.parseInt(s2);
            int v3=Integer.parseInt(s3);
            int suma=v1+v2+v3;
            int grados1=v1*360/suma;
            int grados2=v2*360/suma;
            int grados3=v3*360/suma;

            g.setColor(new Color(255,0,0));
            g.fillArc(50,250,200,200,0,grados1);
            g.fillRect(370,250,20,20);
            g.drawString("Dato 1", 400, 270);            

            g.setColor(new Color(0,128,0));
            g.fillArc(50,250,200,200,grados1,grados2);
            g.fillRect(370,280,20,20);
            g.drawString("Dato 2", 400, 300);            

            g.setColor(new Color(0,0,255));
            g.fillArc(50,250,200,200,grados1+grados2,grados3);
            g.fillRect(370,310,20,20);
            g.drawString("Dato 1", 400, 330);        
        }
    }
        
}