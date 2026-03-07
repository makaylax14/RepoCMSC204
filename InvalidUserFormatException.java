/**
 * This class represents a custom exception, InvalidUserFormatException.
 * It's thrown often when something goes wrong, like the user didn't enter a username.
 */
public class InvalidUserFormatException extends RuntimeException {
	/**
	 * This constructor calls super() with msg.
	 * @param msg
	 */
	public InvalidUserFormatException(String msg) {
		super(msg);
	}
	/**
	 * This constructor calls super() with "Invalid user format!"
	 */
	public InvalidUserFormatException() {
		super("Invalid user format!");
	}
}
