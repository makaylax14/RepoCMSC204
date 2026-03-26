import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class DictionaryBuilder {
	private static final double LOAD_FACTOR=0.6;
	private GenericLinkedList<Word>[] arr;
	private int uniqueCount;
	private int totalCount;
	private int size;
	public DictionaryBuilder(int estimatedEntries) {
		boolean prime=false;
		boolean rightForm=false;
		totalCount=0;
		uniqueCount=0;
		// The unique words is provided via estimatedEntries.
		double temp=estimatedEntries/LOAD_FACTOR;
		// The final table size I selected is the smallest 4k+3 prime number that is either greater than or equal to temp, which is the number of words divided by the load factor.
		size=(int)temp;
		while (!prime || !rightForm) {
			if (isPrime(size)) {
				prime=true;
				if (size%4==3){
					rightForm=true;
				} else {
					rightForm=false;
				}
			} else {
				prime=false;
			}
			if (prime&&rightForm) {
				break;
			}
			size++;
		}
		arr= new GenericLinkedList[size];
		for (int count = 0; count < size; count++) {
			arr[count]=new GenericLinkedList<Word>();
		}
	}
	public DictionaryBuilder(String filename) throws FileNotFoundException {
		boolean prime=false;
		boolean rightForm=false;
		totalCount=0;
		uniqueCount=0;
		File file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		int wordCount=0;
		// I did research and used file.length() which returns the file size in bytes, and I used this as my estimate.
		long fileSize=file.length();
		//  To calculate the amount of unique words, I did the file size in bytes divided by 100.
		wordCount=(int)fileSize/100;
		if (wordCount==0) {
			wordCount=7;
		}
		double temp=wordCount/LOAD_FACTOR;
		//	I set the table size to be the file size divided by 100 and then divided by the load factor. Then, I find the smallest 4k+3 prime that is greater than or equal to the size I found. This value is the final table size I selected.
		size=(int)temp;
		while (!prime || !rightForm) {
			if (isPrime(size)) {
				prime=true;
				if (size%4==3){
					rightForm=true;
				} else {
					rightForm=false;
				}
			} else {
				prime=false;
			}
			if (prime&&rightForm) {
				break;
			}
			size++;
		}
		arr= new GenericLinkedList[size];
		for (int count = 0; count < size; count++) {
			arr[count]=new GenericLinkedList<Word>();
		}
		Scanner sc = new Scanner(file);
		String[] tokens;
		while (sc.hasNextLine()) {
			tokens=sc.nextLine().split(" ");
			for (int count = 0; count < tokens.length; count++) {
				tokens[count]=tokens[count].toLowerCase();
				StringBuilder sb = new StringBuilder();
				for (int count2 = 0; count2 < tokens[count].length(); count2++) {
					if (Character.isLetter(tokens[count].charAt(count2))) {
						sb.append(tokens[count].charAt(count2));
					}
				}
				tokens[count]=sb.toString();
			}
			for (int count = 0; count < tokens.length; count++) {
				addWord(tokens[count]);
			}
		}
		sc.close();
	}
	public void addWord(String word) {
		word=word.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int count2 = 0; count2 < word.length(); count2++) {
			if (Character.isLetter(word.charAt(count2))) {
				sb.append(word.charAt(count2));
			}
		}
		word=sb.toString();
		int index=Math.abs(word.hashCode())%size;
		GenericLinkedList<Word> list = arr[index];
		boolean present=false;
		for (Word entry : list) {
			if (entry.getName().equals(word)) {
				present=true;
			}
		}
		if (present) {
			ListIterator<Word> iterator = list.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getName().equals(word)) {
					Word inst = iterator.previous();
					inst.setCount(inst.getCount()+1);
					break;
				}
			}
			totalCount++;
		}
		else {
			list.addFirst(new Word(word, 1));
			uniqueCount++;
			totalCount++;
		}
	}
	public List<String> getAllWords(){
		if (getTotalCount()==0) {
			System.out.println("Dictionary is empty");
			List<String> emptyList = new ArrayList<>();
			return emptyList;
		}
		List<String> list = new ArrayList<>();
		for (int count = 0; count < arr.length; count++) {
			GenericLinkedList<Word> wordList = arr[count];
			for (Word entry : wordList) {
				list.add(entry.getName());
			}
		}
		Collections.sort(list);
		return list;
	}
	public int getFrequency(String word) {
		word=word.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int count2 = 0; count2 < word.length(); count2++) {
			if (Character.isLetter(word.charAt(count2))) {
				sb.append(word.charAt(count2));
			}
		}
		word=sb.toString();
		int index=Math.abs(word.hashCode())%size;
		GenericLinkedList<Word> list = arr[index];
		int frequency=0;
		for (Word entry : list) {
			if (entry.getName().equals(word)) {
				frequency=entry.getCount();
			}
		}
		return frequency;
	}
	public void removeWord(String word) throws DictionaryEntryNotFoundException{
		word=word.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int count2 = 0; count2 < word.length(); count2++) {
			if (Character.isLetter(word.charAt(count2))) {
				sb.append(word.charAt(count2));
			}
		}
		word=sb.toString();
		int index = Math.abs(word.hashCode())%size;
		GenericLinkedList<Word> list = arr[index];
		for (Word entry : list) {
			if (entry.getName().equals(word)) {
				list.remove(entry);
				totalCount=totalCount-entry.getCount();
				uniqueCount--;
				return;
			}
		}
		throw new DictionaryEntryNotFoundException("Word is not in dictionary!");
	}
	public int getUniqueCount() {
		return uniqueCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public double getLoadFactor() {
		return LOAD_FACTOR;
	}
	public static boolean isPrime(int n) {
		// Corner case
		if (n<=1) {
			return false;
		}
		// For n=2 or n=3 it will check
		if (n==2 || n==3) {
			return true;
		}
		// For multiple of 2 or 3 This will check
		if (n%2==0 || n%3==0) {
			return false;
		}
		// Check for remaining possible factors
		for (int i = 5; i <= Math.sqrt(n); i=i+6) {
			if (n%i==0 || n%(i+2)==0) {
				return false;
			}
		}
		return true;
	}
}
