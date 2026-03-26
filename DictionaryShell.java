import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DictionaryShell {
	public DictionaryShell() {}
	public static void main(String[] args) {
		boolean filenameThere=true;
		String command=null;
		String word=null;
		DictionaryBuilder db=null;
		Scanner sc = new Scanner(System.in);
		if (args.length<=0) {
			filenameThere=false;
		}
		if (filenameThere) {
			try {
				db = new DictionaryBuilder(args[0]);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				sc.close();
				return;
			}
		}
		else {
			db = new DictionaryBuilder(100);
		}
		System.out.println("Welcome to the Dictionary Builder CLI.");
		System.out.println("Available commands: add <word>, delete <word>, search <word>, list, stats, exit");
		System.out.print("> ");
		command=sc.nextLine();
		while (!(command.contains("exit"))) {
			if (command.contains("search")) {
				word=command.substring(7);
				word=word.toLowerCase();
				StringBuilder sb = new StringBuilder();
				for (int count2 = 0; count2 < word.length(); count2++) {
					if (Character.isLetter(word.charAt(count2))) {
						sb.append(word.charAt(count2));
					}
				}
				word=sb.toString();
				int frequency=db.getFrequency(word);
				if (frequency>0) {
					System.out.println(frequency+" instance(s) of \""+word+"\" found.");
				}
				else {
					System.out.println("\""+word+"\" not found.");
				}
				System.out.println("");
			}
			if (command.contains("add")) {
				word=command.substring(4);
				word=word.toLowerCase();
				StringBuilder sb = new StringBuilder();
				for (int count2 = 0; count2 < word.length(); count2++) {
					if (Character.isLetter(word.charAt(count2))) {
						sb.append(word.charAt(count2));
					}
				}
				word=sb.toString();
				db.addWord(word);
				System.out.println("\""+word+"\""+" added.");
				System.out.println("");
			}
			if (command.contains("delete")) {
				word=command.substring(7);
				word=word.toLowerCase();
				StringBuilder sb = new StringBuilder();
				for (int count2 = 0; count2 < word.length(); count2++) {
					if (Character.isLetter(word.charAt(count2))) {
						sb.append(word.charAt(count2));
					}
				}
				word=sb.toString();
				try {
					db.removeWord(word);
				} catch (DictionaryEntryNotFoundException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("\""+word+"\""+" deleted.");
				System.out.println("");
			}
			if (command.contains("list")) {
				List<String> list = db.getAllWords();
				for (String val : list) {
					System.out.println(val);
				}
				System.out.println("");
			}
			if (command.contains("stats")) {
				System.out.println("Total words: "+db.getTotalCount());
				System.out.println("Total unique words: "+db.getUniqueCount());
				System.out.println("Estimated load factor: "+db.getLoadFactor());
				System.out.println("");
			}
			System.out.print("> ");
			command=sc.nextLine();
		}
		System.out.println("Quitting...");
		sc.close();
	}
}
