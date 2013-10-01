package com.miscmagic.asteroids.engine;

import java.awt.Color;
import java.awt.Graphics;

/*
 *    Describe how the element looks
 */
public interface Node {

	public Color getColor();
	
	public void setColor(Color color);
	 
	public void setScale(float scale);
	
	public float getScale();

	public int getDrawingWidth();

	int getDrawingHeight();
	
	// it's location x & y
	int originx();
	int originy();
	
	// If this node has siblings, this number refers to its ordinal, and therefore order left to right.
	int seriesNumber();

	void draw(Graphics g, Tree parent, int seriesnumber, float scale);

	public int getNodeWidth();

	public int getNodeHeight();
	
	
	
	
}
