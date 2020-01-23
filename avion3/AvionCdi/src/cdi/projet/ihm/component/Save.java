package cdi.projet.ihm.component;

import java.io.Serializable;

public class Save implements Serializable {

	String nom = "";

	private int score= 0;

	private String date = "";

	public Save(final String nom, final int score, final String date) {

	this.nom = nom;

	this.score = score;

	this.date = date;
	}


	public String getNom() {

	return this.nom;

	}


	public void setNom(final String nom) {

	this.nom = nom;

	}


	public int getsScore() {

	return this.score;

	}


	public void setScore(final int score) {

	this.score = score;

	}


	public String getDate() {

	return this.date;

	}


	public void setDate(final String date) {

	this.date = date;

	}

	}