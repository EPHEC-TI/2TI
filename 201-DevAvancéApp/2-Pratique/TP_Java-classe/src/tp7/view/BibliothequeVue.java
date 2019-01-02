package tp7.view;

import java.util.Observer;

import tp7.controller.BibliothequeController;
import tp7.model.Bibliotheque;

//* : all modifications are indicated with a star.

public abstract class BibliothequeVue implements Observer{
	
	protected Bibliotheque model;
	protected BibliothequeController controller;
	
	BibliothequeVue(Bibliotheque model,
			BibliothequeController controller) {
		this.model = model;
		this.controller = controller;
		// TODO : Connexion entre la vue et le modele
		model.addObserver(this); // *
	}

	public abstract void affiche(String string) ;
}
