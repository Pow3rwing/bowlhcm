package com.projet.apps.scorebowling.model;

import java.util.LinkedList;

import com.projet.apps.scorebowling.process.BowlingPartieCalculator;
import com.projet.apps.scorebowling.util.BowlingCst;

/**
 * Modélisation d'une partie/session de bowling
 * 
 * @author cherrat
 *
 */
public class BowlingSession {

    private String session;
    private LinkedList<Phase> phasesLinkedList;

    public BowlingSession() {
	super();
	phasesLinkedList = new LinkedList<Phase>();
    }

    public LinkedList<Phase> getPhasesLinkedList() {
	return phasesLinkedList;
    }

    public void setPhasesLinkedList(LinkedList<Phase> phasesLinkedList) {
	this.phasesLinkedList = phasesLinkedList;
    }

    public void addPhase(Phase phase) {
	phasesLinkedList.add(phase);
    }

    @Override
    public String toString() {
	StringBuffer stringBuffer = new StringBuffer();
	stringBuffer
		.append("========================================================================================\n");
	BowlingPartieCalculator bowlingPartieCalculator = BowlingPartieCalculator.getBowlingPartieCalculatorInstance();
	if (phasesLinkedList.size() > 0) {
	    LinkedList<Integer> scoreLinkedList = (LinkedList<Integer>) bowlingPartieCalculator.calculerScore(this);
	    int i = 1;
	    stringBuffer.append("Session : " + this.getSession() + " || score final :" + scoreLinkedList.getLast()
		    + "|| détail de la partie : " + "\n");
	    for (int j = 0; j < BowlingCst.NB_FRAMES; j++) {
		Phase phase = this.getPhasesLinkedList().get(j);
		stringBuffer.append("Phase " + i + " :" + phase.getContenu() + "\n");
		stringBuffer.append("score simple :" + phase.getScoreSimple() + "\n");
		stringBuffer.append("Type Phase : " + phase.getTypePhase() + "\n");
		stringBuffer.append("Score cumulé : " + scoreLinkedList.get(i - 1) + "\n");

		i++;
	    }
	}
	stringBuffer
		.append("========================================================================================\n");
	return stringBuffer.toString();
    }

    public String getSession() {
	return session;
    }

    public void setSession(String session) {
	this.session = session;
    }
}
