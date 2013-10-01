package com.miscmagic.asteroids.engine;

import java.awt.Graphics;
import java.util.List;

public interface Tree {
	
	
	public void addChildTree(Tree childtree);
		
	public void setNode(Node leafnode);
	
	public boolean isLeaf();

	public Node getNode();

	public List<Tree> getChildren();

	int getDrawingWidth(float scale);

	public int getHSpaceWidth(float scale);

	public int getVSpaceWidth(float scale);

	void draw(Graphics g, int cnt, float scale);

	public Tree getParent();

	public void setParent(Tree tree);
	
	

}
