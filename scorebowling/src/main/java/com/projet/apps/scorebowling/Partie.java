package com.projet.apps.scorebowling;

import java.util.LinkedList;

public class Partie {

    private LinkedList<Phase> phasesLinkedList;

    Partie() {
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
	BowlingPartieCalculator bowlingPartieCalculator = BowlingPartieCalculator.getBowlingPartieCalculatorInstance();
	if (phasesLinkedList.size() > 0) {
	    LinkedList<Integer> scoreLinkedList = bowlingPartieCalculator.calculerScore(this);
	    int i = 1;
	    for (int j = 0; j < BowlingCst.NB_FRAMES; j++) {
		Phase phase = this.getPhasesLinkedList().get(j);
		stringBuffer.append("Phase " + i + " :" + phase.getContenu() + "\n");
		stringBuffer.append("score simple :" + phase.getScoreSimple() + "\n");
		stringBuffer.append("Type Phase : " + phase.getTypePhase() + "\n");
		stringBuffer.append("Score cumulé : " + scoreLinkedList.get(i - 1) + "\n");
		i++;
	    }
	}
	return stringBuffer.toString();
    }
}
