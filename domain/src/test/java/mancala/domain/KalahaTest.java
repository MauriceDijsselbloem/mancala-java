package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KalahaTest {
    @Test 
    public void aKalahaStartsWith0Stones() {
        Bowl kalaha = new Kalaha(0, new Player(), new Bowl());
        int stones = kalaha.getStones();
        assertEquals(0, stones);
    }
    
    @Test
    public void givingStonesToKalahaWorksCorrectly() {
    	// set up
    	Bowl bowl = new Bowl();
    	
    	bowl.getNeighbour(5).setStones(8);
    	bowl.getNeighbour(5).startMoveStones();
    	assertEquals(0, bowl.getNeighbour(5).getStones());
    	assertEquals(1, bowl.getNeighbour(6).getStones());
    	assertEquals(0, bowl.getNeighbour(13).getStones());
    }
}