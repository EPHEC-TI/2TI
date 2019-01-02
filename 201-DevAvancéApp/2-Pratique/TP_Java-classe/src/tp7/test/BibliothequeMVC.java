package tp7.test;
/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 * modified by vvds
 */

// import mvc.controller.*;
// import mvc.model.*;
// import mvc.view.*;
import tp7.controller.BibliothequeController;
import tp7.model.Bibliotheque;
import tp7.view.BibliothequeVue;
import tp7.view.BibliothequeVueConsole;
import tp7.view.BibliothequeVueGUI;

//* : all modifications are indicated with a star.

public class BibliothequeMVC {
	public BibliothequeMVC() {
		//Cr�ation du mod�le
		Bibliotheque model = new Bibliotheque(); // *
		//TODO

		//Cr�ation des controleurs : Un pour chaque vue
		//Chaque controleur doit avoir une r�f�rence vers le mod�le pour pouvoir le commander
		BibliothequeController ctrlGUI = new BibliothequeController(model); // *
		BibliothequeController ctrlConsole = new BibliothequeController(model); // *
		//TODO
		
		//Cr�ation des vues.
		//Chaque vue doit connaitre son controleur et avoir une r�f�rence vers le mod�le pour pouvoir l'observer
		BibliothequeVue gui = new BibliothequeVueGUI(model, ctrlGUI, 200, 200); // *
		BibliothequeVue console = new BibliothequeVueConsole(model, ctrlConsole); // *
		//TODO
		
		//On donne la r�f�rence � la vue pour chaque controleur
		ctrlGUI.addView(gui); // *
		ctrlConsole.addView(console); // *
		//TODO
		
		
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BibliothequeMVC();
			}
		});
	}
}
