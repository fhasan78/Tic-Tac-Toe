/**
 * This class represents one of the configurations that will be stored in the
 * dictionary along with its integer score and level.
 * @author FatimaHasan id:250895618
 *
 */
public class TTTRecord {
	private String config;
	private int score;
	private int level;
/**
 * This is the constructor for this class, it returns a new TTTRecord with the 
 * specified configuration, score, and level.
 * 
 * @param config which is the key attribute for every TTTRecord object
 * @param score which is the score
 * @param level which is the level
 */
	public TTTRecord(String config, int score, int level){
		this.config = config;
		this.score = score;
		this.level = level;
	}
/**
 * The public String getConfiguration() method returns the configuration stored in the TTTRecord
 * @return the configuration stored in the TTTRecord 
 */
	public String getConfiguration(){
		return this.config;
			
	}
/**
 * The public int getScore() method returns the score in the TTTRecord
 * @return score which is the score stored in TTTRecord
 */
	public int getScore(){
		return this.score;
	}
/**
 * The public int getLevel() method returns the level in the TTTRecord
 * @return level which is the level in the TTTRecord
 */
	public int getLevel(){
		return this.level;
	}
	
}
