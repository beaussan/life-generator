package test.com.lifeproject.data;

import com.lifeproject.data.GeneratorUtil;
import com.lifeproject.data.Humain;
import com.lifeproject.data.NameReader;
import com.lifeproject.data.SurnameReader;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by beaussan on 03/09/15.
 */
public class GeneratorUtilTest {

    @Test
    public void testRandomizeNameOf() throws Exception {
        Humain h = new Humain("a","b",false);

        SurnameReader sr=new SurnameReader("/com/lifeproject/res/humain_prenom");
        NameReader nr = new NameReader("/com/lifeproject/res/humain_nom");

        for (int i =0; i < 300; i ++){
            GeneratorUtil.randomizeNameOf(h,nr,sr);
            assertTrue(!h.getName().isEmpty(), "Name not blank");
            assertTrue(!h.getSurname().isEmpty(), "Name not blank");
            assertEquals(h.isMasculin(), sr.isMasculin(h.getSurname()), "Sexuality not good");
        }
    }
}