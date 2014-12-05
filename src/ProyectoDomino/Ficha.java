/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoDomino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ficha extends JPanel implements  MouseMotionListener, Runnable
{
	Image imagen;
	int N1,N2;
	int dx,dy;
    Thread hilo;
    public Ficha(int n1, int n2,Image imagen) 
    {
    	this.imagen=imagen;
    	N1 = n1;
    	N2 = n2;
    	resize( 10,20);
      
       addMouseMotionListener(this);
    }
/*
 public void paint(Graphics g)
 {
 	g.setColor(Color.white);
 	g.fillRect(0,0,this.getWidth(),this.getHeight());
 	
 	g.setColor(Color.black);
 	g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
 	
 	g.drawRect(0,0,this.getWidth()-1,this.getHeight()/2-1);
 	
 	if( N1 == 1)
 	{
 		g.fillOval(this.getWidth()/2,this.getHeight()/4,5,5  );	
 	}
 	
   if( N1 ==2)
 	{
 		g.fillOval(this.getWidth()/4,this.getHeight()/4,5,5  );	
 		g.fillOval(this.getWidth()*3/4,this.getHeight()/4,5,5  );		
 	}
 		
 }   
   */
   public void paint(Graphics g)
     {
     	if(imagen !=null)
     	{
     			 g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
     	}
     	 setOpaque(false);
 		 super.paint(g);
     }

    
 public void mouseDragged( MouseEvent e)
{
	if (this.getY()<200)
	{
	
	System.out.println(" x = "+ e.getX());
	setLocation( this.getX()+ e.getX()-this.getWidth()/2 , this.getY()+ e.getY()-this.getHeight()/2);
	}
}
public void mouseMoved( MouseEvent e)
{
	
}
public void run()
{
	while ( true)
	{
		System.out.println(""+this.getX());
	   setLocation( this.getX()+ dx , this.getY()+ dy);
		try 
		{
			hilo.sleep(50); 
		}
		catch(InterruptedException e) 
		{
		}
	}
}

 	public void start(int dx,int dy)
	{	this.dx=dx;
	     this.dy=dy;
		
		if(hilo==null)
		{	hilo=new Thread(this);
			hilo.start();
		}
	}
	public void stop()
	{	if(hilo!=null)
		{	hilo.stop();
			hilo=null;
		}
	}
    
}