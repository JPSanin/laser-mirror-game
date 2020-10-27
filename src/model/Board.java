package model;
/**
 * Class that represents the game board

 * @author: Juan P. Sanin

 * @version: 1.1 26/10/2020 

 */
public class Board {
	private int rows;
	private int columns;
	private int mirrors;
	private int mirrorsAdded;
	private int mirrorsFound;
	private int shots;
	private int locates;
	private int errors;
	//private String viewBoard;
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
		mirrorsAdded=0;
		shots=0;
		locates=0;
		errors=0;

	}


	/** 
	 * 
	 *	Method for generating the game board <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a game board using recursive methods vertically and horizontally, also places mirrors<br>
	 */
	public void generateBoard() {
		addCellsHorizontal(1,1,firstCell);
		addCellsVertical(2,1,firstCell,firstCell);
		placeMirrors(firstCell,firstCell);
	}


	/** Method for adding the first row of cells <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates first row<br>
	 * @param rowCounter, int representing the amount of rows that have been added
	 * @param columnColumn, int representing the amount of columns that have been added
	 * @param current, cell representing the current cell that will be added to
	 */
	private void addCellsHorizontal(int rowCounter, int columnCounter,Cell current) {
		Cell newCell=null;
		char x= (char)(columnCounter+64);
		newCell=new Cell(false,3,rowCounter,x);


		if(rowCounter==1) {
			if(columnCounter==1) {
				firstCell=newCell;
				columnCounter++;
				addCellsHorizontal(rowCounter, columnCounter, firstCell);
			}else if(columnCounter<columns) {
				newCell.setLeft(current);
				current.setRight(newCell);
				columnCounter++;
				addCellsHorizontal(rowCounter,columnCounter,newCell);
			}else if(columnCounter==columns) {
				newCell.setLeft(current);
				current.setRight(newCell);	
			}

		}
	}


	/** Method for adding the columns and linking them in all four directions <br>
	<b> pre: </b> <br>
	<b> post: </b> Completes the full grid of the game board <br>
	 * @param rowCounter, int representing the amount of rows that have been added
	 * @param columnColumn, int representing the amount of columns that have been added
	 * @param current, cell representing the current cell that will be added to
	 * @param colHead, cell representing the first cell of the column, it is used to 
	 * switch columns once all cells have been added
	 */
	private void addCellsVertical(int rowCounter, int columnCounter,Cell current, Cell colHead) {
		Cell newCell=null;
		char x= (char)(columnCounter+64);
		newCell=new Cell(false,3,rowCounter,x);


		if(columnCounter==1) {
			if(rowCounter<rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				rowCounter++;
				addCellsVertical(rowCounter,columnCounter,newCell,colHead);
			}else if(rowCounter==rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				rowCounter=2;
				columnCounter++;
				addCellsVertical(rowCounter,columnCounter,colHead.getRight(),colHead.getRight());
			}
		}else if(columnCounter<columns)  {
			if(rowCounter<rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				newCell.setLeft(current.getLeft().getDown());
				current.getLeft().getDown().setRight(newCell);
				rowCounter++;
				addCellsVertical(rowCounter,columnCounter,newCell, colHead);
			}else if(rowCounter==rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				newCell.setLeft(current.getLeft().getDown());
				current.getLeft().getDown().setRight(newCell);
				rowCounter=2;
				columnCounter++;
				addCellsVertical(rowCounter,columnCounter,colHead.getRight(),colHead.getRight());
			}

		}else if(columnCounter==columns) {
			if(rowCounter<rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				newCell.setLeft(current.getLeft().getDown());
				current.getLeft().getDown().setRight(newCell);
				rowCounter++;
				addCellsVertical(rowCounter,columnCounter,newCell, colHead);
			}else if(rowCounter==rows) {
				current.setDown(newCell);
				newCell.setUp(current);
				newCell.setLeft(current.getLeft().getDown());
				current.getLeft().getDown().setRight(newCell);
			}
		}

	}

	
	//Trabajar para que siempre llene el tablero
	private void placeMirrors(Cell current, Cell rowHead) {
		if(mirrorsAdded<mirrors) {
			if(current.isMirror()==false) {
			int mirrorRandom=(int)(Math.random()*10+1);
			if(mirrorRandom==1) {
				int mirDir=(int)(Math.random()*2+1);
				current.setMirror(true);
				current.setMirrorDir(mirDir);
				mirrorsAdded++;
			}
			if((int)(current.getCol()-64)==columns && current.getRow()==rows) {
				placeMirrors(firstCell,firstCell);
			}else if((int)(current.getCol()-64)<columns) {
				placeMirrors(current.getRight(),rowHead);
			}else if((int)(current.getCol()-64)==columns) {
				placeMirrors(rowHead.getDown(),rowHead.getDown());
			}
			
			}
		}
		
		
		//Traverse grid and set cell with mirrors using randoms
		//	int addMirror=(int)(Math.random()*10+1);
	}

	public void shoot() {}
	public void locate() {}
	public void calculateScore() {}

	public String printBoard() {
		String info="";
		info+=firstCell;
		info+=firstCell.getRight();
		info+=firstCell.getRight().getRight();
		info+=firstCell.getRight().getRight().getRight()+"\n";
		info+=firstCell.getDown();
		info+=firstCell.getDown().getRight();
		info+=firstCell.getDown().getRight().getRight();
		info+=firstCell.getDown().getRight().getRight().getRight()+"\n";
		info+=firstCell.getDown().getDown();
		//info+=firstCell.getDown().getDown().getRight();
		//info+=firstCell.getDown().getDown().getRight().getRight();
		//info+=firstCell.getDown().getDown().getRight().getRight().getRight()+"\n";


		return info;
	}
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
