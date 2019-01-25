package ticTacToe;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	private Board myBoard = new Board();
	private final int MAX = 1;
	private final int MIN = -1;
	private int currentTurn;
	private int ERROR = Integer.MIN_VALUE;
	
	public Game() {
		currentTurn = MAX;
	}
	
	public int utility(Board aBoard) { //Utility function for the minimax algorithm
		if(aBoard.isTerminal()) {
			currentTurn = -currentTurn;
			if(aBoard.isWinLost() && aBoard.getCurrentSymbol() == 'X') {
				System.out.println("Utility = 1");
				return 1;
			}else if(aBoard.isWinLost() && aBoard.getCurrentSymbol() == 'O') {
				System.out.println("Utility = -1");
				return -1;
			}else if(!aBoard.isWinLost()) {
				System.out.println("Utility = 0");
				return 0;
			}
		}
		return ERROR;
	}
	
	public int value(Board aBoard) { //Value function for the minimax algorithm
		System.out.println("Calling value");
		if(aBoard.isTerminal()) {
			System.out.println("This is a terminal state");
			return utility(aBoard);
		}else if(currentTurn == MAX) 
			return maxValue(aBoard);
		else return minValue(aBoard);
	}
	
	public int maxValue(Board aBoard) { //maxValue function for the minimax algorithm
		System.out.println("Calling maxValue");
		int m = - (Integer.MAX_VALUE);
		
		ArrayList<Board> successors = getNextMove(aBoard);
		for(int i=0; i<successors.size(); i++) {
			successors.get(i).printBoard();
			currentTurn = -currentTurn;
			System.out.println("successor "+i+" turn "+this.currentTurn);
			int v = value(successors.get(i));
			m = Math.max(m,  v);
		}
		return m;
	}
	
	public int minValue(Board aBoard) { //minValue function for the minimax algorithm
		System.out.println("Calling minValue ");
		int m = Integer.MAX_VALUE;
		
		ArrayList<Board> successors = getNextMove(aBoard);
		for(int i=0; i<successors.size(); i++) {
			successors.get(i).printBoard();
			currentTurn = -currentTurn;
			System.out.println("successor "+i+" turn "+this.currentTurn);
			int v = value(successors.get(i));
			m = Math.min(m, v);
		}
		return m;
	}
	
	public ArrayList<Board> getNextMove(Board aBoard){ //Returns an ArrayList of all possible moves (Board States) the AI can make
		ArrayList successors = new ArrayList();
		
		if(currentTurn == MAX) {
			//Put 'X' in possible empty fields
			for(int i=0; i<aBoard.ROWS; i++) 
				for(int j=0; j<aBoard.COLUMNS; j++) {
					if(aBoard.isEmptyCell(i, j)) {
						Board newBoard = new Board(aBoard);
						newBoard.move(i,j,'X');
						successors.add(newBoard);
					}
				}
			
			return successors;
		}else {
			//Put 'O' in possible empty fields
			for(int i=0; i<aBoard.ROWS; i++) 
				for(int j=0; j<aBoard.COLUMNS; j++) {
					if(aBoard.isEmptyCell(i, j)) {
						Board newBoard = new Board(aBoard);
						newBoard.move(i, j, 'O');
						successors.add(newBoard);
					}
				}
			return successors;
		}
	}
	
	public void setUpTestBoard() { //Puts X's and O's on the board and prints it
		this.myBoard.move(0, 0, 'X');
		this.myBoard.move(0, 1, 'X');
		this.myBoard.move(0, 3, 'O');
		this.myBoard.move(1, 0, 'O');
		this.myBoard.move(3, 1, 'O');
		this.myBoard.move(1, 3, 'O');
		this.myBoard.move(2, 0, 'X');
		this.myBoard.move(3, 2, 'X');
		this.myBoard.move(3, 3, 'X');
		this.myBoard.move(3, 0, 'O');
		this.myBoard.move(3, 2, 'O');
		this.myBoard.move(0, 2, 'X');
		this.myBoard.printBoard();
	}
	
	public void generateSearchSpace() {
		System.out.println("value = "+ value(myBoard)); //Displays all possible board combinations and their utilites
	}
	
	public static void main(String[]args) {
		Game aGame = new Game();
		
		//Set up test board
		aGame.setUpTestBoard();
		aGame.generateSearchSpace();
		
		//Show the empty starting board
		aGame.myBoard.printBoard();
	
		boolean goodIndex = true;
		while(!aGame.myBoard.isTerminal()) { //Continue asking user for next move until the game is over
			
			do {
				//Ask user for the next move
				goodIndex = true;
				System.out.print("Your Move: ");
				
				try {
					//Declare the input scanner and other variables
					Scanner input = new Scanner(System.in);
					int xVal = input.nextInt(); 
					int yVal = input.nextInt();
					
					if(aGame.myBoard.isEmptyCell(xVal, yVal)) {// if the users move is available
						
						aGame.myBoard.move(xVal, yVal, 'X');//make the users move, 
						aGame.myBoard.printBoard();//then show the board
						
						if(aGame.myBoard.isTerminal()) {//If the game is over after the users last move
							
							if(aGame.myBoard.isWinLost()) {//check if the user is the winner,
								
								System.out.println("Congratulation! You Won!");//if so, tell them they won
								
							}else {
								System.out.println("Tie game. Thank you for playing.");//Tell the user the game is a tie
							}
							
						}else {//game is not terminal, its the computers turn 
							aGame.currentTurn = -1;//its the computers turn, the next character place will be an 'O'
							
							//Create an ArrayList of all the possible board states that result from the opponents move
							ArrayList<Board> AIMoves = new ArrayList();
							AIMoves = aGame.getNextMove(aGame.myBoard);
							ArrayList<Board> playerMoves = new ArrayList();
							Game testGame = new Game();
							
							//Find the board state with the AI's winning move, otherwise find the minimum utility state (best move for the 'AI')
							
							int minUtility = 2;//will hold the Utility of the AI's next move
							int minUtilityIndex = 0;//will hold the position of the AI's next move
							int altMinUtility = 2;
							int altUtilIndex = 0;
							int valueHolder;//holds the value of the next possible moves utility, changes value of minUtility if smaller than it
							boolean badMove = false;
							
							for(int i=0;i<AIMoves.size();i++) {//loops through possible moves the AI can make
								valueHolder = aGame.value(AIMoves.get(i));
								if(AIMoves.get(i).isTerminal()) {//if the next move results in the AI winning (or drawing), choose that move and break
									minUtilityIndex = i;
									break;
								}else if(valueHolder < minUtility){//changes value of minUtility and it's index if valueHolder is less than minUtility
									//Creates a list of possible player moves, given the AI's move
									testGame = aGame;
									testGame.myBoard = AIMoves.get(i);
									playerMoves = testGame.getNextMove(testGame.myBoard);
									
									//if the AI move allows a player to win the next turn, make sure the AI doesn't take that move
									for(int j=0;j<playerMoves.size();j++) {
										if(playerMoves.get(j).isTerminal()) {
											badMove= true;
											break;
										}
									}
									
									//creates an alternative minUtility and minUtilityIndex in case the minUtility and minUtilityIndex is never updated
									altMinUtility = valueHolder;
									altUtilIndex = i;
									
									//update the AI's move if its a good one
									if(!badMove) {
										minUtility = valueHolder;
										minUtilityIndex = i;
									}else {
										badMove = false;
									}
								}
							}
							//use the alternative utility and index if it was never updated
							if(minUtility == 2) {
								minUtility = altMinUtility;
								minUtilityIndex = altUtilIndex;
							}
							
							//AI makes their move
							aGame.myBoard = AIMoves.get(minUtilityIndex);
							aGame.myBoard.printBoard();
							
							//check if the game is over after AI makes their move
							if(aGame.myBoard.isTerminal()) {
								
								if(aGame.myBoard.isWinLost()) {
									//If the game results in a winner, tell the user they lost
									System.out.println("You tried your best.  Thank you for playing.");
								}else {
									//Tell the user the game is a tie
									System.out.println("Tie game.  Thank you for playing.");
								}
								
							}else {
								//the game is not over, break the loop to have the user start another turn
								break;
							}
						}
					}else {
						//Tell the user that the position they chose is not available
						System.out.println("Position ("+xVal+","+yVal+") is taken!");
						break;
					}
					
				}catch (InputMismatchException e) {
					//Tell the user to enter a valid index
					System.out.println("Enter a valid index!");
					goodIndex = false;
				}
	
			}while(goodIndex == false);//Continue asking the user for input until a valid index is entered
		}
	}
}
