package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * 		12	11	10	9	8	7	
 *  13							6
 * 		0	1	2	3	4	5
 */

// 6, 12, 2, 6, 1, 9, 3, 8, 2, 7, 6, 4, 9, 1, 8, 2, 7, 6
// 6, 12, 2, 6, 1, 9, 3, 8, 2, 7, 6, 4, 9, 1, 7, 6, 5, 8, 2, 9
// 6, 12, 2, 6, 1, 9, 3, 8, 2, 7, 6, 4, 9, 1, 7, 6, 5, 8, 2, 12


public class EndGameTest { 
	/**
	 *  player 2 is aan de beurt. Hij heeft nog een steentje in bowl 12
	 *  Als deze zet gedaan wordt, heeft hij de volgende beurt. Daarom 
	 *  maakt het niet uit of player 1 de volgende beurt iets kan of niet,
	 *  het spel gaat door.
	 **/
    @Test
    public void gameShouldNotEnd1() {
    	// set up
    	Bowl bowl = new Bowl();
    	
    	bowl.getOwner().switchTurn();
    	
    	for (int i = 0; i < 6; i++) bowl.getNeighbour(i).setStones(0);
    	bowl.getNeighbour(12).setStones(1);

    	assertFalse(bowl.getOwner().getHasTurn());
    	assertTrue(bowl.getOwner().getOpponent().getHasTurn());
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	
    	bowl.getNeighbour(12).startMoveStones();
    	
	   	// no winner
    	assertFalse(bowl.getOwner().getIsWinner());
    	assertFalse(bowl.getOwner().getOpponent().getIsWinner());
    	
    	// game not end
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	assertFalse(bowl.getOwner().getOpponent().getGameHasEnded());
    }
    
	/**
	 * Player 2 is aan de beurt en heeft nog 1 steentje in bowl 11. Als
	 * hij deze zet doet eindigen er 5 steentjes in bowl 12. De beurt van 
	 * player 2 is ten einde. Player 1 is nu aan zet (maar kan niks 
	 * meer doen). Daaorm is het spel ten einde. Player 2 wint, want
	 * hij heeft 24 steentjes in zijn kalaha terwijl player 0 er 0 heeft.
	 **/
    @Test
    public void gameShouldEnd1() {
    	// set up
    	Bowl bowl = new Bowl();

    	bowl.getOwner().switchTurn();
    	
    	for (int i = 0; i < 6; i++) bowl.getNeighbour(i).setStones(0);
    	
    	assertFalse(bowl.getOwner().getHasTurn());
    	assertTrue(bowl.getOwner().getOpponent().getHasTurn());
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	
    	bowl.getNeighbour(7).startMoveStones();
    	
    	// player 2 wins
    	assertFalse(bowl.getOwner().getIsWinner());
    	assertTrue(bowl.getOwner().getOpponent().getIsWinner());
    	
    	//game ends
    	assertTrue(bowl.getOwner().getGameHasEnded());
    	assertTrue(bowl.getOwner().getOpponent().getGameHasEnded());
    }
	
	/**
	 * Player 2 is aan de beurt en heeft nog 1 steentje in bowl 11. Als
	 * hij deze zet doet eindigt dat steentje in bowl 12. De beurt van 
	 * player 2 is nu ten einde. Player 1 is nu aan zet en kan nog iets 
	 * doen. Daaorm is het spel niet ten einde.
	 **/
    @Test
    public void gameShouldNotEnd2() {
    	// set up
    	Bowl bowl = new Bowl();

    	bowl.getOwner().switchTurn();
    	
    	for (int i = 0; i < 6; i++) bowl.getNeighbour(i).setStones(0);
    	bowl.getNeighbour(3).setStones(1);
    	bowl.getNeighbour(11).setStones(1);
    	
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	assertFalse(bowl.getOwner().getHasTurn());
    	assertTrue(bowl.getOwner().getOpponent().getHasTurn());
    	
    	bowl.getNeighbour(11).startMoveStones();
    	
    	// no winner
    	assertFalse(bowl.getOwner().getIsWinner());
    	assertFalse(bowl.getOwner().getOpponent().getIsWinner());
    	
    	// game not ended
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	assertFalse(bowl.getOwner().getOpponent().getGameHasEnded());
    }
    
	/**
	 * Player 2 is aan de beurt en heeft nog 1 steentje in bowl 12. Als
	 * hij deze zet doet eindigt dit steentje in de kalaha van speler 2 en 
	 * is daarom nog een keer aan de beurt. Hij heeft geen zetten meer over
	 * en daarom is het spel ten einde. (player 1 wint, want hij heeft
	 * 24 steentjes in zijn kalaha).
	 **/
    @Test
    public void gameShouldEnd2() {
    	// set up
    	Bowl bowl = new Bowl();

    	bowl.getOwner().switchTurn();
    	
    	for (int i = 7; i < 12; i++) bowl.getNeighbour(i).setStones(0);
    	bowl.getNeighbour(12).setStones(1);
    	
    	assertFalse(bowl.getOwner().getGameHasEnded());
    	assertFalse(bowl.getOwner().getHasTurn());
    	assertTrue(bowl.getOwner().getOpponent().getHasTurn());
    	
    	bowl.getNeighbour(12).startMoveStones();
    	
    	// no winner
    	assertTrue(bowl.getOwner().getIsWinner());
    	assertFalse(bowl.getOwner().getOpponent().getIsWinner());
    	
    	// game has ended
    	assertTrue(bowl.getOwner().getGameHasEnded());
    	assertTrue(bowl.getOwner().getOpponent().getGameHasEnded());
    }
}