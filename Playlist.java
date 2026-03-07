import java.util.ListIterator;
/**
 * This class represents a user's playlist. It
 * has attributes that represent the name, the list of songs, an iterator, and the current song.
 */
public class Playlist {
	/**
	 * The playlist name
	 */
	private String plName;
	/**
	 * The songs
	 */
	private GenericLinkedList<Song> pl;
	/**
	 * The iterator to move throughout the playlist
	 */
	private ListIterator<Song> iterator;
	/**
	 * The current song
	 */
	private Song currentSong=null;
	
	/**
	 * This constructor assigns a name to the playlist and initializes other fields.
	 * @param name
	 */
	public Playlist(String name) {
		plName=name;
		pl=new GenericLinkedList<>();
		iterator=pl.iterator();
	}
	
	/**
	 * This method returns the name.
	 * @return The name
	 */
	public String getName() {
		return plName;
	}
	
	/**
	 * This method adds a song to the playlist.
	 * @param song
	 * @return True if the song was added
	 */
	public boolean addSong(Song song) {
		pl.addLast(song);
		if (pl.size()==1) {
			currentSong=song;
			iterator=pl.iterator();
		}
		return true;
	}
	
	/**
	 * This method returns the current song.
	 * @return The current song
	 */
	public Song getCurrentSong() {
		return currentSong;
	}
	
	/**
	 * This method returns the size.
	 * @return The size of the playlist
	 */
	public int getSize() {
		return pl.size();
	}
	
	/**
	 * This method makes a shallow copy of the linked list and returns the copy.
	 * @return A shallow copy of the linked list
	 */
	public GenericLinkedList<Song> getSongs(){
		GenericLinkedList<Song> copy = new GenericLinkedList<>();
		int index = 0;
		while (index<getSize()) {
			copy.addLast(pl.get(index));
			index++;
		}
		return copy;
	}
	
	/**
	 * This method checks if the playlist is empty.
	 * @return True if the playlist is empty
	 */
	public boolean isEmpty() {
		return getSize()==0;
	}
	
	/**
	 * This method moves onto the next song.
	 * @return Current song or null
	 */
	public Song nextSong() {
		if (iterator.hasNext()) {
			currentSong= iterator.next();
			return currentSong;
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method goes back one song.
	 * @return Current song or null
	 */
	public Song previousSong() {
		if (iterator.hasPrevious()) {
			currentSong=iterator.previous();
			return currentSong;
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method removes a song from the playlist.
	 * @param song
	 * @return True if the song was removed
	 */
	public boolean removeSong(Song song) {
		return pl.remove(song);
	}
}
