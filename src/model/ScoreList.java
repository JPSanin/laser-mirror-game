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
	private String leaderboard;
	private int rank;
	
	/** 
	 * 
	 *	Constructor method for the score list <br>
		<b> pre: </b> <br>
		<b> post: </b> Creates an empty score list<br>
		
	 */
	public ScoreList() {
		root=null;
		rank=1;
	}
	
	/** Method for adding players<br>
	
	<b> pre:score has had to be already calculated </b> <br>
	<b> post: </b> Adds players to score board<br>
	 *@param nickname, player nickname
	 *@param score, the players score 
	*/
	public void addPlayer(String nickname, int score) {
		Player newPlayer= new Player(nickname, score);
		if(root==null) {
			root=newPlayer;
		}else {
			addPlayer(root,newPlayer);
		}
		System.out.println("Success");
	}
	
	
	/** Recursive Method for adding players<br>
	
	<b> pre:the first current has to be the root </b> <br>
	<b> post: </b> Adds players to score board<br>
	 *@param current, the current player to be added to
	 *@param newPlayer, player to be added
	*/
	private void addPlayer(Player current, Player newPlayer) {
		if(current.getScore()>newPlayer.getScore()) {
			if(current.getLeft()==null) {
				current.setLeft(newPlayer);
				newPlayer.setFather(current);
			}else {
				addPlayer(current.getLeft(),newPlayer);
			}
		}else {
			if(current.getRight()==null) {
				current.setRight(newPlayer);
				newPlayer.setFather(current);
			}else {
				addPlayer(current.getRight(),newPlayer);
			}
		}
	}
	
	/**Method for showing the scores<br>
	
	<b> pre:</b> <br>
	<b> post: </b> Shows the leaderboard<br>
	*@return leaderboard, the String with all the information
	*/
	public String printScores() {	
		leaderboard="";
		rank=1;
		createLeaderBoard(root);
		return leaderboard;
	}

	/**Recursive Method for creating the leaderboard <br>
	
	<b> pre:</b> <br>
	<b> post: </b> Saves the players from greatest to least<br>
	*@param current, the current player in the ranking 
	*/
	private void createLeaderBoard(Player current) {
		if(current!=null) {
			createLeaderBoard(current.getRight());
			leaderboard+=rank+") "+current+"\n";
			rank++;
			createLeaderBoard(current.getLeft());
		}
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
