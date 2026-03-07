import java.util.Objects;
/**
 * This class represents a song. It has the attributes
 * title and artist.
 */
public class Song {
	/**
	 * The title
	 */
	private String title;
	/**
	 * The artist
	 */
	private String artist;
	
	/**
	 * This constructor assigns null to the attributes.
	 */
	public Song() {
		title=null;
		artist=null;
	}
	
	/**
	 * This constructor assigns values to title and artist.
	 * @param title
	 * @param artist
	 */
	public Song(String title, String artist) {
		this.title=title;
		this.artist=artist;
	}
	
	/**
	 * This method returns the title.
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * This method returns the artist.
	 * @return The artist
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * This method sets the title.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title=title;
	}
	
	/**
	 * This method sets the artist.
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist=artist;
	}

	/**
	 * This method checks if two songs are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(artist, other.artist) && Objects.equals(title, other.title);
	}
	
	/**
	 * This method displays the title and artist.
	 */
	@Override
	public String toString() {
		return title+" by "+artist;
	}
	
}
