package mancala.domain;

public class Bowl {
	protected int stones;
	protected Bowl neighbour;
	protected Player owner;
	
	protected Bowl() {
		Player player = new Player();
		stones = 4;
		owner = player;
		createNeighbour(1, player, this);
	}
	
	protected Bowl(int count, Player player, Bowl firstBowl) {
		stones = 4;
		if (count == 7) player = player.getOpponent();
		owner = player;
		createNeighbour(count + 1, player, firstBowl);
	}
	
	private void createNeighbour(int count, Player player, Bowl firstBowl) {
		if (count == 6 || count == 13) {
			neighbour = new Kalaha(count, player, firstBowl);
		} else if (count == 14) {
			neighbour = firstBowl;
		} else {
			neighbour = new Bowl(count, player, firstBowl);
		}
	}
	
	protected int getStones() {return stones;}
	
	protected void setStones(int stones) {this.stones = stones;}

	protected Bowl getNeighbour() {return neighbour;}
	
	protected Bowl getNeighbour(int count) {
		if (count > 0) return neighbour.getNeighbour(count - 1);
		else return this;
	}
	
	protected Bowl getOpposite() {
		return neighbour.getOpposite().getNeighbour();
	}

	protected Player getOwner() {return owner;}

	protected void giveToKalaha(int stones) {
		neighbour.giveToKalaha(stones);
	}
	
	protected void giveEverythingToKalaha(int stones) {
		neighbour.giveEverythingToKalaha(stones + this.stones);
		this.stones = 0;
	}
	
	private void startStealing() {
		Bowl opposite = getOpposite();
		int stolenStones = opposite.getStones();
		opposite.setStones(0);
		neighbour.giveToKalaha(stolenStones + this.stones);
		this.stones = 0;
	}
	
	protected boolean startCheckingIfGameHasEnded() {
		return neighbour.startCheckingIfGameHasEnded();
	}
	
	protected void moveStones(int stones) {
		if (stones > 1) {
			neighbour.moveStones(stones - 1);
		} else {
			if (this.stones == 0 && owner.getHasTurn()) { 
				startStealing();
			}
			owner.switchTurn();
		}
		this.stones++;
	}
	
	protected void startMoveStones() {
		if (stones > 0 && owner.getHasTurn()) {
			int oldStones = stones;
			stones = 0;
			neighbour.moveStones(oldStones);
			owner.setGameHasEnded(startCheckingIfGameHasEnded());
		} else {
			/*
			 * Kalaha does not belong to current player or kalaha is empty.
			 */
		}
	}

	protected boolean checkIfGameHasEnded() {
		return stones == 0 && neighbour.checkIfGameHasEnded();
	}
}
