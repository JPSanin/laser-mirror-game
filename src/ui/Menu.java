package ui;

import model.GameController;

/**
 * Class that represents the game menu, here 
 * the user will give information to be sent to
 * the game controller
 * 
 * @author: Juan P. Sanin

 * @version: 1.0 26/10/2020 6:59 PM

 */
public class Menu {
	private GameController gameController;

	public Menu() {
		gameController= new GameController();
	}
	
	public void mainMenu() {}
	public void play() {}
	public void viewScores() {}
	public void start() {
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
