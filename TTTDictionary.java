import java.util.ArrayList;
import java.util.List;
/**
 * This class implements a dictionary using a hash table with separate chaining.
 * @author FatimaHasan id:250895618
 *
 */
public class TTTDictionary implements TTTDictionaryADT{
	//declaring variables
	private int size;
	private int table_size;
	private List<List<TTTRecord>> dictionary;

/**
 * This is the instructor for the class. It returns an empty TTTDictionary of the specified size.
 * @param size - size of the dictionary
 */
	public TTTDictionary(int size) {
		this.table_size = size;
		this.dictionary = new ArrayList<List<TTTRecord>>(size);
		for (int i = 0; i < table_size; i++) {
			dictionary.add(new ArrayList<TTTRecord>());
		}
		this.size = 0;
	}
	
/**
 * The public int put(TTTRecord record) throws DuplicatedKeyException method inserts the given record in the dictionary.
 * and throws the duplicated key exception if it's already there.This method returns 0 if the dictionary was empty before insertion
 * @param record
 */
	public int put(TTTRecord record) throws DuplicatedKeyException {
		int hash = hashFunction(record.getConfiguration());
		List<TTTRecord> dict = dictionary.get(hash);
		if (!dict.isEmpty()){
		for (TTTRecord records: dict) {
			if (records.getConfiguration().equals(record.getConfiguration())) {
				throw new DuplicatedKeyException("This record already exists in the dictionary");
			}
		}
		}
		dict.add(record);
		size++;
		return 0;
	}
/**
 * The public void remove(String config) throws InexistentKeyException method removes the record with the given configuration from the dictionary , and throws an InexistentKeyException
 * if the configuration is not in the dictionary.
 * @param config
 */
	public void remove(String config) throws InexistentKeyException {
		int hash = hashFunction(config);
		List<TTTRecord> dict = dictionary.get(hash);
		if (!dict.isEmpty()) {
			for (TTTRecord record: dict) {
				if (record.getConfiguration().equals(config)) {
					dict.remove(record);
					size--;
					return;
				}
			}
		}
		throw new InexistentKeyException("The record with this configuration doesn't not exist");
	}

/**
 * The public TTTRecord get(String config) method returns the TTTRecord stored in the dictionary for the given configuration, or null if it's not in the dictionary.
 * @param config - configuration to get the record
 */
	public TTTRecord get(String config) {
		int hash = hashFunction(config);
		List<TTTRecord> list = dictionary.get(hash);
		if (!list.isEmpty()) {
			for (TTTRecord record: list) {
				if (record.getConfiguration().equals(config)) return record;
			}
		}
		return null;
	}

/**
 * The public int numElements() returns the number of TTTRecord objects stored in the dictionary. 
 */
	public int numElements() {
		return size;
	}

/**
 * This is the hashFunction used to implement the dictionary
 * @param config - configuration to find place for the record
 * @return hash the place where the record is to be placed
 */
	private int hashFunction(String config) {
		int hash = 0;
		for (char c: config.toCharArray()) {
			hash = (hash * 47 + (int)c) % table_size; 
		}
		return hash;
	}

}