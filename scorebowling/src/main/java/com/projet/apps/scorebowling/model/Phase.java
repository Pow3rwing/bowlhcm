package com.projet.apps.scorebowling.model;

import com.projet.apps.scorebowling.util.TypePhase;

/**
 * Modélisation de la frame d'une partie de bowling
 * 
 * @author cherrat
 *
 */
public class Phase {

    private String contenu;
    private Integer scoreSimple;
    private Integer scorePremierEssai;

    public Phase(String contenu, Integer scoreSimple, Integer scorePremierEssai, Integer scoreDeuxiemeEssai,
	    TypePhase typePhase) {
	super();
	this.contenu = contenu;
	this.scoreSimple = scoreSimple;
	this.scorePremierEssai = scorePremierEssai;
	this.scoreDeuxiemeEssai = scoreDeuxiemeEssai;
	this.typePhase = typePhase;
    }

    private Integer scoreDeuxiemeEssai;
    private TypePhase typePhase;

    public Phase() {
	this.scoreSimple = 0;
	this.scorePremierEssai = 0;
	this.scoreDeuxiemeEssai = 0;
    }

    public Phase(String contenu) {
	this.contenu = contenu;
	this.scoreSimple = 0;
	this.scorePremierEssai = 0;
	this.scoreDeuxiemeEssai = 0;
    }

    public Phase(String contenu, Integer scoreSimple) {
	this.contenu = contenu;
	this.scoreSimple = scoreSimple;
    }

    public String getContenu() {
	return contenu;
    }

    public void setContenu(String contenu) {
	this.contenu = contenu;
    }

    public TypePhase getTypePhase() {
	return typePhase;
    }

    public void setTypePhase(TypePhase typePhase) {
	this.typePhase = typePhase;
    }

    public Integer getScoreSimple() {
	return scoreSimple;
    }

    public void setScoreSimple(Integer scoreSimple) {
	this.scoreSimple = scoreSimple;
    }

    public Integer getScoreDeuxiemeEssai() {
	return scoreDeuxiemeEssai;
    }

    public void setScoreDeuxiemeEssai(Integer scoreDeuxiemeEssai) {
	this.scoreDeuxiemeEssai = scoreDeuxiemeEssai;
    }

    public Integer getScorePremierEssai() {
	return scorePremierEssai;
    }

    public void setScorePremierEssai(Integer scorePremierEssai) {
	this.scorePremierEssai = scorePremierEssai;
    }

}
