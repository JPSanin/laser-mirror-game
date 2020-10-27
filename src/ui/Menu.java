package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.GameController;

/**
 * Class that represents the game menu, here 
 * the user will give information to be sent to
 * the game controller
 * 
 * @author: Juan P. Sanin

 * @version: 1.1 26/10/2020 

 */
public class Menu {
	private GameController gameController;
	private BufferedReader br;
	
	/** 
	 * 
	 *	Constructor method for the Menu<br>
		<b> pre: </b> <br>
		<b> post: </b> Creates a menu and initializes attributes<br>
		
	 */
	public Menu() {
		gameController= new GameController();
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	
	/** Method for showing welcome message<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Prints welcome message<br>	
	*/
	private void welcome() {
		System.out.println("**************************************");
		System.out.println("***WELCOME TO THE LASER-MIRROR GAME***");
		System.out.println("**************************************");
	}
	
	/** Method for showing main menu<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Prints options<br>	
	*/
	private void displayMainMenu(){
		System.out.println("Please select an option");
		System.out.println("1) Play");
		System.out.println("2) View Scores");
		System.out.println("3) Exit");
	}
	
	
	/** Method for interacting with main menu<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Prints options and read selection, allows program to function<br>	
	@throws IOException
	*/
	private void mainMenu() throws IOException  {
		String option;
		displayMainMenu();
		option=br.readLine();
		switch(option) {
		case "1":
			play();
			mainMenu();
			break;
		case "2":
			viewScores();
			mainMenu();
			break;
		case "3":
			System.out.println("Thank you for playing, see you next time :)");
			break;
		default:
			System.out.println("Please enter a valid option");
			mainMenu();
			break;
		
		}
		
	}
	
	/** Method for starting to play the game<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Starts the game and allows the player to play<br>	
	*/
	private void play() {
		System.out.println("Start Game");
	}
	
	/** Method for showing the scores  <br>
	
	<b> pre: </b> <br>
	<b> post: </b> Shows the scores of the players that have played<br>	
	*/
	private void viewScores() {
		System.out.println("Show Scores");
	}
	
	/** Method for initiating program<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Starts the program<br>	
	*/
	public void start() {
		welcome();
		try {
			mainMenu();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Please enter valid commands");
		}
		
		
	}
	
	
	/**
	 *Getters and Setters 
	 */
	public GameController getGameController() {
		return gameController;
	}

	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	
	
	
	
	
	

}
