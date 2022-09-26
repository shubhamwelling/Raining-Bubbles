
package bubblesAPCS;
import java.awt.*;

import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class RainingBubblesOriginalApplet extends Applet implements MouseListener,MouseMotionListener,KeyListener, ActionListener
{
	//constants to control size, speed, number of circles...
	private final int DELAY =20;
	private final int MAX_SIZE = 45;
	private final int MAX_CIRCLES = 100;
	private final int MAX_VELOCITY = 20;
	private Circle control=new Circle();
	//these are called "parallel arrays." Is there a better way to handle all
	//of this data?  Hint... these could all be ATTRIBUTES of a certain class.  Make that class and 
	//create a single array of that object.
	private Graphics graphics=null;
	private int locator;
	private int display;
	private BubbleApplet[] b;
	//private int[] x;
	//private int[] y;
	//private int[] yvelocity;
	//private int[] size;

	public void init() 
	{
		this.setFocusTraversalKeysEnabled(false);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		//What is the purpose of this me;thod?  State as a comment under this line.
	
		//Document this...what's going on in each line?...
		this.resize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
					(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		//again, change these parallel arrays to make them better.
//		x = new int[MAX_CIRCLES];
//		y = new int[MAX_CIRCLES];
//		yvelocity = new int[MAX_CIRCLES];
//		size = new int[MAX_CIRCLES];
		b=new BubbleApplet [MAX_CIRCLES];
		for(int index = 0;index < MAX_CIRCLES; index++)
		{
			resetCircle(index);
		}
		
		//I needed this to use "repaint" the screen. It uses a timer which is "listenedTo" by an ActionListener
		ActionListener taskPerformer = new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				repaint();
			}
		};
		
		new Timer(DELAY, taskPerformer).start();
		
	}
		
	//This method is to "double buffer".  If it wasn't here,
	//the animations would flicker.  No need to modify/comment anything
	//in this method.
	public void update(Graphics g)
	{
		Graphics offgc;
		Image offscreen = null;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		offscreen = createImage(d.width, d.height);
		offgc = offscreen.getGraphics();
		
		offgc.setColor(getBackground());
		offgc.fillRect(0,0,d.width,d.height);
		offgc.setColor(getForeground());
		
		paint(offgc);
		
		g.drawImage(offscreen, 0,0, this);
	}
	private void resetCircle(int index)
	{
		//What does this method do?  Comment under this line.  Also, again - fix the parallel array issue.
		
//		y[index] = 0;
//		x[index] = (int)(Math.random()*this.getWidth());
//		yvelocity[index] = (int)(Math.random()*MAX_VELOCITY)+2;	
//		size[index] = (int)(Math.random()*MAX_SIZE);	

        b[index]=new BubbleApplet();
		b[index].setY(999999999);
		b[index].setX(99999999);
		  b[index].setyVelocity((int)(Math.random()*2*MAX_VELOCITY)- MAX_VELOCITY);
			b[index].setxVelocity((int)(Math.random()*2*MAX_VELOCITY)- MAX_VELOCITY);
		b[index].setSize((int)(Math.random()*MAX_SIZE)+2);
		
	}
	private void resetCircle(int index, int x, int y) {
		b[index]=new BubbleApplet();
		b[index].setX(x);
	    b[index].setY(y);
	    b[index].setyVelocity((int)(Math.random()*MAX_VELOCITY)+2);
		b[index].setxVelocity((int)(Math.random()*MAX_VELOCITY)+2);

		b[index].setSize((int)(Math.random()*MAX_SIZE)+2);
		control.setX1(500);
		control.setY1(600);
		control.setyV(0);
		control.setxV(0);
		control.setSize1(25);
		control.setColor(Color.black);

	}
	
	
	public void paint(Graphics g) 
	{	
		
	
			graphics=g.create();
			
			Ellipse2D circle;
			Ellipse2D circle2;
		Graphics2D g2 = (Graphics2D)g;
		Graphics2D g3=(Graphics2D)g;
		
		Font f1=new Font("Forte",Font.BOLD,60);
		
		
	g.setFont(f1);
	g.setColor(Color.black);
	g.drawString("Bubble-Mania", 475, 420);

	Font f2=new Font("TimesRoman",Font.ITALIC, 30);
	Font f3=new Font("TimesRoman",Font.BOLD,40);
	
	g.setFont(f3);
	g.setColor(Color.black);
	g.drawString("Controls", 1100, 100);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Click to spawn in bursts of 5!", 1100, 170);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Drag to increase size!", 1100, 240);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Hold to freeze!", 1100, 310);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Alt to decrease size!", 1100, 380);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Keys A to G to change background color!", 1100, 450);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Enter to speed things up!", 1100, 520);
	g.setFont(f2);
	g.setColor(Color.red);
	g.drawString("Objective: Make as many bubbles disappear by touching them with the mobile circle(with arrow keys)",200,800);
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Shift for some Slow-Mo action!", 1100, 580);
	g.setFont(f2);
	g.setColor(Color.orange);
	
	g.setFont(f2);
	g.setColor(Color.black);
	g.drawString("Backspace to make bubbles disappear!", 1100, 630);
	g.setColor(Color.magenta);
	((Graphics2D) g).setStroke(new BasicStroke(10));
g.drawLine(1050, 50, 1050, 650);
g.drawLine(1050, 650, 1610, 650);
g.drawLine(1610, 650, 1610, 50);
g.drawLine(1050,50,1610,50);
g.setColor(Color.yellow);
g.fillRect(0, 0, 400, 110);
Font f4=new Font("TimesRoman",Font.BOLD,12);
Font f5=new Font("TimesRoman",Font.ITALIC,12);
g.setFont(f4);
g.setColor(Color.black);
g.drawString("Features",20,20);
g.setFont(f5);
g.setColor(Color.black);
g.drawString("One Bubble Array ",20,40);
g.setFont(f5);
g.setColor(Color.black);
g.drawString("Multiple Colored Bubbles",20,60);
g.setFont(f5);
g.setColor(Color.black);
g.drawString("Bubbles move in x and y directions",20,80);
g.setFont(f5);
g.setColor(Color.black);
g.drawString("Change size with key and mouse commands and with wall contact",20,100);
		//int []arr1= new int [70];
	
		//for(int h=0;h<arr1.length; h++);
		//int i=(int)(Math.random()*arr1.length);
			
//		
			
			
			
		
		
		//Document this...what's going on in each line?... there should be a comment for each line.
		for(int count = 0;count < MAX_CIRCLES; count++)
		{ 
			b[count].setY(b[count].getY()+b[count].getyVelocity());
			
			b[count].setX(b[count].getX()+b[count].getxVelocity());
			
			
			//y[count] += yvelocity[count];
			
			if(b[count].getY()>this.getHeight() || b[count].getY()<0)
			{
				b[count].setyVelocity(-b[count].getyVelocity());

			}
			
			if(b[count].getX()>this.getWidth()||b[count].getX()<0)
			{
				b[count].setxVelocity(-b[count].getxVelocity());
				
			}
			if((b[count].getY()<0)||(b[count].getX()<0)||b[count].getX()>this.getWidth()||(b[count].getY()>this.getHeight()))
		{
				b[count].makeBig(2);		
				
		}
			g.setFont(f1);
			g.drawString("Score:"+display, 670, 100);
			double x=b[count].getX()-control.getX1();
			double y=b[count].getY()-control.getY1();
if((Math.abs(x)<b[count].getSize())&& (Math.abs(y)<b[count].getSize()))
			{
				display++;
					
					b[count].setX(999999);
					b[count].setY(999999);
					Font f10=new Font("Forte",Font.BOLD,40);
					
				
			} 
			
			
			
			
//			if((b[count].getY()==600)||(b[count].getX()==400)) {
//				b[count].setyVelocity(-b[count].getyVelocity());
//				b[count].setxVelocity(-b[count].getxVelocity());			}
//		
			
		
			
		g2.setPaint(b[count].getColor());	
		circle2=new Ellipse2D.Double(control.getX1(),control.getY1(),control.getSize1(),control.getSize1());	
			circle =new Ellipse2D.Double(b[count].getX(),b[count].getY(),b[count].getSize(),b[count].getSize());	
			g2.fill(circle);
			g3.setPaint(control.getColor());
			g3.fill(circle2);
	
		
		}
		
	}
	

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		
		
			int x=e.getX();
			int y=e.getY();
			for(int i=0;i<5;i++) {
			resetCircle(locator,x,y);
			locator++;
			
			}
			
		
	

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	for (int i=0; i<MAX_CIRCLES;i++){
		b[i].setxVelocity(0);
		b[i].setyVelocity(0);
	
		}
	}
	

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i=0; i<MAX_CIRCLES;i++){
			 b[i].setyVelocity((int)(Math.random()*MAX_VELOCITY)+2);
				b[i].setxVelocity((int)(Math.random()*MAX_VELOCITY)+2);


	
		}
	}

	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		for (int i=0; i<MAX_CIRCLES;i++){
			 b[i].setSize((int)(MAX_SIZE)*2);
		
				
	}
	}
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
			
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void up() {
	control.setY1(control.getY1()-0.5);
	control.setX1(control.getX1());
	}
	public void down() {
		control.setY1(control.getY1()+0.5);
		control.setX1(control.getX1());
	}
	public void left() {
		control.setX1(control.getX1()-0.5);
		control.setY1(control.getY1());
	}
	public void right() {
		control.setX1(control.getX1()+0.5);
		control.setY1(control.getY1());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<MAX_CIRCLES;i++) {
		int compare=e.getKeyCode();
		if(compare==KeyEvent.VK_A)
			setBackground(Color.orange);
		if(compare==KeyEvent.VK_S)
			setBackground(Color.blue);
		if(compare==KeyEvent.VK_D)
			setBackground(Color.green);
		if(compare==KeyEvent.VK_F)
			setBackground(Color.LIGHT_GRAY);
		if(compare==KeyEvent.VK_UP) {
			up();
		}
		if(compare==KeyEvent.VK_DOWN)
			down();
		if(compare==KeyEvent.VK_LEFT)
		left();
		if(compare==KeyEvent.VK_RIGHT)
			right();
		if(compare==KeyEvent.VK_ALT) {
			b[i].setSize(b[i].getSize()/4);
		}
		if(compare==KeyEvent.VK_ENTER) {
			
			
			b[i].setxVelocity(b[i].getxVelocity()*5);
			b[i].setyVelocity(b[i].getyVelocity()*5);
		
	}
		if(compare==KeyEvent.VK_BACK_SPACE) {
			for(int x=0;x<MAX_CIRCLES;x++) {
				b[i].setX(99999);
				b[i].setY(99999);
			}
				
		}
if(compare==KeyEvent.VK_SHIFT) {
			
			
			b[i].setxVelocity(b[i].getxVelocity()/20);
			b[i].setyVelocity(b[i].getyVelocity()/20);
		
	}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	

package bubblesAPCS;

import java.awt.Color;
import java.awt.Paint;

public class Circle {
	private double x;
	private double y;
	private int yVelocity;
	private double size;
	private int xVelocity;
	private Color c;
	private double radius;
	private Color color;
	private static final double PI=3.14;
	
private Circle circle;
public Circle() {
radius=20;


}
	public double getX1() {
		return x;
	}
	public double getY1() {
		return y;
	}
	public void setX1(double x) {
	this.x=x;
	}
	public void setY1(double y) {
		this.y=y;
	}
	public int getxV() {
		return xVelocity;
	}
	public int getyV() {
		return yVelocity;
	}
	public void setxV(int xVelocity) {
		this.xVelocity=xVelocity;
	}
	public void setyV(int yVelocity) {
		this.yVelocity=yVelocity;
	}
	public double getSize1() {
		return size;
	}
	public void setSize1(double size) {
		this.size=size;
}
	public void makeBig(int randSize) {
		size += randSize;
	}
	public void makeSmall(int randSize) {
		size -=randSize;
	}

	public void addyVel(int Velocity) {
		y += Velocity;
	}
	public void addxVel(int Velocity) {
		x += Velocity;
	}
	public Color getColor() {
		// TODO Auto-generated method stub
		return c;
	}
	public void setColor(Color x) {
		// TODO Auto-generated method stub
		this.c=x;
	}


}

package bubblesAPCS;

import java.awt.Color;



public class BubbleApplet {
	
	private double x;
	private double y;
	private int yVelocity;
	private double size;
	private int xVelocity;
	private Color c;
private Circle circle;




	public BubbleApplet(){
      int z= (int)(Math.random()*6);
      if(z==1){
    	 c=Color.blue;}
      if(z==2){
    	  c=Color.magenta;}
      if(z==3){
    	  c=Color.green;}
    	  if(z==4){
    		  c=Color.orange;
    		  
    	  }
    	  if(z==5){
    		  c=Color.RED;
    		  
    	  }
      
		x = (int)(Math.random()*100);
		y = (int)(Math.random()*100);
		yVelocity = (int)(Math.random()*100);
		xVelocity = (int)(Math.random()*100);
		size = (int)(Math.random()*100);
	 
	}
	
	public Color getColor(){
		return c;
	}
	public void setColor(Color newc) {
		this.c=newc;
	}
	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
	public void makeBig(int randSize) {
		size += randSize;
	}
	public void makeSmall(int randSize) {
		size -=randSize;
	}

	public void addyVel(int Velocity) {
		y += Velocity;
	}
	public void addxVel(int Velocity) {
		x += Velocity;
	}

}



