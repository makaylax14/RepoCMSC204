/**
 * This class represents a custom exception, UserNotFoundException.
 * It's thrown when the desired user cannot be found.
 */
public class UserNotFoundException extends RuntimeException {
	/**
	 * This constructor calls super() with msg.
	 * @param msg
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	/**
	 * This constructor calls super() with "User cannot be found!".
	 */
	public UserNotFoundException() {
		super("User cannot be found!");
	}
}
