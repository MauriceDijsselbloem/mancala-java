package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test 
    public void playerOpponentIsCreated() {
        Player player = new Player();
        assertFalse(player.getOpponent() == player);
        assertTrue(player.getOpponent().getOpponent() == player);
    }
}