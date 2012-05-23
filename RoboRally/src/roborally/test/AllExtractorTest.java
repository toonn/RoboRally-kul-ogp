package roborally.test;

import static org.junit.Assert.*;

import org.junit.Test;

import roborally.filters.AllExtractor;
import roborally.model.*;

/**
 * @author Ben Adriaenssens (ben.adriaenssens@student.kuleuven.be) - WtkCws,
 *         Toon Nolten (toon.nolten@student.kuleuven.be) - CwsElt.
 */
public class AllExtractorTest {

    @Test
    public void testAllExtractor() {
        AllExtractor allExtractor1 = null;
        assertNull(allExtractor1);

        allExtractor1 = new AllExtractor();

        assertNotNull(allExtractor1);
    }

    @Test
    public void testIsSatisfied() {
        assertTrue(allExtractor.isSatisfied(new Wall()));
        assertTrue(allExtractor.isSatisfied(new Battery()));
        assertTrue(allExtractor.isSatisfied(new SurpriseBox()));
    }

    private final AllExtractor allExtractor = new AllExtractor();
}
