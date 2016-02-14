package com.projet.apps.scorebowling;

public class Phase {

    private String contenu;
    private Integer scoreSimple;
    private Integer scorePremierEssai;
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
