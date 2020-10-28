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
	private String viewBoard;
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
		viewBoard="";
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
		printBoard(firstCell,firstCell);
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

	
	/** Method for setting the amount of mirrors randomly <br>
	<b> pre: </b> <br>
	<b> post: </b> Adds all mirrors to cells <br>
	 * @param current, cell representing the current cell that the mirror will be set to
	 * @param rowHead, cell representing the first cell of the row, it is used to 
	 * switch rows once all horizontal cells have been traversed
	 */
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

	
	/** Method for starting the shot process <br>
	<b> pre: </b> <br>
	<b> post: </b> Shoots and prints the start and end of the shot<br>
	 * @param row, the row of the starting cell
	 * @param column, the column of the starting cell
	 * @param dir, the direction the laser is shot
	 */
	public void shootStarter(int row, char col, int dir) {
		Cell start=null;
		Cell end=null;
		start=searchBorder(row,col,firstCell);
		start.setStart(true);
		end=shooter(start,dir);
		end.setEnd(true);
		viewBoard="";
		printBoard(firstCell,firstCell);
		start.setStart(false);
		end.setEnd(false);
		shots++;
		
	}
	
	/** Method that shoots the laser <br>
	<b> pre: </b> Found starting cell and entered it as first current<br>
	<b> post: </b> Finds the end of the shot<br>
	 * @param current, current cell the shot is at
	 * @param dir, direction the shot has
	 * @return end, the cell that the shot comes out of
	 * 
	 */
	private Cell shooter(Cell current, int dir) {
		Cell end=null;
		switch(dir) {
		case 1:
			if(current.isMirror()==true) {
				if(current.getMirrorDir()==1) {
					if(current.getRight()==null) {
						end=current;
					}else {
						end=shooter(current.getRight(),3);
					}
				}else if(current.getMirrorDir()==2) {
					if(current.getLeft()==null) {
						end=current;
					}else {
						end=shooter(current.getLeft(),4);
					}
				}
			}else if(current.getUp()==null){
				end=current;
			}else {
				end=shooter(current.getUp(),1);
			}
			break;
		case 2:
			if(current.isMirror()==true) {
				if(current.getMirrorDir()==1) {
					if(current.getLeft()==null) {
						end=current;
					}else {
						end=shooter(current.getLeft(),4);
					}
				}else if(current.getMirrorDir()==2) {
					if(current.getRight()==null) {
						end=current;
					}else {
						end=shooter(current.getRight(),3);
					}
				}
			}else if(current.getDown()==null){
				end=current;
			}else {
				end=shooter(current.getDown(),2);
			}
			break;
		case 3:
			if(current.isMirror()==true) {
				if(current.getMirrorDir()==1) {
					if(current.getUp()==null) {
						end=current;
					}else {
						end=shooter(current.getUp(),1);
					}
				}else if(current.getMirrorDir()==2) {
					if(current.getDown()==null) {
						end=current;
					}else {
						end=shooter(current.getDown(),2);
					}
				}
			}else if(current.getRight()==null){
				end=current;
			}else {
				end=shooter(current.getRight(),3);
			}
			break;
		case 4:
			if(current.isMirror()==true) {
				if(current.getMirrorDir()==1) {
					if(current.getDown()==null) {
						end=current;
					}else {
						end=shooter(current.getDown(),2);
					
					}
				}else if(current.getMirrorDir()==2) {
					if(current.getUp()==null) {
						end=current;
					}else {
						end=shooter(current.getUp(),1);
					}
				}
			}else if(current.getLeft()==null){
				end=current;
			}else {
				end=shooter(current.getLeft(),4);
			}
			break;
			
		}
		return end;
	}
	
	
	/** Method that finds the starting cell of the shot <br>
	<b> pre: </b> row and col have to be of a border cell, first current has to be the firstCell<br>
	<b> post: </b> Finds the end of the shot<br>
	 * @param row, row of the searched cell
	 * @param col, column of the searched cell
	 * @param current, current cell that we are seeing if it is the start
	 * @return searched, the cell we are looking for on the game border
	 * 
	 */
	private Cell searchBorder(int row, char col, Cell current) {
		Cell searched=null;
		if(row==current.getRow()&&col==current.getCol()) {
			searched=current;
		}else {
			if(current.getRight()!=null && current.getUp()==null) {
				searched=searchBorder(row,col,current.getRight());
			}else if(current.getRight()==null && current.getDown()!=null) {
				searched=searchBorder(row,col,current.getDown());
			}else if(current.getRight()==null && current.getDown()==null) {
				searched=searchBorder(row,col,current.getLeft());
			}else if(current.getLeft()!=null && current.getDown()==null) {
				searched=searchBorder(row,col,current.getLeft());
			}else if(current.getLeft()==null && current.getDown()==null) {
				searched=searchBorder(row,col,current.getUp());
			}else if(current.getUp()!=null && current.getLeft()==null ) {
				searched=searchBorder(row,col,current.getUp());
			}
		}
		return searched;
	}
	
	
	/** Method for locating a cell <br>
	<b> pre: </b> <br>
	<b> post: </b> Finds the cell and prints the result of what it is<br>
	 * @param row, the row of the  cell
	 * @param col, the column of the cell
	 * @param mirDir, the direction of the mirror
	 */
	public void locate(int row, char col, int mirDir) {
		Cell colHead=findColumnHead(col,firstCell);
		Cell located=findCell(row,colHead);
		if(located.isMirror()==true) {
			if(mirDir==located.getMirrorDir()) {
				located.setFound(true);
				mirrorsFound++;
			}else {
				located.setError(2);
				errors++;
			}
		}else {
			located.setError(1);
			errors++;
		}
		viewBoard="";
		printBoard(firstCell,firstCell);
		locates++;
		
	}
	
	/** Method for finding the column head <br>
	<b> pre: </b> <br>
	<b> post: </b> Finds the column head and returns it<br>
	 * @param col, the column of the  cell
	 * @param current, current cell it is searching 
	 * @return head, the first cell of the column
	 */
	private Cell findColumnHead(char col, Cell current) {
		Cell head=null;
		if(col==current.getCol()) {
			head=current;
		}else {
			head=findColumnHead(col,current.getRight());
		}
		return head;
	}
	
	
	/** Method for finding the cell in column <br>
	<b> pre: </b> <br>
	<b> post: </b> Finds the cell and returns it<br>
	 * @param row, the row of the  cell
	 * @param current, current cell it is searching 
	 * @return cell, the searched cell in the column
	 */
	private Cell findCell(int row, Cell current) {
		Cell cell=null;
		if(row==current.getRow()) {
			cell=current;
		}else {
			cell=findCell(row,current.getDown());
		}
		return cell;
	}
	
	
	public void calculateScore() {}
	
	public boolean checkWin() {
		boolean win= false;
		if(mirrorsFound==mirrors) {
			win=true;
		}
		return win;
	}
	
	/** Method for showing the game current screen <br>
	<b> pre: </b> <br>
	<b> post: </b> Shows the current state of the game <br>
	 * @param nickname, String with the current players nickname
	 * @return info, String with all the information 
	 */
	public String showBoardScreen(String nickname) {
		String info="";
		int remainingMirrors= mirrors-mirrorsFound;
		info+= nickname+": "+remainingMirrors+ " mirrors remaining\n";
		info+=viewBoard;
		return info;
		
	}
	
	/** Method for showing the hacked game screen <br>
	<b> pre: </b> <br>
	<b> post: </b> Shows the solved board <br>
	 * @param nickname, String with the current players nickname
	 * @return info, String with all the information 
	 */
	public String showBoardHacked(String nickname) {
		String info="";
		info+= nickname+ " HAS HACKED THE BOARD !!!\n";
		viewBoard="";
		printBoardHacks(firstCell,firstCell);
		info+=viewBoard;
		return info;
		
	}

	
	/** Method for saving the representation of the game board into a string <br>
	<b> pre: </b> <br>
	<b> post: </b> Adds the string of each cell to a the game board <br>
	 * @param current, cell representing the current cell 
	 * @param rowHead, cell representing the first cell of the row, it is used to 
	 * switch rows once all horizontal cells have been traversed
	 */
	private void printBoard(Cell current, Cell rowHead) {
		if((int)(current.getCol()-64)==columns && current.getRow()==rows) {
			viewBoard+=current+"\n";
		}else if((int)(current.getCol()-64)<columns) {
			viewBoard+=current;
			printBoard(current.getRight(),rowHead);
		}else if((int)(current.getCol()-64)==columns) {
			viewBoard+=current+"\n";
			printBoard(rowHead.getDown(),rowHead.getDown());
			
		}
	}
	
	/** Method for saving the representation of the hacked game board into a string <br>
	<b> pre: </b> <br>
	<b> post: </b> Adds the hacked string of each cell to a the game board <br>
	 * @param current, cell representing the current cell 
	 * @param rowHead, cell representing the first cell of the row, it is used to 
	 * switch rows once all horizontal cells have been traversed
	 */
	private void printBoardHacks(Cell current, Cell rowHead) {
		if((int)(current.getCol()-64)==columns && current.getRow()==rows) {
			viewBoard+=current.hacks()+"\n";
		}else if((int)(current.getCol()-64)<columns) {
			viewBoard+=current.hacks();
			printBoardHacks(current.getRight(),rowHead);
		}else if((int)(current.getCol()-64)==columns) {
			viewBoard+=current.hacks()+"\n";
			printBoardHacks(rowHead.getDown(),rowHead.getDown());
			
		}
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


	public String getViewBoard() {
		return viewBoard;
	}


	public void setViewBoard(String viewBoard) {
		this.viewBoard = viewBoard;
	}




}
