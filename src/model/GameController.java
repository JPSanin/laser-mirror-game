package model;

import exceptions.*;

/**
 * Class that represents the Game Controller
 * this class will send the instructions to the board
 * or the score list
 * 
 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:53 PM

 */
public class GameController {
	private Board gameBoard;
	private ScoreList scoreBoard;
	
	/** 
	 * 
	 *	Constructor method for the Game Controller <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a Game Controller<br>
		
	 */
	public GameController() {
		gameBoard=null;
		scoreBoard=null;
	} 
	
	
	/** Method for sending shot information to the game board<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Sends shot information to game board<br>	
	 * @throws CannotShootException, when the cell is not a border
	 * @throws CornerException, when he shot is a corner and the direction is not specified or wrong 
	 * Direction is represented by an int 1=up, 2=down, 3= right, 4= left, 5= corner with wrong direction;
	*/
	public void shoot(String shot) throws CannotShootException, CornerException {
		String shotinfo = stringSplitter(shot,0);
		String[] parts=shotinfo.split(",");
		int row= Integer.parseInt(parts[0]);
		char col= parts[1].charAt(0);
		int dir=checkCorner(row,col, parts);
		if(dir==5 && parts[1].length()==1) {
			dir=findDir(row,col);
		}
		if(dir==5 && parts[1].length()>1) {
			throw new CornerException(row,col);
		}
		if(dir==0) {
			throw new CannotShootException(row,col);
		}
		gameBoard.shootStarter(row, col, dir);
	}
	
	/** Method for finding the direction of cells that are not corners<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Returns the initial direction of the shot<br>
	 *@param row, int representing the row of the shot
	 *@param col, int representing the column of the shot
	 *@return dir, an int representing the initial direction of the shot
	 *
	*/
	private int findDir(int row, char col) {
		int dir=0;
		if(row==1) {
			dir=2;
		}else if(row==gameBoard.getRows()) {
			dir=1;
		}else if(col==65) {
			dir=3;
		}else if(col== (gameBoard.getColumns()+64)) {
			dir=4;
		}
		return dir;
	}

	
	/** Method for checking if a cell is a corner and sending direction<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Returns the initial direction of the shot if it is a corner<br>
	 *@param row, int representing the row of the shot
	 *@param col, int representing the column of the shot
	 *@param parts, a string array containing the direction of the shot given by the user
	 *@return dir, an int representing the initial direction of the shot
	 *
	*/
	private int checkCorner(int row, char col, String[] parts) throws CornerException {
		int dir=5;
		if(row==1 && col==65) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=3;
				}else if(parts[1].charAt(1)=='V') {
					dir=2;
				}
			}else if (parts[1].length()==1) {
				throw new CornerException(row,col);
			}
			
		}else if(row==1 && col== (gameBoard.getColumns()+64)) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=4;
				}else if(parts[1].charAt(1)=='V') {
					dir=2;
				}
			}else if (parts[1].length()==1) {
				throw new CornerException(row,col);
			}
		}else if(row==gameBoard.getRows() && col==65) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=3;
				}else if(parts[1].charAt(1)=='V') {
					dir=1;
				}
			}else if (parts[1].length()==1) {
				throw new CornerException(row,col);
			}
		}else if(row==gameBoard.getRows() && col==(gameBoard.getColumns()+64)) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=4;
				}else if(parts[1].charAt(1)=='V') {
					dir=1;
				}
			}else if (parts[1].length()==1) {
				throw new CornerException(row,col);
			}
		}
		return dir;
	}
	
	
	

	/** Method for splitting Strings<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Returns the split string with row and column separated<br>
	 *@param str, String that wants to be split
	 *@param index, where it is going to be split 
	 *@return info, a string with the information separated by a comma so it can be split
	 *
	*/
	private String stringSplitter(String str, int index) {
		String info=null;
		if(!Character.isDigit(str.charAt(index))){
			info=str.substring(0, index) + "," + str.substring(index);
			return info;
		}else {
			index++;
			info=stringSplitter(str,index);
		}
		return info;
		
	}
	
	public void locate(String location) {
		String trimmedLoc=location.substring(1);
		String info = stringSplitter(trimmedLoc,0);
		String[] parts= info.split(",");
		int row= Integer.parseInt(parts[0]);
		char col= parts[1].charAt(0);
		int mirDir=0;
		if(parts[1].charAt(1)=='R'){
			mirDir=1;
		}else if(parts[1].charAt(1)=='L') {
			mirDir=2;
		}
		gameBoard.locate(row,col,mirDir);

	}
	
	
	public void addPlayer() {}
	
	
	
	/** Method for sending game board values<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Sends game board values and generates the game board<br>
	 *@param rows, the amount of rows in the board 
	 *@param columns, the amount of columns in the board 
	 *@param mirrors, the amount of mirrors in the board 
	 *
	*/
	public void generateBoard(int rows, int columns, int mirrors) {
		gameBoard=new Board(rows, columns, mirrors);
		gameBoard.generateBoard();
		
	}

	/**
	 *Getters and Setters 
	 */
	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public ScoreList getScoreBoard() {
		return scoreBoard;
	}

	public void setScoreBoard(ScoreList scoreBoard) {
		this.scoreBoard = scoreBoard;
	}


	
	
	
	

}
