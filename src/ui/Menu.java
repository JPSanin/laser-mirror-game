package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.CannotShootException;
import exceptions.CornerException;
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
	private String playerName;
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
		System.out.println("***WELCOME TO THE LAZER-MIRROR GAME***");
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
	<b> post: </b> Prints options and read selection, allows program to function recursively<br>	
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
	 * @throws IOException 
	*/
	private void play() throws IOException {
		generateBoard();
		action();
		
		
	}
	
	private void action() throws IOException {
		String info= br.readLine().toUpperCase();
		if(Character.isDigit(info.charAt(0))) {
			try {
				gameController.shoot(info);
				System.out.println(gameController.getGameBoard().showBoardScreen(playerName));
			} catch (CannotShootException | CornerException e) {
				//e.printStackTrace();
				System.err.println(e);
			}
			action();
		}else if(info.charAt(0)=='L'){
			gameController.locate();
			
		}else if(info.equals("MENU")){
			
		}
		
	}
	
	
	/** Method for reading information to begin playing the game<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Generates the board<br>	
	 * @throws IOException 
	*/
	private void generateBoard() throws IOException {
		System.out.println("Starting Game...");
		System.out.println("Please enter the following information in the specified format to begin");
		System.out.println("Please separate information using commas as shown below");
		System.out.println("nickname, amount of rows, amount of columns, amount of mirrors");
		String info[]= br.readLine().split(",");
		playerName=info[0].trim();
		int rows= Integer.parseInt(info[1].trim());
		int columns= Integer.parseInt(info[2].trim());
		int mirrors= Integer.parseInt(info[3].trim());
		gameController.generateBoard(rows,columns,mirrors);
		System.out.println("Generating Board...");
		System.out.println(gameController.getGameBoard().showBoardScreen(playerName));
		
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
