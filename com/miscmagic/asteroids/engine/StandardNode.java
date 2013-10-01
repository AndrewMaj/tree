package com.miscmagic.asteroids.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;


public class StandardNode implements Node {

	public static final int HEIGHT = 10;
	public static final int WIDTH = 20;
	public static final int HSPACE = 30;
	public static final int VSPACE = 20;
	
	Color color = Color.BLUE;
	private int seriesnumber = -1;
	private int nodex = MainDisplayer.WINDOWWIDTH/2;
	private int nodey = 10;
	
	public StandardNode() {
	
	}
	
	public StandardNode(Color color) {
		this.color = color;
	}
	
	@Override
	public Color getColor() {	
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void setScale(float scale) {
	//	this.scale = scale;
	}

	@Override
	public float getScale() {		
		return 0.0f;
	}

	/**
	 * Returns unscaled size of the object
	 */
	@Override
	public int getDrawingWidth() {	
		return WIDTH;
	}

	
	@Override
	public int getDrawingHeight() {	
		return HEIGHT;
	}



	@Override
	public void draw(Graphics g, Tree parent, int seriesnumber, float scale) {
		
		 g.setColor(getColor());
		
		 if (parent == null) {
			 // Then we are a root node
			 // so just draw ourselves at the top
			 nodex = MainDisplayer.WINDOWWIDTH/2;
			 nodey = 10;
			 g.fill3DRect(nodex,nodey,(int)(WIDTH*scale), (int)(HEIGHT*scale), true);
		 }  else {
		     // compute where we should be drawn given the number of siblings we have and our order within them
			 int dadx = parent.getNode().originx();
			 int dady = parent.getNode().originy();
			 // this.getDrawingWidth()*numnodes at this level.;
			 // figure how to partition the space
			 // At this level we are numnodes*the node drawing width wide
			 List<Tree> childtrees = parent.getChildren();
			 int levelwidth = 0;
			 for (Tree child: childtrees) {
				 levelwidth += child.getNode().getDrawingWidth();
			 }
			 

			 // The width of our whole shooting match
			 int width = parent.getDrawingWidth(scale);

			 // distance from starting x location is  (parent nodes x - half the total width of theline of nodes) to next node is
			 // space divided up by # children -1 + nodewidth * seriesnumber
			 int startx = dadx - width/2;
			 
			 int totalspace = width-levelwidth;
			 int spacerportion;
			 // Prevent a div 0 error...  one node means it get all of any space
			 if (childtrees.size() == 1) {
				 spacerportion = 0;
			 } else {
				 spacerportion = totalspace/(childtrees.size()-1);
			 }
			 int nextnodedist =  (WIDTH+spacerportion)*(seriesnumber-1);
			 int drawingx = startx + nextnodedist + (parent.getNode().getDrawingWidth()/2);
			 
			 nodey = dady+parent.getVSpaceWidth(scale);
			 nodex = drawingx; 
			 g.fill3DRect(nodex,nodey,(int)(WIDTH*scale), (int)(HEIGHT*scale), true);
			 
			 // Now draw our line back to our parent
			 int parentnodewidth = parent.getNode().getNodeWidth(); 		
			 int parentnodeheight = parent.getNode().getNodeHeight();
			 g.drawLine(dadx+(int)(parentnodewidth*scale/2), dady+(int)(parentnodeheight*scale), nodex+(int)((WIDTH*scale)/2), nodey);
		 }
	}
	
	@Override
	public int originx() {
		return nodex;
	}

	@Override
	public int originy() {
		return nodey;
	}

	@Override
	public int seriesNumber() {
		return seriesnumber ;
	}

	@Override
	public int getNodeWidth() {
		return WIDTH;
	}

	@Override
	public int getNodeHeight() {
		return HEIGHT;
	}

}
