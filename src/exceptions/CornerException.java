package exceptions;

public class CornerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CornerException(String cell) {
		super("The cell:"+cell+ "is a corner please add direction of shot");
	}

}
