package mancala.domain;

public class Player {
	private boolean hasTurn;
	private boolean gameHasEnded;
	private boolean isWinner;
	private Player opponent;

	protected Player() {
		opponent = new Player(this);
		hasTurn = true;
	}
	
	protected Player(Player player) {
		opponent = player;
	}
	
	protected void setGameHasEnded(boolean gameHasEnded) {
		this.gameHasEnded = gameHasEnded;
		opponent.setGameHasEndedLetOpponentKnow(gameHasEnded);
	}
	
	protected void setGameHasEndedLetOpponentKnow(boolean gameHasEnded) {
		this.gameHasEnded = gameHasEnded;
	}
	
	protected boolean getGameHasEnded() {return gameHasEnded;}
	
	protected boolean getIsWinner() {return isWinner;}
	
	protected void setIsWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}
	
	protected boolean getHasTurn() {return hasTurn;}
	
	protected void setHasTurn(boolean hasTurn) {this.hasTurn = hasTurn;}
	
	protected Player getOpponent() {return opponent;}
	
	protected void switchTurn() {
		opponent.setHasTurn(hasTurn);
		hasTurn = !hasTurn;
	}
}