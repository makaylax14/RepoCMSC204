import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserStudentTest {

	User user;
	
	@BeforeEach
	void setUp() throws Exception {
		user = new User("Alice", "123");
	}

	@AfterEach
	void tearDown() throws Exception {
		user=null;
	}

	@Test
	void testUserSettersAndGetters() {
		assertEquals("Alice", user.getUsername());
		assertEquals("123", user.getPassword());
		user.setUsername("Dad");
		user.setPassword("321");
		assertEquals("Dad", user.getUsername());
		assertEquals("321", user.getPassword());
	}
	
	@Test
	void testPlaylistOperations() {
		Playlist playlist=new Playlist("Test Playlist");
        playlist.addSong(new Song("Song A", "Artist 1"));
        playlist.addSong(new Song("Song B", "Artist 2"));
        user.addPlaylist(playlist);
        assertEquals(1, user.getPlaylistCount());
        GenericLinkedList<Playlist> list = user.getPlaylists();
        assertEquals(1, list.size());
	}

}
