package assoziation.exception;

/**
 * EndOfListException
 * 
 */
public class EndOfListException extends Exception {

	private static final long serialVersionUID = -593651786072268717L;

	public EndOfListException() {
		super();
	}

	/**
	 * 
	 * @param message
	 */
	public EndOfListException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public EndOfListException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public EndOfListException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public EndOfListException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
