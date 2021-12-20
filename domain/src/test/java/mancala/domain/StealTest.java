package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StealTest { 
    @Test
    public void stealingStonesWorksWhenBowlIsOfPlayerAtTurn() {
    	// set up
    	Bowl bowl = new Bowl();
    	
    	bowl.getNeighbour(2).setStones(1);
    	bowl.getNeighbour(3).setStones(0);
    	
    	bowl.getNeighbour(2).startMoveStones();
    	
    	assertEquals(0, bowl.getNeighbour(2).getStones());
    	assertEquals(0, bowl.getNeighbour(3).getStones());
    	assertEquals(5, bowl.getNeighbour(6).getStones()); // kalaha
    	assertEquals(0, bowl.getNeighbour(3).getOpposite().getStones()); // van gestolen
    }
    
    @Test
    public void stealingStonesDoesNotWorkWhenBowlIsOfOpponent() {
    	// set up
    	Bowl bowl = new Bowl();
    	
    	bowl.getOwner().switchTurn();
    	
    	bowl.getNeighbour(2).setStones(0);
    	
    	bowl.getNeighbour(12).startMoveStones();
    	
    	assertEquals(4, bowl.getNeighbour(10).getStones()); // niet gestolen
    	assertEquals(0, bowl.getNeighbour(12).getStones());
    	assertEquals(1, bowl.getNeighbour(13).getStones()); // kalaha
    	assertEquals(5, bowl.getNeighbour(0).getStones());
    	assertEquals(5, bowl.getNeighbour(1).getStones());
    	assertEquals(1, bowl.getNeighbour(2).getStones()); // niet gestolen
    }
}