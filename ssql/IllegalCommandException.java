/**
 * A custom exception class to handle invalid ssql commands.
 * 
 * @author
 *
 */
public class IllegalCommandException extends Exception {

	public IllegalCommandException(String message){
		super(message);
	}
}
