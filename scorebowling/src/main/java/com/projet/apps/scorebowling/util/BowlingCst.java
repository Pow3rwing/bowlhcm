package com.projet.apps.scorebowling.util;

/**
 * 
 * @author cherrat
 *
 */
public final class BowlingCst {

    private BowlingCst() {

    }

    public final static String SPARE = "/";
    public final static String NOTHING = "-";
    public final static String STRIKE = "X";
    public final static String EMPTY_STRING_SPLIT = "";
    public final static int SPARE_OR_STRIKE = 10;
    public final static int NB_FRAMES = 10;
    public final static int MAX_TOKENS = 21;
    public final static String INPUT_ALLOWED_REGEX = "([0-9 \\- X \\/]+)";
}
