/**
 * This class represents the situation in which the key configuration does't exist.
 * @author FatimaHasan id:250895618
 *
 */
public class InexistentKeyException extends Exception {
	/**
	 * This is the constructor that sets up the exception with a message
	 * @param config which is the key configuration that is inexistent.
	 */
	public InexistentKeyException(String message){
		System.out.println(message);
	}
}
