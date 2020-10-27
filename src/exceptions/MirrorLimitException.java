package exceptions;

public class MirrorLimitException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MirrorLimitException(int mirrors) {
		super("The amount of mirrors: "+ mirrors+ "surpasses the board limit");
	}

}
