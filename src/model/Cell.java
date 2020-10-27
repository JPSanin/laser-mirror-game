package model;


/**
 * Class that represents the cells of the game board

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:20 PM

 */

public class Cell {
	private char posX;
	private int posY;
	
	private boolean mirror;
	private int mirrorDir;
	private boolean found;
	private Cell left;
	private Cell right;
	private Cell up;
	private Cell down;
	
	/** 
	 * 
	 *	Constructor method for a Cell <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a cell of the grid<br>
	 * @param mirror, boolean indicating if the cell has a mirror or not
	 * @param mirrorDir, int indicating the direction of the mirror (Right is 1, left is 2, no mirror is 3)
	 * @param posX, char indicating the column of the cell
	 * @param posY, int indicating the row of the cell
	 */
	public Cell(boolean mirror, int mirrorDir, char posX, int posY) {
		this.mirror = mirror;
		this.mirrorDir = mirrorDir;
		this.posX=posX;
		this.posY=posY;
		
	}


	/**
	 *Getters and Setters 
	 */
	 
	public char getPosX() {
		return posX;
	}


	public void setPosX(char posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean isMirror() {
		return mirror;
	}


	public void setMirror(boolean mirror) {
		this.mirror = mirror;
	}

	
	public int getMirrorDir() {
		return mirrorDir;
	}


	public void setMirrorDir(int mirrorDir) {
		this.mirrorDir = mirrorDir;
	}


	public boolean isFound() {
		return found;
	}


	public void setFound(boolean found) {
		this.found = found;
	}


	public Cell getLeft() {
		return left;
	}


	public void setLeft(Cell left) {
		this.left = left;
	}


	public Cell getRight() {
		return right;
	}


	public void setRight(Cell right) {
		this.right = right;
	}


	public Cell getUp() {
		return up;
	}


	public void setUp(Cell up) {
		this.up = up;
	}


	public Cell getDown() {
		return down;
	}


	public void setDown(Cell down) {
		this.down = down;
	}



	
	
	
	

}
