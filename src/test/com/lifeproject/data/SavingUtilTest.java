package test.com.lifeproject.data;

import com.lifeproject.data.Humain;
import com.lifeproject.data.Race;
import com.lifeproject.data.SavingUtil;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.*;

/**
 * Created by beaussan on 03/09/15.
 */
public class SavingUtilTest {

    @Test
    public void testWriteToFile() throws Exception {
        Humain h = new Humain("aaa","bbbb",true);
        h.setHistory("Je suis un nain dans une cave et je suis mort.");
        h.setRace(Race.DWARF);
        SavingUtil.writeToFile(h,"nain.txt");


        BufferedReader br = null;
        int cpt = 1;
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("nain.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);


                switch (cpt){
                    case 1:
                        assertEquals("Name : aaa",sCurrentLine);
                        break;
                    case 2:
                        assertEquals("Surname : bbbb",sCurrentLine);
                        break;
                    case 3:
                        assertEquals("Sexe : Homme",sCurrentLine);
                        break;
                    case 4:
                        assertEquals("Race : DWARF",sCurrentLine);
                        break;
                    case 5:
                        assertEquals("Je suis un nain dans une cave et je suis mort.",sCurrentLine);
                        break;
                    default:
                        assertTrue(false,"More information is present on this file");
                }
                cpt++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}