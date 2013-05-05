/**
 * 
 */
package assoziation.exception;

/**
 * ListAddedException
 */
public class ListAddedException extends Exception {

	private static final long serialVersionUID = 7556464538632439432L;

	public ListAddedException() {
		super();
	}

	/**
	 * @param message
	 */
	public ListAddedException(String message) {
		super(message);
	}

	public ListAddedException(ExceptionType type) {
		this(type.getType());
	}

	/**
	 * @param cause
	 */
	public ListAddedException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param type
	 * @param cause
	 */
	public ListAddedException(ExceptionType type, Throwable cause) {
		this(type.getType(), cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ListAddedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ListAddedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * 
	 * ExceptionType
	 * 
	 */
	public enum ExceptionType {

		ELEMENT_NULL("Given Element is NULL"), ELEMENT_EXISTS(
				"Given Element already exists");

		private String type;

		private ExceptionType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

	}

}