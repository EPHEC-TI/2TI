package tp7.controller;

import tp7.model.Bibliotheque;
import tp7.view.BibliothequeVue;

//* : all modifications are indicated with a star

public class BibliothequeController {
	Bibliotheque model; 
	BibliothequeVue vue;
	public BibliothequeController(Bibliotheque model) {
		this.model = model;
	}


	public void emprunteLivre(int numLivre) {
		if(model.emprunte(numLivre)) { // *
			vue.affiche("Emprunt livre " + numLivre + " OK"); //*
		} else vue.affiche("Emprunt livre " + numLivre + " impossible"); //*
		//TODO 
	}

	public void rendreLivre(int numLivre) {
		model.rendre((numLivre)); //*
		vue.affiche("Livre " + numLivre + " disponible"); // *
		//TODO
	}


	public void addView(BibliothequeVue vue) {
		this.vue = vue;
		
	}

}
