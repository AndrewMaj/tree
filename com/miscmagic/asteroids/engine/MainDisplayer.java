package com.miscmagic.asteroids.engine;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;


public class MainDisplayer extends Applet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WINDOWHEIGHT = 500;
	public static final int WINDOWWIDTH = 500;
	int x, y, xVelocity, yVelocity; //velocity variables store the 
	//direction the circle is currently moving.
	Thread thread; 
	long startTime, endTime, framePeriod;
	Dimension dim; // stores the size of the back buffer
	Image img; // the back buffer object
	Graphics g; // used to draw on the back buffer
	private Tree statustree;
	private float scale=1.0f;
	private int delta =0;
	private Random rand;
	private static Point origin = new Point(WINDOWWIDTH/2,10);
	
	private void createTestTree() {
		statustree = new StandardTree();
		Tree childtree1 = new StandardTree();
		  Tree childtree1_1 = new StandardTree();
		  Tree childtree1_2 = new StandardTree();
		  childtree1.addChildTree(childtree1_1);
		  childtree1.addChildTree(childtree1_2);
		   		   
		Tree childtree2 = new StandardTree();
		  Tree childtree2_1 = new StandardTree();
		  Tree childtree2_2 = new StandardTree();
		  childtree2.addChildTree(childtree2_1);
		  childtree2.addChildTree(childtree2_2);
	      
		statustree.addChildTree(childtree1);
		statustree.addChildTree(childtree2); 
	       
	    
		
	}

	
	private Tree createSimpleTestTree() {

		Tree childtree1 = new StandardTree();
		  Tree childtree1_1 = new StandardTree();
		  Tree childtree1_2 = new StandardTree();
		  childtree1.addChildTree(childtree1_1);
		  childtree1.addChildTree(childtree1_2);
		  childtree1.addChildTree(childtree1_2);
	      

 
	       
	    return childtree1;
		
	}
	
	private void createTestTree2() {
		statustree = new StandardTree();
		Tree childtree = new StandardTree();
		
		Tree t = new StandardTree();
		   Tree one = new StandardTree();
				
	 	   Tree two = new StandardTree();
		   
		t.addChildTree(one);
		t.addChildTree(two);
		t.addChildTree(two);

		
		statustree.addChildTree(childtree);
//		statustree.addChildTree(t);
//		statustree.addChildTree(t);
		statustree.addChildTree(childtree);
//		statustree.addChildTree(childtree);
//		statustree.addChildTree(childtree);
//		statustree.addChildTree(childtree);
				
		statustree.addChildTree(t);
		
//		//childtree.addChildTree(new StandardTree());
//		//childtree.addChildTree(new StandardTree());
//		childtree.addChildTree(new StandardTree());
		
//		Tree childtree2 = new StandardTree();
//		childtree2.addChildTree(new StandardTree());
//		testtree.addChildTree(childtree2);
		
		scale = 1.0f;
	}
	
	// Run a rest command over to the task service to get the status
	private Tree getStatus() {
		// let's just set colors to animate
		List<Tree> childtrees = statustree.getChildren();
		//for (Tree tree: childtrees) {
		Tree tree = childtrees.get(rand.nextInt(childtrees.size()));
			tree.getNode().setColor(getRandomColor());
	//	}
		return statustree;
	}

	
	
	private Color getRandomColor() {
		Color umph = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
		return umph;
	}


	public void paint(Graphics gfx){ 
		g.setColor(Color.black); //Notice these first four lines all
		g.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT); //use g instead of gfx now. g draws
		g.setColor(Color.red); //to the back buffer, while gfx draws
		
		statustree.draw(g, 1, scale);	
		gfx.drawImage(img,0,0,this); //copies back buffer to the screen
	} 

	public void init(){ 
		this.resize(500,500); 
		x=225; 
		y=225; 
		startTime=0; 
		endTime=0; 
		xVelocity=0; //the circle is not moving when the applet starts
		yVelocity=0; 
		 addKeyListener(this); 
		framePeriod=25; 
		dim=getSize(); //set dim equal to the size of the applet
		img=createImage(dim.width, dim.height);//create the back buffer
		g=img.getGraphics(); //retrieve Graphics object for back buffer
		
		// let's create a test tree
		createTestTree2();
		//testtree = createSimpleTestTree();
		rand = new Random();
		
		thread=new Thread(this); 
		thread.start(); 
	} 

	

	public void update(Graphics gfx) {
		paint(gfx);
	}


	@Override
	public void run(){ 
		for(;;){//this infinite loop ends when the webpage is exited
			startTime=System.currentTimeMillis();
			if (rand.nextInt(100) > 95) {
			  statustree = getStatus();
			}
			repaint(); 
			try{ 
				endTime=System.currentTimeMillis(); 
				if(framePeriod-(endTime-startTime)>0) 
					Thread.sleep(framePeriod-(endTime-startTime)); 
			}catch(InterruptedException e){ 
			} 
		} 
	} 
	


	public void keyPressed(KeyEvent e){ 
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			origin.translate(0,-5);
		} else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			origin.translate(0,5);
		} else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			origin.translate(-5,0);
		} else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			origin.translate(5,0);
		} else if(e.getKeyCode()==KeyEvent.VK_A) {
			scale += 0.1;		    
		} else if(e.getKeyCode()==KeyEvent.VK_Z) {
			if (scale > 0.5) {
			   scale -= 0.1;	
			}
		}

	} 


	public void keyReleased(KeyEvent e){ //stops moving the circle when the 
		//arrow key is released
		if(e.getKeyCode()==KeyEvent.VK_UP || 
				e.getKeyCode()==KeyEvent.VK_DOWN) 
			yVelocity=0; //stops vertical motion when either up or down 
		//is released
		else if(e.getKeyCode()==KeyEvent.VK_LEFT || 
				e.getKeyCode()==KeyEvent.VK_RIGHT) 
			xVelocity=0; //stops horizontal motion when either right or 
		//left is released
	} 

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	public static Point getOrigin() {
		return origin;
	}


	public static void setOrigin(Point org) {
		origin = org;
	} 

}
