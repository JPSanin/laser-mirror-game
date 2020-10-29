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
		System.out.println("3) How to play");
		System.out.println("4) Exit");
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
			showInstructions();
			mainMenu();
			break;
		case "4":
			System.out.println("Thank you for playing, see you next time :)");
			gameController.exportData();
			break;
		default:
			System.out.println("Please enter a valid option");
			mainMenu();
			break;
		
		}
		
	}
	/** Method for displaying the instructions<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Shows the instructions<br>	
	 * 
	*/
	private void showInstructions() {
		System.out.println("Instructions");
		System.out.println("Your objective is to find the mirrors hidden inside the game board,\n"
				+ "to do so you can shoot a laser from one cell and see where it will come out. \n" + 
				"\n" + 
				"To shoot a laser enter the command (ROW+COLUMN) of the cell you would like to shoot from,\n"
				+ "if the cell is a corner you must add the direction of the shot (ROW+COLUMN+DIRECTION) Rows are numbers,\n"
				+ "columns are letters and the direction is a letter H for horizontal or V for vertical.\n" + 
				"\r\n" + 
				"The starting cell will be marked with an S, and the ending cell will be marked with an E,\n"
				+ "if the cell is both start and end it will be displayed with an M.\r\n" + 
				"\r\n" + 
				"Examples: \r\n" + 
				"\r\n" + 
				"[ / ][  ][  ]\r\n" + 
				"[S][  ][E]\r\n" + 
				"[  ][  ][   ]\r\n" + 
				"\r\n" + 
				"2A the shot will be shot from the cell 2A and will end at cell 2C\r\n" + 
				"1AH the shot will be marked with an M because the cell is the start and end.\r\n" + 
				"\r\n" + 
				"If you think you know where a mirror might be located you can type the command (L+ROW+COLUMN+MIRRORDIRECTION)\n"
				+ "L is for locating, row is a number, column is a letter, and Mirror direction is either L for left-facing mirror\n"
				+ "or R for a right-facing mirror. \r\n" + 
				"\r\n" + 
				"Example:\r\n" + 
				"\r\n" + 
				"[ / ][  ][  ]\r\n" + 
				"[  ][  ][   ]\r\n" + 
				"[  ][  ][   ]\r\n" + 
				"\r\n" + 
				"L1AR will be a correct location and the gameboard will display it for the rest of the game\r\n" + 
				"L2AL will be an incorrect location and the game board will show an X in that cell\r\n" + 
				"L1AL will be a semi correct selection and the game board will display an asterisk (*)\r\n" + 
				"\r\n" + 
				"To win you must find all of the mirrors that are hidden.\r\n" + 
				"\r\n" + 
				"Scoring:\r\n" + 
				"Rows + Columns = starter bonus (Ex. 3x3 is a 6 point bonus)\r\n" + 
				"Each mirror takes away one point\r\n" + 
				"Each shot takes away 1 point\r\n" + 
				"Each error takes away 3 points\r\n" + 
				"Each location attempt takes away one point\r\n" + 
				"Each mirror found gives 10 points.\r\n" + 
				"\r\n" + 
				"Good Luck and Have Fun.\r\n" + 
				"");
		
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
		}
		if(info.charAt(0)=='L'){
			gameController.locate(info);
			System.out.println(gameController.getGameBoard().showBoardScreen(playerName));
			if(gameController.getGameBoard().checkWin()) {
				System.out.println("************WINNER************");
				System.out.println("YOU HAVE FOUND ALL THE MIRRORS");
				gameController.getGameBoard().calculateScore();
				System.out.println(gameController.getGameBoard().printSummary(playerName));
				gameController.addPlayer(playerName, gameController.getGameBoard().getScore());
			}else {
				action();
			}
			
		}else if(info.equals("HACKS")){
			System.out.println(gameController.getGameBoard().showBoardHacked(playerName));
			action();
		}else if(info.equals("MENU")){
			gameController.getGameBoard().calculateScore();
			System.out.println(gameController.getGameBoard().printSummary(playerName));
			gameController.addPlayer(playerName, gameController.getGameBoard().getScore());
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
		System.out.println("************LEADERBOARDS************");
		System.out.println("RANK) Nickname: Score");
		System.out.println(gameController.getScoreBoard().printScores());
	}
	
	/** Method for initiating program<br>
	
	<b> pre: </b> <br>
	<b> post: </b> Starts the program<br>	
	*/
	public void start() {
		try {
			gameController.importData();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
