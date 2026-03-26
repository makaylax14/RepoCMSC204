
public class DictionaryEntryNotFoundException extends RuntimeException{
	public DictionaryEntryNotFoundException(String msg) {
		super(msg);
	}
	public DictionaryEntryNotFoundException() {
		super("Word is not in the dictionary!");
	}

}
