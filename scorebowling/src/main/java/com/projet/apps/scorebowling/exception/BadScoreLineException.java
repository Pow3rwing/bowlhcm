package com.projet.apps.scorebowling.exception;

/**
 * 
 * @author cherrat
 *
 */
public class BadScoreLineException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public BadScoreLineException(String msg) {
	super(msg);
    }
}
