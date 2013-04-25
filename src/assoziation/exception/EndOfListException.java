package assoziation.exception;

public class EndOfListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -593651786072268717L;

	public EndOfListException() {
	}

	public EndOfListException(String message) {
		super(message);
	}

	public EndOfListException(Throwable cause) {
		super(cause);
	}

	public EndOfListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EndOfListException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
