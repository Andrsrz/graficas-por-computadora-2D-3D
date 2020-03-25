/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recortelineas;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;


public class RecorteLineas extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

      private Image  imgPixel;
    private Graphics graPixel;   
	private boolean primer_click = false;
	private int x1b,x2b,y1b,y2b;
	private int x0r = 100;
	private int x1r = 500;
	private int y0r = 200;
	private int y1r = 500;
	
    BufferedImage Buffer =new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);  
    
    //Constructor
    public RecorteLineas(){
        this.setSize(800, 600);
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPixel = createImage(1, 1);
        graPixel = imgPixel.getGraphics();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
        
		dibujarFiguras();
         this.getGraphics().drawImage(Buffer, 1, 1, this);
    } 
    
    public void paint(Graphics g){
        
    }
    
   public void putPixel(int x, int y, Color c){
        Buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(Buffer, x, y, this);
    }
    
public void DDA(int x0, int y0, int x1, int y1, Color c)
{
  int x, y, dx, dy, p, incE, incNE, pasox, pasoy;
  dx = (x1 - x0);
  dy = (y1 - y0);

  if (dy < 0) 
  {
	  dy = -dy; 
	  pasoy = -1;
  }
  else 
    pasoy = 1;

  if (dx < 0) 
  {  
	dx = -dx;
	pasox = -1;
  } 
  else
    pasox = 1;

  x = x0;
  y = y0;
  
  putPixel(x0, y0, Color.BLUE);


  
  
  if(dx>dy)
  {
    p = 2*dy - dx;
    incE = 2*dy;
    incNE = 2*(dy-dx);
    while (x != x1)
	{
      x = x + pasox;
      if (p < 0)
        p = p + incE;
      else 
	  { 	
	y = y + pasoy; 
	p = p + incNE;
	  }
      putPixel(x,y, Color.BLUE);
    }
  }
  
  else
  {
    p = 2*dx - dy;
    incE = 2*dx;
    incNE = 2*(dx-dy);
    while (y != y1)
	{
      y = y + pasoy;
      if (p < 0)
        p = p + incE;
      else 
	  {x = x + pasox;p = p + incNE;}
      putPixel(x,y, Color.BLUE);
    }
  }
	
}
   
   public void DDAR(int x0, int y0, int x1, int y1)
{
  int x, y, dx, dy, p, incE, incNE, pasox, pasoy;
  dx = (x1 - x0);
  dy = (y1 - y0);

  if (dy < 0) 
  {
	  dy = -dy; 
	  pasoy = -1;
  }
  else 
    pasoy = 1;

  if (dx < 0) 
  {  
	dx = -dx;
	pasox = -1;
  } 
  else
    pasox = 1;

  x = x0;
  y = y0;
  

  
  
  if(dx>dy)
  {
    p = 2*dy - dx;
    incE = 2*dy;
    incNE = 2*(dy-dx);
    while (x != x1)
	{
      x = x + pasox;
      if (p < 0)
        p = p + incE;
      else 
	  { 	
	y = y + pasoy; 
	p = p + incNE;
	  }
	  
	if((x > x0r && y > y0r)&&(x < x1r && y < y1r) )
      putPixel(x,y, Color.BLUE);
    }
  }
  
  else
  {
    p = 2*dx - dy;
    incE = 2*dx;
    incNE = 2*(dx-dy);
    while (y != y1)
	{
      y = y + pasoy;
      if (p < 0)
        p = p + incE;
      else 
	  {x = x + pasox;p = p + incNE;}
    if((x > x0r && y > y0r)&&(x < x1r && y < y1r) )
      putPixel(x,y, Color.BLUE);
    }
  }
	
}
   
   
    public void dibujarFiguras()
	{   
	
	Rectangulo(x0r, x1r, y0r, y1r);	
    }
	
	public void Rectangulo(int x0, int x1, int y0, int y1)
	{
		
		DDA(x0, y0, x1, y0,Color.BLUE);
		DDA(x0, y0, x0, y1,Color.BLUE);
		DDA(x1, y0, x1, y1,Color.BLUE);
		DDA(x0, y1, x1, y1,Color.BLUE);
				
	}
	
    public static void main(String[] args) {
        new RecorteLineas();
    }
	
	public void actionPerformed(ActionEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}
		
		
	public void mousePressed(MouseEvent e) {
		x1b = e.getX();
		y1b = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		x2b = e.getX();
			y2b = e.getY();
			DDAR(x1b, y1b, x2b, y2b);
			this.getGraphics().drawImage(Buffer, 1, 1, this);
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
	
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	
    
}

