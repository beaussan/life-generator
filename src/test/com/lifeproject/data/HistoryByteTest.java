package test.com.lifeproject.data;

import com.lifeproject.data.HistoryByte;
import com.lifeproject.data.Humain;

import static org.testng.Assert.*;

/**
 * Created by beaussan on 03/09/15.
 */
public class HistoryByteTest {

    private Humain humH = new Humain("Nicolas","Beaussartt",true);
    private Humain humF = new Humain("Michelle","Dubois",false);
    private HistoryByte h1;
    private HistoryByte h2;

    @org.testng.annotations.Test
    public void testTestGetPossibleOutcome() throws Exception {
        h1.getPossibleOutcome().contains(h2);
    }

    @org.testng.annotations.Test
    public void testTestAdd() throws Exception {
        h1.add(h2);
        h1.getPossibleOutcome().contains(h2);
    }

    @org.testng.annotations.Test
    public void testTestGetStoryFor() throws Exception {
        assertEquals("Bonjour Beaussartt Nicolas; tu est un homme",h1.getStoryFor(humH),"Test string to male");
        assertEquals("Bonjour Dubois Michelle; tu est une femme",h1.getStoryFor(humF), "Test string to female");
    }

    @org.testng.annotations.BeforeMethod
    public void setUp() throws Exception {
        h1 = new HistoryByte("Bonjour {name} {surname}; tu est un{\\e} {homme\\femme}");
        h2 = new HistoryByte("La suite !");
    }
}