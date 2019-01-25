package ticTacToe;

public class Board {
	
	public final int COLUMNS = 4, ROWS = 4;
	private char board[][] = new char[ROWS][COLUMNS];
	private char currentSymbol;
	private boolean winLost = true;
	
	public Board() {//Board constructor
		for(int i=0; i<ROWS; i++) {
			for(int j=0; j<COLUMNS; j++) {
				board[i][j] = ' ';
			}
		}
		currentSymbol = ' ';
	}
	
	public Board(Board aNewBoard) {//Board constructor, given a board as input
		for(int i=0; i<ROWS; i++) {
			for (int j=0; j<COLUMNS; j++) {
				this.board[i][j] = aNewBoard.board[i][j];
			}
		}
		this.currentSymbol = aNewBoard.currentSymbol;
	}
	
	public boolean isTerminal(){//check if the game has been won or a draw occurs
		
		//check rows for a winner
		if(board[0][0] != ' ' && board[0][0] == board[0][1] &&
				board[0][0] == board[0][2] && board[0][0] == board[0][3]) {
			currentSymbol = board[0][0];
			return true;
		}else if(board[1][0] != ' ' && board[1][0] == board[1][1] &&
				board[1][0] == board[1][2] && board[1][0] == board[1][3]) {
			currentSymbol = board[1][0];
			return true;
		}else if(board[2][0] != ' ' && board[2][0] == board[2][1] &&
				board[2][0] == board[2][2] && board[2][0] == board[2][3]) {
			currentSymbol = board[2][0];
			return true;
		}else if(board[3][0] != ' ' && board[3][0] == board[3][1] &&
				board[3][0] == board[3][2] && board[3][0] == board[3][3]) {
			currentSymbol = board[3][0];
			return true;
		}else
		
		//check columns for a winner
		if(board[0][0] != ' ' && board[0][0] == board[1][0] &&
				board[0][0] == board[2][0] && board[0][0] == board[3][0]) {
			currentSymbol = board[0][0];
			return true;
		}else if(board[0][1] != ' ' && board[0][1] == board[1][1] &&
				board[0][1] == board[2][1] && board[0][1] == board[3][1]) {
			currentSymbol = board[0][1];
			return true;
		}else if(board[0][2] != ' ' && board[0][2] == board[1][2] &&
				board[0][2] == board[2][2] && board[0][2] == board[3][2]) {
			currentSymbol = board[0][2];
			return true;
		}else if(board[0][3] != ' ' && board[0][3] == board[1][3] &&
				board[0][3] == board[2][3] && board[0][3] == board[3][3]) {
			currentSymbol = board[0][3];
			return true;
		}else
		
		//check diagonals for a winner
		if(board[0][0] != ' ' && board[0][0] == board[1][1] &&
				board[0][0] == board[2][2] && board[0][0] == board[3][3]) {
			currentSymbol = board[0][0];
			return true;
		}else if(board[0][3] != ' ' && board[0][3] == board[1][2] &&
				board[0][3] == board[2][1] && board[0][3] == board[3][0]) {
			currentSymbol = board[0][3];
			return true;
		}
		
		//Check for a draw
		else {
			boolean isDraw = true;
			for(int i=0; i<ROWS; i++) {
				for(int j=0; j<COLUMNS; j++) {
					if(board[i][j] == ' ') {
						isDraw = false;
					}
				}
			}
			
			if(isDraw) {
				winLost = false;
				return true;
			}else {
				return false;
			}
		}
	}
	
	public boolean isWinLost() {//tells whether the game was won or lost
		return winLost;
	}
	
	public void setWinLost(boolean winLost) {//updates the value of winLost
		this.winLost = winLost;
	}
	
	public char getCurrentSymbol() {//retrieves what the current symbol is
		return currentSymbol;
	}
	
	public void setCurrentSymbol(char currentSymbol) {//updates the current symbol
		this.currentSymbol = currentSymbol;
	}
	
	public boolean isEmptyCell(int i, int j) {//checks if a cell is empty or not
		if(this.board[i][j] == ' ') {
			return true;
		}else {
			return false;
		}
	}
	
	public void move(int i, int j, char symbol) {
		this.board[i][j] = symbol;
	}
	
	public void printBoard() {//displays the state of the board
		System.out.println("-----------------------------------------");
		for(int i=0; i<ROWS; i++) {
			System.out.print("|");
			for(int j=0; j<COLUMNS; j++) {
				System.out.print("    "+this.board[i][j] +"    |");
			}
			System.out.println();
			System.out.println("-----------------------------------------");
		}
	}
	
}
