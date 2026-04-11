import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This  class uses MorseCodeTree to convert morse
 * code (code or from a file) to English.
 */
public class MorseCodeConverter {
	/**
	 * Instance of MorseCodeTree
	 */
	private static MorseCodeTree inst=new MorseCodeTree();
	/**
	 * This constructor doesn't do anything.
	 */
	public MorseCodeConverter(){}
	/**
	 * This method takes in morse code in a file and converts it to English.
	 * @param codeFile
	 * @return The English version
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		StringBuilder str = new StringBuilder();
		Scanner sc = new Scanner(codeFile);
		while (sc.hasNext()) {
			str.append(sc.next());
			str.append(" ");
		}
		sc.close();
		String[] words = str.toString().split("/");
		StringBuilder str2 = new StringBuilder();
		for (int count = 0; count < words.length; count++) {
			String[] tokens = words[count].split(" ");
			for (int count3 = 0; count3 < tokens.length; count3++) {
				if (!(tokens[count3]).equals("")) {
					str2.append(inst.fetch(tokens[count3]));
				}
			}
			str2.append(" ");
		}
		str2.delete(str2.length()-1, str2.length());
		return str2.toString();
		
	}
	/**
	 * This method turns morse code into English given a code as a String.
	 * @param code
	 * @return The English version
	 */
	public static String convertToEnglish(String code) {
		String[] words = code.split("/");
		StringBuilder str = new StringBuilder();
		for (int count = 0; count < words.length; count++) {
			String[] tokens = words[count].split(" ");
			for (int count3 = 0; count3 < tokens.length; count3++) {
				if (!(tokens[count3]).equals("")){
					str.append(inst.fetch(tokens[count3]));
				}
			}
			str.append(" ");
		}
		str.delete(str.length()-1, str.length());
		return str.toString();
	}
	/**
	 * This method prints the letters in the tree using LNR traversal.
	 * @return A String with all the letters in LNR traversal order
	 */
	public static String printTree() {
		ArrayList<String> list = inst.toArrayList();
		StringBuilder str = new StringBuilder();
		for (int count = 0; count < list.size(); count++) {
			str.append(list.get(count));
			if (count!=list.size()-1) {
				str.append(" ");
			}
		}
		return str.toString();
	}
}
