/**
 * This class implements all the methods by algorithm computerPlay
 * @author FatimaHasan id: 250895618
 *
 */
public class BlockedTicTacToe {
	//declaring variables
	private int board_size;
	private int inline;
	private int max_levels;
	private char[][] gameBoard;
	
/**
 * This the constructor of the class, it has three parameters board_size, inline, and max_levels, and it initializes gameBoard so that every entry stores a space ' '.
 * @param board_size which specifies the size of the board
 * @param inline which is the number of symbols in-line needed to win the game
 * @param max_levels which is the maximum level of the game tree that will be explored by the program
 */
	public BlockedTicTacToe (int board_size, int inline, int max_levels){
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameBoard = new char [board_size][board_size];
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}
	}
/**
 * The public TTTDictionary createDictionary() method returns an empty TTTDictionary of the size preselected
 * @return an empty dictionary with the preselected size
 */
	public TTTDictionary createDictionary(){
		final int DICTIONARY_SIZE = 4997;
		return new TTTDictionary(DICTIONARY_SIZE);
	}
/**
 * The private String GameBoardStringRepresentation() is a helper method that represents the game board as a string and returns it.
 * @return String representation of game board.
 */
	private String GameBoardStringRepresentation() {
		StringBuilder sb = new StringBuilder();
		for (char[] line: gameBoard) {
			for (char c: line) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
/**
 * public int repeatedConfig(TTTDictionary configurations): This method uses the helpermethod GameBoardStringRepresentation that represents the gameBoard as a string; 
 * then it checks whether the string representing the gameBoard is in the configurations dictionary: If it is in the dictionary this method returns its associated score, otherwise it returns the value -1.
 * @param configurations
 * @return the score if the config is there, otherwise -1
 */
	public int repeatedConfig(TTTDictionary configurations){
		TTTRecord record = configurations.get(GameBoardStringRepresentation());
		if (record != null){
			return record.getScore();
		}
		return -1;
	}
/**
 * The public void insertConfig(TTTDictionary configurations, int score, int level) method uses helper method GameBoardStringRepresentation that represents the content of gameBoard as a string  then it inserts this string, score and level in the configurations dictionary.
 * @param configurations - to be used to represent the gameboard as a string
 * @param score - score of the record(gameBoard)
 * @param level - level of the record(gameBoard)
 */
	public void insertConfig(TTTDictionary configurations,int score, int level){
		TTTRecord record = new TTTRecord(GameBoardStringRepresentation(), score, level);
		try{
			configurations.put(record);
			
		} catch (DuplicatedKeyException e){
			System.out.println("DuplicatedKeyException!");
		}
	}
/**
 * The public void storePlay(int row, int col, char symbol) method stores the character symbol in the gameBoard[row][col].
 * @param row row in the game board
 * @param col - column in the game board
 * @param symbol - symbol of the player
 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;			
	}
/**
 *  The public boolean squareIsEmpty (int row, int col): method returns true if gameBoard[row][col] is ’ ’; 
 * otherwise it returns false.
 * @param row - row of game board 
 * @param col - column of game board
 * @return true if gameBoard[row][col] is ’ ’, and false otherwise.
 */
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		}
		return false;
	}
/**
 * private boolean symbolMatch(int x, int y, char symbol) method is a helper method used by the existkAdjacentOccurences helper method to see if the adjacent occurences are by the same player.
 * returns true the symbol matches otherwise false
 */
	private boolean symbolMatch(int x, int y, char symbol) {
		if (x < 0 || x >= board_size || y < 0 || y >= board_size) return false;
		if (gameBoard[x][y] == symbol) return true;
		return false;		
	}
/**
 * private boolean existkAdjacentOccurences(int i, int j, char symbol) is helper method that true if there are k adjacent occurrences of symbol in the same row, column, or diagonal of gameBoard, 
 * where k is the number of required symbols in-line needed to win the game.
 * @param i - row
 * @param j - column
 * @param symbol - player
 * @return true or false as stated above
 */
	private boolean existkAdjacentOccurences(int i, int j, char symbol) {
		boolean row = true, col = true, diagonal1 = true, diagonal2 = true;
		for (int k = 0; k < inline; k++) {
			if (!symbolMatch(i+k, j, symbol)) row = false;
			if (!symbolMatch(i, j+k, symbol)) col = false; 
			if (!symbolMatch(i+k, j+k, symbol)) diagonal1 = false; 
			if (!symbolMatch(i+k, j-k, symbol)) diagonal2 = false;
		}	
		return (row || col || diagonal1 || diagonal2);
	}
/**
 * 	public boolean wins (char symbol) method uses helper method and returns true if there are k adjacent occurrences of symbol in the same row, column, or diagonal of gameBoard
 * @param symbol - player 
 * @return true if there are k adjacent occurrences of symbol in the same row, column, or diagonal of gameBoard
 */
	public boolean wins (char symbol){
		for(int i=0; i < board_size ; i++){
			for(int j = 0; j < board_size ; j++){
				if(gameBoard[i][j] == symbol){
					if(existkAdjacentOccurences(i,j,symbol)){
						return true;
					}
				}
			}
		}
		return false;
	}
/**
 * The private boolean EmptySpacesLeft() method checks if there are empty positions
 * left and returns true if so, and false otherwise
 * @return true if empty spaces left, false otherwise
 */
	private boolean EmptySpacesLeft() {
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (squareIsEmpty(i, j)) return true; 
			}
		}
		return false;
	}
/**
 * The public boolean isDraw() returns true if the gameBoard has no empty positions left and no 
 * player has won
 * @return true if the games a draw and false otherwise
 */
	public boolean isDraw() {
		if (!wins('o') && !wins('x') && !EmptySpacesLeft()) return true;
		return false;
	}
/**
 * The public int evalBoard() method evaluates the game board and returns 1 if the game is a draw, 0 if the human player won, and 3 if the 
 * computer has won, and if the game is still undecided it returns 2. 
 * @return 1,2,3,0 based on who wins, or if no one wins at all.
 */
	public int evalBoard() {
		if (wins('x')) return 0;//if the human player won.
		else if (wins('o')) return 3;//if the computer has won.
		else if (isDraw()) return 1;
		else return 2;
	}

}
