package com.projet.apps.scorebowling;

import java.util.List;
import java.util.regex.Pattern;

import com.projet.apps.scorebowling.builders.BowlingSessionBuilder;
import com.projet.apps.scorebowling.exception.BadScoreLineException;
import com.projet.apps.scorebowling.model.BowlingSession;
import com.projet.apps.scorebowling.process.BowlingPartieCalculator;
import com.projet.apps.scorebowling.util.BowlingCst;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BowlingScoreCalculatorTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName
     *            name of the test case
     */
    public BowlingScoreCalculatorTest(String testName) {
	super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
	return new TestSuite(BowlingScoreCalculatorTest.class);
    }

    public void testPatternAllowed() {
	assertTrue(Pattern.matches(BowlingCst.INPUT_ALLOWED_REGEX, "1234567890-/X"));
    }

    public void testPatternNotAllowed() {
	assertFalse(Pattern.matches(BowlingCst.INPUT_ALLOWED_REGEX, "ABCD*%"));
    }

    public void testPerfectGame() throws BadScoreLineException {
	BowlingSession partie = BowlingSessionBuilder.getBowlingSessionBuilderInstance().buildPartie("XXXXXXXXXXXX");
	BowlingPartieCalculator bowlingPartieCalculator = BowlingPartieCalculator.getBowlingPartieCalculatorInstance();
	List<Integer> scoreList = bowlingPartieCalculator.calculerScore(partie);
	assertTrue(scoreList.get(BowlingCst.NB_FRAMES - 1) == 300);
    }

}
