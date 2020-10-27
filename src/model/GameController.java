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
	
	public void shoot() {}
	public void locate() {}
	public void addPlayer() {}
	public void generateBoard() {
		gameBoard=new Board(2, 3, 2);
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
