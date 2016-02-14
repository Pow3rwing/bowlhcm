package com.projet.apps.scorebowling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

/**
 * Calcul du score
 *
 */
public class App {
    public static void main(String[] args) {
	BufferedReader lecteurAvecBuffer = null;
	File fichierScore = new File("E://Dev//ressources//scores.txt");
	if (!fichierScore.exists()) {
	    System.out.println("File not found");
	} else {
	    try {
		String scoreLigne;
		lecteurAvecBuffer = new BufferedReader(new FileReader(fichierScore));
		while ((scoreLigne = lecteurAvecBuffer.readLine()) != null) {
		    if (StringUtils.isNotBlank(scoreLigne)) {
			Partie partie = BowlingPartieBuilder.getPartieBuilderInstance().buildPartie(scoreLigne);
			System.out.print(partie);
		    }
		}
	    } catch (FileNotFoundException e) {
		System.out.println("FileNotFoundException");
	    } catch (IOException e) {
		System.out.println("IOException");
	    }

	}
    }
}
