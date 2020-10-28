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
	
	public void addPlayer(String nickname, int score) {
		Player newPlayer= new Player(nickname, score);
		if(root==null) {
			root=newPlayer;
		}else {
			addEmployee(root,newPlayer);
		}
		System.out.println("Success");
	}
	
	private void addEmployee(Player current, Player newPlayer) {
		if(current.getScore()>newPlayer.getScore()) {
			if(current.getLeft()==null) {
				current.setLeft(newPlayer);
				newPlayer.setFather(current);
			}else {
				addEmployee(current.getLeft(),newPlayer);
			}
		}else {
			if(current.getRight()==null) {
				current.setRight(newPlayer);
				newPlayer.setFather(current);
			}else {
				addEmployee(current.getRight(),newPlayer);
			}
		}
	}
	

	public String printScores() {	
		leaderboard="";
		rank=1;
		createLeaderBoard(root);
		return leaderboard;
	}

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
