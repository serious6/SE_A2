/**
 * 
 */
package assoziation.exception;

/**
 * ElementNotFoundException
 */
public class ElementNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176392961064042195L;

	/**
	 * 
	 */
	public ElementNotFoundException() {
		super();
	}

	/**
	 * @param message
	 */
	public ElementNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ElementNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ElementNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ElementNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}