/**
 * This class represents a custom exception, InvalidPasswordException.
 * It's thrown when the password is wrong.
 */
public class InvalidPasswordException extends RuntimeException {
	/**
	 * This constructor calls super() with msg.
	 * @param msg
	 */
	public InvalidPasswordException(String msg) {
		super(msg);
	}
	/**
	 * This constructor calls super() with "Invalid password!"
	 */
	public InvalidPasswordException() {
		super("Invalid password!");
	}
}
