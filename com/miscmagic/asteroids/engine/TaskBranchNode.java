package com.miscmagic.asteroids.engine;

import java.awt.Graphics;
import java.util.List;

public class TaskBranchNode extends BranchNode {
	
	enum TaskDiscipline { Ordered, Unordered }

	private TaskDiscipline discipline;;
	
	public TaskBranchNode(TaskDiscipline dis) {
		this.discipline = dis;
	}
	
	
	public void draw(Graphics g, Tree parent, int seriesnumber, float scale) {
				
		  g.setColor(getColor());
		
		 if (parent == null) {
			 // Then we are a root node
			 // so just draw ourselves at the top
			 setNodex((int) MainDisplayer.getOrigin().getX()-(int)((getWidth()*scale)/2));
			 setNodey((int) MainDisplayer.getOrigin().getY());
			 g.fill3DRect(getNodex(),getNodey(),(int)(getWidth()*scale), (int)(getHeight()*scale), true);
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
			 int nextnodedist =  (getWidth()+spacerportion)*(seriesnumber-1);
			 int drawingx = startx + nextnodedist + (parent.getNode().getDrawingWidth()/2);
			 
			 setNodey(dady+parent.getVSpaceWidth(scale));
			 setNodex(drawingx); 
			 g.fill3DRect(getNodex(),getNodey(),(int)(getWidth()*scale), (int)(getHeight()*scale), true);
			 
			 // Now draw our line back to our parent
			 int parentnodewidth = parent.getNode().getNodeWidth(); 		
			 int parentnodeheight = parent.getNode().getNodeHeight();
			 g.drawLine(dadx+(int)(parentnodewidth*scale/2), dady+(int)(parentnodeheight*scale), getNodex()+(int)((getWidth()*scale)/2), getNodey());
		 }
		 
		 if (discipline == TaskDiscipline.Ordered) {
			 // then we need to draw lines between the nodes
			 
			 
		 }

		 
	}

}
