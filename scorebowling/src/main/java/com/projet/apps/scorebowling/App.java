package com.projet.apps.scorebowling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.projet.apps.scorebowling.builders.BowlingSessionBuilder;
import com.projet.apps.scorebowling.exception.BadScoreLineException;
import com.projet.apps.scorebowling.model.BowlingSession;

/**
 * Calcul du score de bowling
 *
 */
public class App {
    private static Logger logger = Logger.getLogger("com.projet.apps.scorebowling.App");

    public static void main(String[] args) {
	BufferedReader bufferedReader = null;
	File fichierScore = new File("E://Dev//ressources//scores.txt");
	if (!fichierScore.exists()) {
	    System.out.println("File not found");
	} else {
	    try {
		String scoreLigne;
		bufferedReader = new BufferedReader(new FileReader(fichierScore));
		while ((scoreLigne = bufferedReader.readLine()) != null) {
		    if (StringUtils.isNotBlank(scoreLigne)) {
			BowlingSession partie = BowlingSessionBuilder.getBowlingSessionBuilderInstance()
				.buildPartie(scoreLigne);
			System.out.print(partie);
		    }
		}
	    } catch (FileNotFoundException e) {
		logger.log(Level.SEVERE, "FileNotFoundException", e);
	    } catch (IOException e) {
		logger.log(Level.SEVERE, "IOException", e);
	    } catch (BadScoreLineException e) {
		logger.log(Level.SEVERE, e.getMessage(), e);
	    }

	}
    }
}
