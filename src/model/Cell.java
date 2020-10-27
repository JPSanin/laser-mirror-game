package model;


/**
 * Class that represents the cells of the game board

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:20 PM

 */

public class Cell {
	private char col;
	private int row;
	
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
	 * @param col, char indicating the column of the cell
	 * @param row, int indicating the row of the cell
	 */
	public Cell(boolean mirror, int mirrorDir, int row, char col) {
		this.mirror = mirror;
		this.mirrorDir = mirrorDir;
		this.col=col;
		this.row=row;
		
	}
	
	@Override
	public String toString() {
		String info="";
		if(mirror==true) {
			if(mirrorDir==1) {
				info="[/]";
			}
			if(mirrorDir==2) {
				info="[\\]";
			}
			
		}else {
			info="[ ]";
		}
		//info="["+row+col+"]";
		return info;
	}



	/**
	 *Getters and Setters 
	 */
	 

	public char getCol() {
		return col;
	}


	public void setCol(char col) {
		this.col = col;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
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
