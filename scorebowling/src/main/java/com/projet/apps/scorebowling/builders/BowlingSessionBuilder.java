package com.projet.apps.scorebowling.builders;

import java.util.LinkedList;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.projet.apps.scorebowling.exception.BadScoreLineException;
import com.projet.apps.scorebowling.model.BowlingSession;
import com.projet.apps.scorebowling.model.Phase;
import com.projet.apps.scorebowling.util.BowlingCst;
import com.projet.apps.scorebowling.util.TypePhase;

/**
 * A partir d'une ligne de score créé une modélisation de la partie
 * 
 * @author cherrat
 *
 */
public class BowlingSessionBuilder {

    private static BowlingSessionBuilder partieBuilder = new BowlingSessionBuilder();

    private BowlingSessionBuilder() {
	super();
    }

    /**
     * 
     * @return
     */
    public static BowlingSessionBuilder getBowlingSessionBuilderInstance() {
	return partieBuilder;
    }

    /**
     * Construit la partie/session en partant d'une ligne de score
     * 
     * @param ligneScore
     * @return
     * @throws BadScoreLineException
     */
    public BowlingSession buildPartie(String ligneScore) throws BadScoreLineException {

	BowlingSession partie = new BowlingSession();
	if (!Pattern.matches(BowlingCst.INPUT_ALLOWED_REGEX, ligneScore)) {
	    throw new BadScoreLineException("La feuille de score est invalide");
	}
	if (StringUtils.isBlank(ligneScore) || ligneScore.length() > BowlingCst.MAX_TOKENS) {
	    throw new BadScoreLineException("Nombre incohérent de lancers");
	}
	LinkedList<String> tokensLinkedList = new LinkedList<String>();
	partie.setSession(ligneScore);
	String[] tokens = ligneScore.split(BowlingCst.EMPTY_STRING_SPLIT);
	for (String token : tokens) {
	    tokensLinkedList.add(token);
	}
	tokensToBowlingSession(tokensLinkedList, partie, 1);
	return partie;
    }

    /**
     * 
     * @param tokensLinkedList
     * @param partie
     * @param nbPhase
     */
    private void tokensToBowlingSession(LinkedList<String> tokensLinkedList, BowlingSession partie, int nbPhase) {
	Integer score = 0;
	Integer scorePrem = 0;
	Integer scoreSec = 0;
	Phase phase = new Phase();
	StringBuffer stringBuffer = new StringBuffer();
	if (nbPhase > BowlingCst.NB_FRAMES
		&& partie.getPhasesLinkedList().getLast().getTypePhase().equals(TypePhase.SPARE)) {
	    if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score += Integer.valueOf(stringBuffer.toString());
		scorePrem = Integer.valueOf(stringBuffer.toString());
		partie.addPhase(new Phase(stringBuffer.toString(), score, scorePrem, scoreSec, TypePhase.SPARE_BONUS));
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
		partie.addPhase(new Phase(tokensLinkedList.removeFirst(), BowlingCst.SPARE_OR_STRIKE,
			BowlingCst.SPARE_OR_STRIKE, scoreSec, TypePhase.SPARE_BONUS));
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		partie.addPhase(
			new Phase(tokensLinkedList.removeFirst(), score, scorePrem, scoreSec, TypePhase.SPARE_BONUS));
	    }
	} else if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
	    stringBuffer.append(tokensLinkedList.removeFirst());
	    score += Integer.valueOf(stringBuffer.toString());
	    scorePrem = Integer.valueOf(stringBuffer.toString());
	    if (StringUtils.isNumeric(tokensLinkedList.getFirst())) {
		score += Integer.valueOf(tokensLinkedList.getFirst());
		scoreSec = Integer.valueOf(tokensLinkedList.getFirst());
		stringBuffer.append(tokensLinkedList.removeFirst());
		partie.addPhase(new Phase(stringBuffer.toString(), score, scorePrem, scoreSec, TypePhase.NORMAL));
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.NOTHING)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		partie.addPhase(new Phase(stringBuffer.toString(), score, scorePrem, scoreSec, TypePhase.NORMAL));
	    } else if (StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.SPARE)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score = BowlingCst.SPARE_OR_STRIKE;
		scoreSec = BowlingCst.SPARE_OR_STRIKE - scorePrem;
		partie.addPhase(new Phase(stringBuffer.toString(), score, scorePrem, scoreSec, TypePhase.SPARE));
	    } else if (nbPhase > BowlingCst.NB_FRAMES
		    && StringUtils.equals(tokensLinkedList.getFirst(), BowlingCst.STRIKE)) {
		stringBuffer.append(tokensLinkedList.removeFirst());
		score += BowlingCst.SPARE_OR_STRIKE;
		partie.addPhase(new Phase(stringBuffer.toString(), score, scorePrem, BowlingCst.SPARE_OR_STRIKE,
			TypePhase.STRIKE_BONUS));
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
	    tokensToBowlingSession(tokensLinkedList, partie, nbPhase);
	}
    }

}
