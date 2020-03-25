/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recortesc;


import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class RecortesC extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

      private Image  imgPixel;
    private Graphics graPixel;   
	private boolean primer_click = false;
	private int x0b,y0b,x1b,y1b;
	private int x0r = 100;
	private int x1r = 500;
	private int y0r = 100;
	private int y1r = 500;
	
    BufferedImage miBuffer = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);    
    
    //Constructor
    public RecortesC(){
        this.setSize(600, 600);
        this.setVisible(true); 
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imgPixel = createImage(1, 1);
        graPixel = imgPixel.getGraphics();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
        
		dibujarFiguras();
         this.getGraphics().drawImage(miBuffer, 1, 1, this);
    } 
    
    public void paint(Graphics g){
        
    }
    
    //Metodo para Pintar Pixel de Color
    public void putPixel(int x, int y,String colorPixel) {
      
              
        miBuffer.setRGB(x, y, graPixel.getColor().getRGB());
		
    } 
    
public void DDA(int x0, int y0, int x1, int y1, String Color)
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
  
  putPixel(x0, y0, Color);

  
  
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
      putPixel(x,y, Color);
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
      putPixel(x,y, Color);
    }
  }
	
}
   
public void Simetria_Cir(int xc, int yc, int r)
{
	
  double x;
  double y;
  for(double t = 0; t <= 2*Math.PI; t = t+0.001)
  {
	  x = xc + r*Math.sin(t);
	  y = yc + r*Math.cos(t);
	  if((x > x0r && y > y0r)&&(x < x1r && y < y1r))
	  putPixel((int)Math.round(x), (int)Math.round(y), "BLACK");
  }
  
	
  
}
   
    public void dibujarFiguras()
	{   
	Rectangulo(x0r, x1r, y0r, y1r);	
    }
	
	public void Rectangulo(int x0, int x1, int y0, int y1)
	{
		
		DDA(x0, y0, x1, y0,"BLACK");
		DDA(x0, y0, x0, y1,"BLACK");
		DDA(x1, y0, x1, y1,"BLACK");
		DDA(x0, y1, x1, y1,"BLACK");
				
	}
	
    public static void main(String[] args) {
        new RecortesC();
    }
	
	public void actionPerformed(ActionEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
	}
		
		
	public void mousePressed(MouseEvent e) {
		x0b = e.getX();
		y0b = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		x1b = e.getX();
		y1b = e.getY();
		
		int xc = 0;
		int yc = 0;
		
		if(x0b < x1b && y0b < y1b)
		{
		xc = (int)(x0b+Math.abs(x0b - x1b)/2);
		yc = (int)(y0b+Math.abs(y0b - y1b)/2);
		}
		else if(x0b < x1b && y0b > y1b)
		{
		xc = (int)(x0b+Math.abs(x0b - x1b)/2);
		yc = (int)(y0b-Math.abs(y0b - y1b)/2);
		}
		else if(x0b > x1b && y0b > y1b)
		{
		xc = (int)(x0b-Math.abs(x0b - x1b)/2);
		yc = (int)(y0b-Math.abs(y0b - y1b)/2);
		}
		else if(x0b > x1b && y0b < y1b)
		{
		xc = (int)(x1b+Math.abs(x0b - x1b)/2);
		yc = (int)(y1b-Math.abs(y0b - y1b)/2);
		}
		
		
		int r = (int)(Math.sqrt(Math.pow((x0b-x1b),2)+ Math.pow((y0b-y1b),2))/2);
		
		Simetria_Cir(xc, yc, r);
		this.getGraphics().drawImage(miBuffer, 1, 1, this);
		
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
