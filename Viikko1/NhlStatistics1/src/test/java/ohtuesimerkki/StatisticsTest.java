
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void etsiJaLoyda() {
        Player player = stats.search("Semenko");
        String etsitty = player.getName();
        assertTrue(etsitty.contains("Semenko"));
    }
    
    @Test
    public void etsiJaEiLoyda() {
        Player player = stats.search("Litmanen");
        assertEquals(player, null);
    }
    
    @Test
    public void joukkueKokoOikea() {
        List<Player> pelaajat = stats.team("EDM");
        int koko = pelaajat.size();
        assertEquals(3, koko);
    }
    
    @Test
    public void joukkueKokoVäärä() {
        List<Player> pelaajat = stats.team("EDM");
        int koko = pelaajat.size();
        assertNotEquals(2, koko);
    }
    
    @Test
    public void topScore() {
        List lista = stats.topScorers(3);
        int koko = lista.size();
        assertEquals(4, koko);
    }
}
