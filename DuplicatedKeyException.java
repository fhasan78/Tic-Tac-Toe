/**
 * This class represents the situation in which there is Duplicated Key Exception
 * @author FatimaHasan id:250895618
 *
 */
public class DuplicatedKeyException extends Exception {
/**
 * This is the constructor which sets up the exception with a message
 * @param string which is the record with the duplicated key configuration thats needs to change
 */
public DuplicatedKeyException(String message) {
	super(message);
}
}
