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
		
	}
	
	private Cell shooter(Cell current, int dir) {
		Cell end=null;
		switch(dir) {
		case 1:
			if(current.getUp()==null) {
				end=current;
			}else {
				if(current.getUp().isMirror()==true) {
					if(current.getUp().getMirrorDir()==1) {
						end=shooter(current.getUp(),3);
					}else if(current.getUp().getMirrorDir()==2) {
						end=shooter(current.getUp(),4);
					}
				}else {
					end=shooter(current.getUp(),1);
				}
			}
			break;
		case 2:
			if(current.getDown()==null) {
				end=current;
			}else {
				if(current.getDown().isMirror()==true) {
					if(current.getDown().getMirrorDir()==1) {
						end=shooter(current.getDown(),4);
					}else if(current.getDown().getMirrorDir()==2) {
						end=shooter(current.getDown(),3);
					}
				}else {
					end=shooter(current.getDown(),2);
				}
			}
			break;
		case 3:
			if(current.getRight()==null) {
				end=current;
			}else {
				if(current.getRight().isMirror()==true) {
					if(current.getRight().getMirrorDir()==1) {
						end=shooter(current.getRight(),1);
					}else if(current.getRight().getMirrorDir()==2) {
						end=shooter(current.getRight(),2);
					}
				}else {
					end=shooter(current.getRight(),3);
				}
			}
			break;
		case 4:
			if(current.getLeft()==null) {
				end=current;
			}else {
				if(current.getLeft().isMirror()==true) {
					if(current.getLeft().getMirrorDir()==1) {
						end=shooter(current.getLeft(),2);
					}else if(current.getLeft().getMirrorDir()==2) {
						end=shooter(current.getLeft(),1);
					}
				}else {
					end=shooter(current.getLeft(),4);
				}
			}
			break;
			
		}
		return end;
	}
	
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
	
	public void locate() {}
	public void calculateScore() {}
	
	
	/** Method for showing the game current screen <br>
	<b> pre: </b> <br>
	<b> post: </b> Shows the current state of the game <br>
	 * @param nickname, String with the current players nickname 
	 */
	public String showBoardScreen(String nickname) {
		String info="";
		int remainingMirrors= mirrors-mirrorsFound;
		info+= nickname+": "+remainingMirrors+ " mirrors remaining\n";
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
