package com.projet.apps.scorebowling;

import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

public class BowlingPartieBuilder {

    private static BowlingPartieBuilder partieBuilder = new BowlingPartieBuilder();

    private BowlingPartieBuilder() {
	super();
    }

    /**
     * 
     * @return
     */
    public static BowlingPartieBuilder getPartieBuilderInstance() {
	return partieBuilder;
    }

    /**
     * 
     * @param ligneScore
     * @return
     */
    public Partie buildPartie(String ligneScore) {
	Partie partie = new Partie();
	LinkedList<String> tokensLinkedList = new LinkedList<String>();
	String[] tokens = ligneScore.split(BowlingCst.EMPTY_STRING_SPLIT);
	for (String token : tokens) {
	    tokensLinkedList.add(token);
	}
	tokensToPartie(tokensLinkedList, partie, 1);
	return partie;
    }

    /**
     * 
     * @param tokensLinkedList
     * @param partie
     * @param nbPhase
     */
    private void tokensToPartie(LinkedList<String> tokensLinkedList, Partie partie, int nbPhase) {
	Integer score = 0;
	Phase phase = new Phase();
	StringBuffer stringBuffer = new StringBuffer();
	if (nbPhase > BowlingCst.NB_FRAMES
		&& partie.getPhasesLinkedList().getLast().getTypePhase().equals(TypePhase.SPARE)) {
	    if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score += Integer.valueOf(stringBuffer.toString());
		phase.setTypePhase(TypePhase.SPARE_BONUS);
		phase.setScorePremierEssai(Integer.valueOf(stringBuffer.toString()));
		partie.addPhase(phase);
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
		phase.setContenu(tokensLinkedList.removeFirst());
		phase.setScorePremierEssai(BowlingCst.SPARE_OR_STRIKE);
		phase.setScoreSimple(BowlingCst.SPARE_OR_STRIKE);
		phase.setTypePhase(TypePhase.SPARE_BONUS);
		partie.addPhase(phase);
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		phase.setContenu(tokensLinkedList.removeFirst());
		phase.setTypePhase(TypePhase.SPARE_BONUS);
		partie.addPhase(phase);
	    }
	} else if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
	    stringBuffer.append(tokensLinkedList.removeFirst());
	    score += Integer.valueOf(stringBuffer.toString());
	    phase.setScorePremierEssai(Integer.valueOf(stringBuffer.toString()));
	    if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
		score += Integer.valueOf(tokensLinkedList.getFirst());
		phase.setScoreDeuxiemeEssai(Integer.valueOf(tokensLinkedList.getFirst()));
		stringBuffer.append(tokensLinkedList.removeFirst());
		phase.setContenu(stringBuffer.toString());
		phase.setScoreSimple(score);
		phase.setTypePhase(TypePhase.NORMAL);
		partie.addPhase(phase);
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		phase.setContenu(stringBuffer.toString());
		phase.setScoreSimple(score);
		phase.setTypePhase(TypePhase.NORMAL);
		partie.addPhase(phase);
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.SPARE)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score = BowlingCst.SPARE_OR_STRIKE;
		phase.setContenu(stringBuffer.toString());
		phase.setScoreSimple(score);
		phase.setScoreDeuxiemeEssai(BowlingCst.SPARE_OR_STRIKE - phase.getScorePremierEssai());
		phase.setTypePhase(TypePhase.SPARE);
		partie.addPhase(phase);
	    } else if (nbPhase > BowlingCst.NB_FRAMES
		    && StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score += BowlingCst.SPARE_OR_STRIKE;
		phase.setContenu(stringBuffer.toString());
		phase.setScoreSimple(score);
		phase.setScoreDeuxiemeEssai(BowlingCst.SPARE_OR_STRIKE);
		phase.setTypePhase(TypePhase.STRIKE_BONUS);
		partie.addPhase(phase);
	    }
	} else {
	    if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
		phase.setContenu(tokensLinkedList.removeFirst());
		phase.setScoreSimple(BowlingCst.SPARE_OR_STRIKE);
		phase.setTypePhase(TypePhase.STRIKE);
		phase.setScorePremierEssai(BowlingCst.SPARE_OR_STRIKE);
		partie.addPhase(phase);
		if (nbPhase > BowlingCst.NB_FRAMES) {
		    phase.setTypePhase(TypePhase.STRIKE_BONUS);
		    if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
			phase.setScoreDeuxiemeEssai(Integer.valueOf(tokensLinkedList.getFirst()));
			phase.setScoreSimple(phase.getScoreSimple() + phase.getScoreDeuxiemeEssai());
			phase.setContenu(
				stringBuffer.append(phase.getContenu()).append(tokensLinkedList.getFirst()).toString());
		    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
			phase.setContenu(stringBuffer.append(phase.getContenu()).append(BowlingCst.NOTHING).toString());
		    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
			phase.setScoreDeuxiemeEssai(BowlingCst.SPARE_OR_STRIKE);
			phase.setScoreSimple(phase.getScoreSimple() + phase.getScoreDeuxiemeEssai());
			phase.setContenu(stringBuffer.append(phase.getContenu()).append(BowlingCst.STRIKE).toString());
		    }
		}
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.SPARE)) {
		    stringBuffer.append(tokensLinkedList.removeFirst());
		    phase.setContenu(stringBuffer.toString());
		    phase.setScoreSimple(BowlingCst.SPARE_OR_STRIKE);
		    phase.setTypePhase(TypePhase.SPARE);
		    partie.addPhase(phase);
		} else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		    stringBuffer.append(tokensLinkedList.removeFirst());
		    phase.setContenu(stringBuffer.toString());
		    phase.setTypePhase(TypePhase.VIDE);
		    partie.addPhase(phase);
		} else if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
		    score += Integer.valueOf(tokensLinkedList.getFirst());
		    phase.setScoreDeuxiemeEssai(Integer.valueOf(tokensLinkedList.getFirst()));
		    stringBuffer.append(tokensLinkedList.removeFirst());
		    phase.setContenu(stringBuffer.toString());
		    phase.setScoreSimple(score);
		    phase.setTypePhase(TypePhase.NORMAL);
		    partie.addPhase(phase);
		}
	    }
	}
	if (nbPhase < 10
		|| (nbPhase == 10 && (partie.getPhasesLinkedList().getLast().getTypePhase().equals(TypePhase.STRIKE)
			|| partie.getPhasesLinkedList().getLast().getTypePhase().equals(TypePhase.SPARE)))) {
	    nbPhase++;
	    tokensToPartie(tokensLinkedList, partie, nbPhase);
	}
    }

}
