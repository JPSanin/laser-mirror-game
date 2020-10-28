package model;

/**
 * Class that represents the player

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:43 PM

 */
public class Player {
	private String nickname;
	private int score;
	private Player father;
	private Player right;
	private Player left;
	
	
	/** 
	 * 
	 *	Constructor method for a player <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a player with nickname and game board characteristics <br>
		
	 * @param nickname, String with the players' nickname
	 * @param score, int representing the players score
	 */
	public Player(String nickname, int score) {
		this.nickname = nickname;
		this.score = score;
	}
	
	@Override
	public String toString() {
		String info="";
		info+=nickname+": "+score;
		return info;
	}
	
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
