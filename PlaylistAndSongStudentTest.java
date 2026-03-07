import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistAndSongStudentTest {
	
	private Playlist playlist;

	@BeforeEach
	void setUp() throws Exception {
		playlist = new Playlist("Test Playlist");
        playlist.addSong(new Song("Song A", "Artist 1"));
        playlist.addSong(new Song("Song B", "Artist 2"));
	}

	@AfterEach
	void tearDown() throws Exception {
		playlist=null;
	}

	@Test
	void testPlaylistAndSetterAndGetters() {
		assertEquals("Test Playlist", playlist.getName());
		GenericLinkedList<Song> test = playlist.getSongs();
		assertEquals("Song A", test.get(0).getTitle());
		assertEquals("Song B", test.get(1).getTitle());
		Song song = playlist.getCurrentSong();
		assertEquals("Song A", song.getTitle());
		assertEquals(2, playlist.getSize());
	}
	
	@Test
	void testAddingAndTraversalOperations() {
		assertEquals(2, playlist.getSize());
		playlist.addSong(new Song("Song C", "Artist 3"));
		assertEquals(3, playlist.getSize());
		Song song1 = playlist.nextSong();
		assertEquals("Song A", song1.getTitle());
		playlist.nextSong();
		Song song2 = playlist.previousSong();
		assertEquals("Song B", song2.getTitle());
		playlist.nextSong();
		playlist.nextSong();
		assertEquals(null, playlist.nextSong());
	}
	
	@Test
	void testIsEmptyAndRemoveSong() {
		assertEquals(false, playlist.isEmpty());
		Song song1 = new Song("Song A", "Artist 1");
		Song song2 = new Song("Song B", "Artist 2");
		playlist.removeSong(song1);
		playlist.removeSong(song2);
		assertEquals(0, playlist.getSize());
		assertEquals(true, playlist.isEmpty());
	}
	
	@Test
	void testSongMethods() {
		Song song = new Song();
		assertEquals(null, song.getTitle());
		assertEquals(null, song.getArtist());
		song.setTitle("Growing Sideways");
		song.setArtist("Noah Kahan");
		assertEquals("Growing Sideways", song.getTitle());
		assertEquals("Noah Kahan", song.getArtist());
		Song song2 = new Song("Yes", "Harry Styles");
		assertEquals("Yes", song2.getTitle());
		assertEquals("Harry Styles", song2.getArtist());
	}

}
