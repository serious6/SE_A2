/**
 * 
 */
package assoziation.exception;

/**
 * 
 */
public class ListAddedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7556464538632439432L;

	/**
	 * 
	 */
	public ListAddedException() {
	}

	/**
	 * @param arg0
	 */
	public ListAddedException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ListAddedException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ListAddedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ListAddedException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
