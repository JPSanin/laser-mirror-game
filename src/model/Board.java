package model;
/**
 * Class that represents the game board

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:30 PM

 */
public class Board {
	private int rows;
	private int columns;
	private int mirrors;
	private int mirrorsFound;
	private int shots;
	private int locates;
	private int errors;
	private Cell firstCell;
	
	/** 
	 * 
	 *	Constructor method for a Board <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a game board using input<br>
	 * @param rows, int representing the amount of rows
	 * @param columns, int representing the amount of columns
	 * @param mirrors, int representing the amount of mirrors on the board
	 */
	public Board(int rows, int columns, int mirrors) {
		this.rows = rows;
		this.columns = columns;
		this.mirrors = mirrors;
		mirrorsFound=0;
		shots=0;
		locates=0;
		errors=0;
	}
	
	public void generateBoard() {}
	public void shoot() {}
	public void locate() {}
	public void calculateScore() {}

	
	/**
	 *Getters and Setters 
	 */
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getMirrors() {
		return mirrors;
	}

	public void setMirrors(int mirrors) {
		this.mirrors = mirrors;
	}

	public int getMirrorsFound() {
		return mirrorsFound;
	}

	public void setMirrorsFound(int mirrorsFound) {
		this.mirrorsFound = mirrorsFound;
	}

	public int getShots() {
		return shots;
	}

	public void setShots(int shots) {
		this.shots = shots;
	}

	public int getLocates() {
		return locates;
	}

	public void setLocates(int locates) {
		this.locates = locates;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public Cell getFirstCell() {
		return firstCell;
	}

	public void setFirstCell(Cell firstCell) {
		this.firstCell = firstCell;
	}
	
	
	

}
