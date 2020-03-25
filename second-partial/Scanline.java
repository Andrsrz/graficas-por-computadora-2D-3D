/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Scanline extends JFrame {
	
	private BufferedImage buffer;	
	private Graphics graPixel;

	public Scanline() {
		super("Scanline");
		setSize(400, 430);
		setResizable(false);
		show();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buffer = new BufferedImage(500,500, BufferedImage.TYPE_INT_RGB);
		Graphics dbg = buffer.getGraphics();
		dbg.setColor(Color.white);
    	dbg.fillRect(0, 0, 500, 500);
		//graPixel = (Graphics2D) buffer.createGraphics();
		graPixel = this.getGraphics();
	}

	public void putPixel(int x, int y, Color c) {
		buffer.setRGB(x, y, c.getRGB());
		graPixel.drawImage(buffer, 0, 0, this);	
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

	public void sline(int x, int y){
		//tomo el color de la linea dibujada
		
		int realx = x;
		int figura = buffer.getRGB(x,y);
		
		y++;
		int actual = buffer.getRGB(x,y);
		while( actual != figura ){
			while( actual != figura ){
				putPixel(x,y,Color.BLUE); //COLOR DEL RELLENO
				x++;
				actual = buffer.getRGB(x,y);
			}
			y++;
			x = realx;
			actual = buffer.getRGB(x,y);
		}
	}

	public void makeCube(int x1, int y1, int x2, int y2){
		putLine_Bresenham( x1, y1, x1, y2 );
		putLine_Bresenham( x1, y1, x2, y1 );
		putLine_Bresenham( x2, y2, x2, y1 );
		putLine_Bresenham( x2, y2, x1, y2 );
		int x = x1, y = y1;
		if(x1 > x2)
			x = x2;
		if(y1 > y2)
			y = y2;
		
		sline(x+1,y); //aun sobre la linea dibujada
	}

	public static void main(String[] args){
		Scanline l = new Scanline();

		l.makeCube(50, 50, 300, 200);
	}
}



