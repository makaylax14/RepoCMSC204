import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpotifyManagerStudentTest {
	
	private SpotifyManager manager;
    private File tempFile;
    private String testData;

	@BeforeEach
	void setUp() throws Exception {
		manager = new SpotifyManager();
        testData = 
            "# USER\n" +
            "username: makayla\n" +
            "password: unicorns\n" +
            "playlist: Favorites\n" +
            "song: Clean - Taylor Swift\n" +
            "song: Hello - Adele\n" +
            "playlist: Workout\n" +
            "song: Stronger - Kanye West\n" +
            "# USER\n" +
            "username: bob\n" +
            "password: 1234\n" +
            "playlist: Chill\n" +
            "song: Yellow - Coldplay\n" +
            "song: Sparks - Coldplay\n";
        tempFile = File.createTempFile("test_users", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(testData.replace("\n", System.lineSeparator()));
        }
	}

	@AfterEach
	void tearDown() throws Exception {
		manager=null;
		tempFile=null;
	}

	@Test
	void testLoadUsersFromFile() throws InvalidUserFormatException, IOException {
		 manager.loadUsersFromFile(tempFile.getAbsolutePath());
		 assertEquals(2, manager.getSize());
		 GenericLinkedList<User> users =  manager.getUsers();
		 User user = users.get(0);
		 assertEquals(2, user.getPlaylistCount());
		 String testData2 = "# USER\nusername: \n";
		 File test = File.createTempFile("test", ".txt");
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(test))){
			 writer.write(testData2.replace("\n", System.lineSeparator()));
		 }
		 assertThrows(InvalidUserFormatException.class,
	                () ->  manager.loadUsersFromFile(test.getAbsolutePath()));
	}
	
	@Test
	void testFindUser() throws InvalidUserFormatException, IOException {
		manager.loadUsersFromFile(tempFile.getAbsolutePath());
		assertThrows(UserNotFoundException.class,
                () ->  manager.findUser("Danny", "123"));
		User user = manager.findUser("makayla", "unicorns");
		assertEquals("makayla", user.getUsername());
		assertEquals("unicorns", user.getPassword());
	}

}
