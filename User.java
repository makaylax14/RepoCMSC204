/**
 * This class represents a Spotify user. It has
 * the attributes username, password, and listOfPls.
 */
public class User {
	/**
	 * The username
	 */
	private String username;
	/**
	 * The password
	 */
	private String password;
	/**
	 * The list of playlists
	 */
	private GenericLinkedList<Playlist> listOfPls;
	
	/**
	 * This constructor assigns values to name and username and instantiates listOfPls.
	 * @param name
	 * @param pw
	 */
	public User(String name, String pw) {
		username=name;
		password=pw;
		listOfPls=new GenericLinkedList<>();
	}
	
	/**
	 * This method adds a playlist.
	 * @param playlist
	 */
	public void addPlaylist(Playlist playlist) {
		listOfPls.addLast(playlist);
	}
	
	/**
	 * This method returns the number of playlists.
	 * @return
	 */
	public int getPlaylistCount() {
		return listOfPls.size();
	}
	
	/**
	 * This method sets the name.
	 * @param name
	 */
	public void setUsername(String name) {
		this.username=name;
	}
	
	/**
	 * This method sets the password.
	 * @param pw
	 */
	public void setPassword(String pw) {
		this.password=pw;
	}
	
	/**
	 * This method gets the password.
	 * @return The password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * This method gets the username.
	 * @return The username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * This method returns the list of playlists.
	 * @return The list of playlists
	 */
	public GenericLinkedList<Playlist> getPlaylists(){
		return listOfPls;
	}
}
