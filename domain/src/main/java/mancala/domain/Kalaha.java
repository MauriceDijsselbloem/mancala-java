package mancala.domain;

public class Kalaha extends Bowl {
	protected Kalaha(int count, Player player, Bowl firstBowl) {
		super(count, player, firstBowl);
		stones = 0;
	}
	
	@Override
	protected void moveStones(int stones) {
		if (owner.getHasTurn()) {
			this.stones++;
			stones--;
		}
		
		if (stones > 0) neighbour.moveStones(stones);
	}
	
	@Override
	protected void giveToKalaha(int stones) {
		this.stones += stones;
	}
	
	protected void giveEverythingToKalaha(int stones) {
		this.stones += stones;
		owner.setIsWinner(this.stones >= 24);
	}
	
	@Override
	protected void startMoveStones() {
		// don´t do this
	}
	
	@Override
	protected Bowl getOpposite() {
		return this;
	}
	
	@Override
	protected boolean checkIfGameHasEnded() {
		neighbour.giveEverythingToKalaha(0);
		owner.setIsWinner(stones >= 24);
		return true;
	}
		
	@Override
	protected boolean startCheckingIfGameHasEnded() {
		if (!owner.getHasTurn()) { // getHasTurn() is nu al True voor de player die de volgende beurt heeft...
			return neighbour.checkIfGameHasEnded();
		} else {
			return neighbour.startCheckingIfGameHasEnded();
		}
	}
}