package model;


/**
 * Class that represents the score list
 * The list is a binary search tree using players and the 
 * sorting criteria is their score.

 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:48 PM

 */
public class ScoreList {
	private Player root;
	
	
	/** 
	 * 
	 *	Constructor method for the score list <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates an empty score list<br>
		
	 */
	public ScoreList() {
		root=null;
	}
	
	public void addPlayer() {}
	
	public String printScores() {
		String info="";
		return info;
	}

	
	/**
	 *Getters and Setters 
	 */
	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}
	

}
