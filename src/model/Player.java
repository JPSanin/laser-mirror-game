package model;

/**
 * Class that represents the player

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:43 PM

 */
public class Player {
	private String nickname;
	private int score;
	private int rows;
	private int columns;
	private int mirrors;
	private Player father;
	private Player right;
	private Player left;
	
	
	/** 
	 * 
	 *	Constructor method for a player <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a player with nickname and game board characteristics <br>
		
	 * @param nickname, String with the players' nickname
	 * @param rows, int representing the amount of rows
	 * @param columns, int representing the amount of columns
	 * @param mirrors, int representing the amount of mirrors on the board
	 */
	public Player(String nickname, int rows, int columns, int mirrors) {
		super();
		this.nickname = nickname;
		this.score = 0;
		this.rows = rows;
		this.columns = columns;
		this.mirrors = mirrors;
	}
	
	@Override
	public String toString() {
		String info="";
		return info;
	}

	public void calculateScore(int shots, int locates, int errors, int mirrorsFound) {}
	
	
	/**
	 *Getters and Setters 
	 */
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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

	public Player getFather() {
		return father;
	}

	public void setFather(Player father) {
		this.father = father;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}
	
	
	
	
	
	
}
