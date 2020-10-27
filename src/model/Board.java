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
	
	public void generateBoard() {
		addCell(1,1,firstCell);
	
	}
	
	private void addCell(int rowCounter, int columnCounter,Cell current) {
		Cell newCell=null;
		char x= (char)(columnCounter+64);
		System.out.println("yes");
		newCell=new Cell(false,3,rowCounter,x);
		/*if(mirrorsAdded<mirrors) {
			int addMirror=(int)(Math.random()*10+1);
			if(addMirror==1) {
				int mirDir=(int)(Math.random()*2+1);
				newCell=new Cell(true,mirDir,rowCounter,x);
				mirrorsAdded++;
			}else {
				newCell=new Cell(false,3,rowCounter,x);
			}
		}*/
		
		if(rowCounter==1) {
			if(columnCounter==1) {
				firstCell=newCell;
				columnCounter++;
				addCell(rowCounter, columnCounter, firstCell);
			}else if(columnCounter<columns) {
				newCell.setLeft(current);
				current.setRight(newCell);
				columnCounter++;
				addCell(rowCounter,columnCounter,newCell);
			}else if(columnCounter==columns) {
				newCell.setLeft(current);
				current.setRight(newCell);
				columnCounter=1;
				rowCounter++;
				addCell(rowCounter,columnCounter,newCell);
			}
			
		}else if(rowCounter>1 && rowCounter<=rows) {
			if(columnCounter==1) {
				//Search for upper (row-1,col)
				//newCell.setUp(searchResult);
				//searchResult.setDown(newCell);
				columnCounter++;
				addCell(rowCounter,columnCounter,newCell);
				
			}else if(columnCounter<columns) {
				//Search for upper (row-1,col)
				//newCell.setUp(searchResult);
				//searchResult.setDown(newCell);
				newCell.setLeft(current);
				current.setRight(newCell);
				columnCounter++;
				addCell(rowCounter,columnCounter,newCell);
			}else if(columnCounter==columns) {
				//Search for upper (row-1,col)
				//newCell.setUp(searchResult);
				//searchResult.setDown(newCell);
				newCell.setLeft(current);
				current.setRight(newCell);
				columnCounter=1;
				rowCounter++;
				addCell(rowCounter,columnCounter,newCell);
			}
		}
		
	}
	public void shoot() {}
	public void locate() {}
	public void calculateScore() {}

	public String printBoard() {
		String info="";
		info+=firstCell;
		info+=firstCell.getRight();
		info+=firstCell.getRight().getRight();
		info+=firstCell.getRight().getRight().getRight();
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
