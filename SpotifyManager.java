import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Scanner;
/**
 * This method represents Spotify. It manages a
 * list of users.
 */
public class SpotifyManager {
	/**
	 * A list of users
	 */
	private GenericLinkedList<User> users;
	
	/**
	 * This constructor initializes users.
	 */
	public SpotifyManager() {
		users=new GenericLinkedList<>();
	}
	
	/**
	 * This method returns users.
	 * @return users
	 */
	public GenericLinkedList<User> getUsers(){
		return users;
	}
	
	/**
	 * This method checks if a user is in the list and returns it if so.
	 * @param username
	 * @param password
	 * @return The user
	 * @throws UserNotFoundException
	 * @throws InvalidPasswordException
	 */
	public User findUser(String username, String password) throws UserNotFoundException, InvalidPasswordException{
		ListIterator<User> iter = users.iterator();
		while (iter.hasNext()) {
			String name, pw;
			User userFromIter = iter.next();
			name=userFromIter.getUsername();
			pw=userFromIter.getPassword();
			if (name.equals(username)) {
				if (password.equals(pw)) {
					return userFromIter;
				} else {
					throw new InvalidPasswordException();
				}
			}
		}
		throw new UserNotFoundException();
	}
	
	/**
	 * This method loads data from a file into the linked list.
	 * @param filename
	 * @throws IOException
	 * @throws InvalidUserFormatException
	 */
	public void loadUsersFromFile(String filename) throws IOException, InvalidUserFormatException{
		File file = new File(filename);
		if (!file.exists()) {
			throw new IOException();
		}
		Scanner sc= new Scanner(file);
		User newUser = null;
		String username=null;
		String password=null;
		Playlist playlist=null;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.contains("# USER")) {
				if (newUser!=null) {
					users.addLast(newUser);
				}
			}
			if (line.contains("username")) {
				username=line.substring(10);
				if (username.isBlank()) {
					throw new InvalidUserFormatException();
				}
			}
			if (line.contains("password")) {
				password=line.substring(10);
				newUser=new User(username, password);
			}
			if (line.contains("playlist")) {
				if (newUser==null) {
					throw new InvalidUserFormatException();
				}
				playlist = new Playlist(line.substring(10));
				newUser.addPlaylist(playlist);
			}
			if (line.contains("song")) {
				if (playlist==null) {
					throw new InvalidUserFormatException();
				}
				String song = line.substring(6);
				String[] parts = song.split(" - ");
				if (parts.length==1) {
					throw new InvalidUserFormatException();
				}
				Song newSong = new Song(parts[0], parts[1]);
				playlist.addSong(newSong);
			}
		}
		users.addLast(newUser);
		sc.close();
	}
	
	/**
	 * This method returns the size.
	 * @return The size of users
	 */
	public int getSize() {
		return users.size();
	}
	
	/**
	 * This method returns users.
	 * @return users
	 */
	public GenericLinkedList<User> returnUsers(){
		return users;
	}
}
