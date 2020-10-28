package exceptions;

public class CornerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CornerException(int row, char col) {
		super("The cell: "+row+col+ " is a corner please add direction of shot");
	}

}
