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
		System.out.println(shotinfo);
		String[] parts=shotinfo.split(",");
		int row= Integer.parseInt(parts[0]);
		
		//gameBoard.shootStarter(10,'a', 2);
	}
	
	
	
	private String stringSplitter(String str, int index) {
		String info=null;
		if(!Character.isDigit(str.charAt(index))){
			System.out.println("YES");
			info=str.substring(0, index) + "," + str.substring(index);
			return info;
		}else {
			index++;
			System.out.println(index);
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
