package com.projet.apps.scorebowling.process;

import java.util.LinkedList;
import java.util.List;

import com.projet.apps.scorebowling.model.BowlingSession;
import com.projet.apps.scorebowling.model.Phase;
import com.projet.apps.scorebowling.util.TypePhase;

/**
 * Permet de calculer le score à partir d'une partie
 * 
 * @author cherrat
 *
 */
public final class BowlingSessionCalculator {

    private static BowlingSessionCalculator bowlingSessionCalculator = new BowlingSessionCalculator();

    private BowlingSessionCalculator() {
	super();
    }

    public static BowlingSessionCalculator getBowlingSessionCalculatorInstance() {
	return bowlingSessionCalculator;
    }

    /**
     * Calcul le score de la partie
     * 
     * @param partie
     * @return la liste
     */
    public List<Integer> calculerScore(BowlingSession partie) {
	LinkedList<Phase> phasesLinkedList = partie.getPhasesLinkedList();
	LinkedList<Integer> scoreLinkedList = new LinkedList<Integer>();

	for (int nbPhase = 0; nbPhase < 10; nbPhase++) {
	    calculPhase(phasesLinkedList.get(nbPhase), phasesLinkedList, scoreLinkedList, nbPhase);
	}
	return scoreLinkedList;
    }

    /**
     * Réalise le calcul de chaque frame en s'appuyant sur le type de frame et
     * le "score simple" de la frame
     * 
     * @param phase
     * @param phasesLinkedList
     * @param scoreLinkedList
     * @param numeroPhase
     */
    private void calculPhase(Phase phase, LinkedList<Phase> phasesLinkedList, LinkedList<Integer> scoreLinkedList,
	    int numeroPhase) {
	int score = 0;
	if (phase.getTypePhase().equals(TypePhase.VIDE)) {
	    if (numeroPhase != 0) {
		score = scoreLinkedList.get(numeroPhase - 1);
		scoreLinkedList.add(score);
	    } else {
		scoreLinkedList.add(score);
	    }

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
