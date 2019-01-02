package tp7.view;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import tp7.controller.BibliothequeController;
import tp7.model.Bibliotheque;



public class BibliothequeVueConsole extends BibliothequeVue implements Observer {
	protected Scanner sc;
	
	public BibliothequeVueConsole(Bibliotheque model,
			BibliothequeController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();	
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);
		printHelp();
		
	}
	

	private void printHelp(){
		affiche("Pour emprunter : E + numéro de livre.");
		affiche("Pour rendre : R + numéro de livre.");
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					if(c.length()!=1){
						affiche("Format d'input incorrect");
						printHelp();
					}
						
					int i = sc.nextInt();
					if(i<0 || i> 9){
						affiche("Numéro du livre incorrect");
						printHelp(); 
					}
					switch(c){
						case "R" :
							controller.rendreLivre(i);
							break;
						case "E" : 
							controller.emprunteLivre(i);
							break;
						default : 
							affiche("Opération incorrecte");
							printHelp();
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
					printHelp();
				}
			}
		}
	}


	@Override
	public void affiche(String string) {
		System.out.println(string);
		
	}

}
