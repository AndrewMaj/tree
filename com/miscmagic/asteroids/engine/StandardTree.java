package com.miscmagic.asteroids.engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class StandardTree implements Tree {
	
	private static final int HORIZONTALSPACE = 30;
	private static final int VERTICLESPACE = 30;
	List<Tree> childtreelist;
	Node node;
	private Tree parent;
	
	public StandardTree() {
		childtreelist = new ArrayList<Tree>();
		node = new StandardNode();
	}
	
	@Override
	public void addChildTree(Tree childtree) {
		childtreelist.add(childtree);
		childtree.setParent(this);
		node = new BranchNode();  // maybe optimize this if it's a problem someday
	}

	@Override
	public void setNode(Node leafnode) {
		this.node = leafnode;
	}

	@Override
	public boolean isLeaf() {
		if (childtreelist.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void draw(Graphics g, int seriesnum, float scale) {
		
		// Draw our main node
		if (node != null) {
			node.draw(g, this.getParent(), seriesnum, scale);
		}  else {
			System.out.println("No leaf node, we are a broken tree definition.");
			return;
		}
		
		// If we have any children, draw them
		int cnt = 1;
		for (Tree childtree: childtreelist) {							
			childtree.draw(g, cnt++, scale);  // tell the child where the parent is.
		}				
		
	}

	@Override
	public int getDrawingWidth(float scale) {	
		int numchildren = childtreelist.size();
		int width = ((numchildren<=0) ? 0: numchildren-1) * (int)(HORIZONTALSPACE*scale) + node.getDrawingWidth();
		for (Tree child: childtreelist) {
			width += child.getDrawingWidth(scale);
		}
		return width;
	}

	@Override
	public Node getNode() {
		return node;
	}

	@Override
	public List<Tree> getChildren() {
		return childtreelist;
	}

	@Override
	public int getHSpaceWidth(float scale) {
		return (int) (HORIZONTALSPACE * scale);
	}

	@Override
	public int getVSpaceWidth(float scale) {
		return (int) (VERTICLESPACE*scale);
	}

	@Override
	public Tree getParent() {
	   return parent;
	}

	@Override
	public void setParent(Tree tree) {
       this.parent = tree;	
	}
	

}
