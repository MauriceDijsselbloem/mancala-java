package mancala.domain;

// Your test class should be in the same 
// package as the class you're testing.
// Usually the test directory mirrors the
// main directory 1:1. So for each class in src/main,
// there is a class in src/test.

// Import our test dependencies. We import the Test-attribute
// and a set of assertions.
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {
    @Test 
    public void aNormalBowlStartsWith4Stones() {
        Bowl bowl = new Bowl();
        int stones = bowl.getStones();
        assertEquals(4, stones);
    }
    
    @Test
    public void thereAre12BowlsAnd2Kalahas() {
    	Bowl bowl = new Bowl();
    	   	
    	for (int i = 0; i < 14; i++) {
    		if (i == 6 || i == 13) {
    			assertEquals(0, bowl.getNeighbour(i).getStones());
    		} else {
    			assertEquals(4, bowl.getNeighbour(i).getStones());
    		}
    		
    		if (i < 7) {
    			assertTrue(bowl.getOwner() == bowl.getNeighbour(i).getOwner());
    		} else {
    			assertTrue(bowl.getOwner().getOpponent() == 
    					   bowl.getNeighbour(i).getOwner());
    		}
    	}
    	assertEquals(bowl, bowl.getNeighbour(14));
    }
    
    @Test
    public void tryToMoveEmptyBowlDoesNotWork() {
    	Bowl bowl1 = new Bowl();
    	
    	Bowl bowl2 = bowl1.getNeighbour(7);
    	
    	bowl1.startMoveStones(); // move of player 1
    	
    	assertEquals(0, bowl1.getStones());
    	assertEquals(5, bowl1.getNeighbour().getStones());
    	
    	bowl2.startMoveStones(); // move of player 2
    	bowl1.startMoveStones(); // move of player 1
    	
    	assertEquals(0, bowl1.getStones());
    	assertEquals(5, bowl1.getNeighbour().getStones());
    }
    
    @Test
    public void tryToMoveOpponendsBowlDoesNotWork() {
    	Bowl bowl1 = new Bowl();
    	Bowl bowl2 = bowl1.getNeighbour(7);

    	bowl2.startMoveStones();
    	
    	assertEquals(4, bowl2.getStones());
    	assertEquals(4, bowl2.getNeighbour().getStones());
    }
    
    @Test
    public void tryToMoveOwnBowlWorks() {
    	Bowl bowl = new Bowl();
    	
    	bowl.startMoveStones();
    	
    	assertEquals(0, bowl.getStones());
    	
    	for (int i = 1; i < 5; i++) {
    		assertEquals(5, bowl.getNeighbour(i).getStones());
    	}
    	
    	assertEquals(4, bowl.getNeighbour(5).getStones());
    }
    
    @Test
    public void getNeighbourWorks() {
    	Bowl bowl = new Bowl();
    	
    	assertTrue(bowl.getNeighbour(1) == bowl.getNeighbour());
    	assertTrue(bowl.getNeighbour(2) == bowl.getNeighbour().getNeighbour());
    	assertTrue(bowl == bowl.getNeighbour(0));
    	assertFalse(bowl.getNeighbour(1) == bowl.getNeighbour(2));
    }
    
    @Test
    public void getOppositeWorks() {
    	Bowl bowl = new Bowl();

    	assertFalse(bowl.getOpposite() == bowl.getNeighbour(8));
    	assertTrue(bowl.getOpposite() == bowl.getNeighbour(12));
    	assertTrue(bowl == bowl.getOpposite().getOpposite());
    }
}