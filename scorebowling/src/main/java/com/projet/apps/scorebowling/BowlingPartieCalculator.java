package com.projet.apps.scorebowling;

import java.util.LinkedList;

public final class BowlingPartieCalculator {
    private static BowlingPartieCalculator bowlingPartieCalculator = new BowlingPartieCalculator();

    private BowlingPartieCalculator() {
	super();
    }

    public static BowlingPartieCalculator getBowlingPartieCalculatorInstance() {
	return bowlingPartieCalculator;
    }

    public LinkedList<Integer> calculerScore(Partie partie) {
	LinkedList<Phase> phasesLinkedList = partie.getPhasesLinkedList();
	LinkedList<Integer> scoreLinkedList = new LinkedList<Integer>();

	for (int nbPhase = 0; nbPhase < 10; nbPhase++) {
	    calculPhase(phasesLinkedList.get(nbPhase), phasesLinkedList, scoreLinkedList, nbPhase);
	}
	return scoreLinkedList;
    }

    private void calculPhase(Phase phase, LinkedList<Phase> phasesLinkedList, LinkedList<Integer> scoreLinkedList,
	    int numeroPhase) {
	int score = 0;
	if (phase.getTypePhase().equals(TypePhase.VIDE)) {
	    scoreLinkedList.add(score);

	} else if (phase.getTypePhase().equals(TypePhase.NORMAL)) {
	    if (numeroPhase != 0) {
		score = scoreLinkedList.get(numeroPhase - 1) + phase.getScoreSimple();
		scoreLinkedList.add(score);
	    } else {
		scoreLinkedList.add(phase.getScoreSimple());
	    }
	} else if (phase.getTypePhase().equals(TypePhase.SPARE)) {
	    if (numeroPhase != 0) {
		score = scoreLinkedList.get(numeroPhase - 1) + phase.getScoreSimple()
			+ phasesLinkedList.get(numeroPhase + 1).getScorePremierEssai();
		scoreLinkedList.add(score);
	    } else {
		score = phase.getScoreSimple() + phasesLinkedList.get(numeroPhase + 1).getScorePremierEssai();
		scoreLinkedList.add(score);
	    }
	} else if (phase.getTypePhase().equals(TypePhase.STRIKE)) {
	    if (numeroPhase != 0) {
		score += scoreLinkedList.get(numeroPhase - 1);
	    }
	    score += phase.getScoreSimple();
	    if (phasesLinkedList.get(numeroPhase + 1).getTypePhase().equals(TypePhase.STRIKE)) {
		score += phasesLinkedList.get(numeroPhase + 1).getScoreSimple();
		if (phasesLinkedList.get(numeroPhase + 2).getTypePhase().equals(TypePhase.STRIKE)) {
		    score += phasesLinkedList.get(numeroPhase + 2).getScoreSimple();
		} else {
		    score += phasesLinkedList.get(numeroPhase + 2).getScorePremierEssai();
		}
	    } else {
		score += phasesLinkedList.get(numeroPhase + 1).getScoreSimple();
	    }
	    scoreLinkedList.add(score);

	}
    }
}
