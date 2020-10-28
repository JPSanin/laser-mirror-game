package exceptions;

public class CannotShootException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotShootException(int row, char col) {
		super("The cell: "+row+col+ " is not a border and cannot shoot");
	}


}
