package model;

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
	
	public void shoot(String shot) {
		String shotinfo = stringSplitter(shot,0);
		String[] parts=shotinfo.split(",");
		int row= Integer.parseInt(parts[0]);
		char col= parts[1].charAt(0);
		int dir=checkCorner(row,col, parts);
		if(dir==0) {
			dir=findDir(row,col);
		}
		if(dir==0) {
			System.out.println("Cannot shoot");
		}
		
		System.out.println("Row: "+row+", Col: "+col+", Dir : "+dir);
	}
	
	
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

	private int checkCorner(int row, char col, String[] parts) {
		int dir=0;
		if(row==1 && col==65) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=3;
				}else if(parts[1].charAt(1)=='V') {
					dir=2;
				}
			}else if (parts[1].length()==1) {
				System.out.println("CORNER EXCEPTION");
			}
			
		}else if(row==1 && col== (gameBoard.getColumns()+64)) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=4;
				}else if(parts[1].charAt(1)=='V') {
					dir=2;
				}
			}else if (parts[1].length()==1) {
				System.out.println("CORNER EXCEPTION");
			}
		}else if(row==gameBoard.getRows() && col==65) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=3;
				}else if(parts[1].charAt(1)=='V') {
					dir=1;
				}
			}else if (parts[1].length()==1) {
				System.out.println("CORNER EXCEPTION");
			}
		}else if(row==gameBoard.getRows() && col==(gameBoard.getColumns()+64)) {
			if(parts[1].length()==2) {
				if(parts[1].charAt(1)=='H') {
					dir=4;
				}else if(parts[1].charAt(1)=='V') {
					dir=1;
				}
			}else if (parts[1].length()==1) {
				System.out.println("CORNER EXCEPTION");
			}
		}
		return dir;
	}
	
	
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
	public void locate() {}
	public void addPlayer() {}
	
	
	
	
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
