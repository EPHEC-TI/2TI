package tp7.model;

import java.util.Observable;

// * : all modifications are indicated with a star.

//TODO : Me transformer en modèle MVC!

public class Bibliotheque extends Observable { // * extends added
	Livre [] livres; 
	
	public Bibliotheque(){
		livres = new Livre[10];
		for(int i = 0; i<10; i++){
			livres[i] = new Livre("Livre "+i);
		}
	}
	public boolean emprunte(int i){
		if(livres[i].emprunte()){
			setChanged(); // *
			notifyObservers(); // *
			return true;
		}
		return false;
	}
	public void rendre(int i){
		if(livres[i].estEmprunte()){
			livres[i].rendre();
			setChanged(); //*
			notifyObservers(); //* 
		}
	}
	public String toString(){
		String result = "";
		for(int i = 0; i< livres.length; i++){
			result += (i + " : " + livres[i] + "\n") ;
		}
		return result;
	}
	/*
	 * @return une copie des livres de la bibliothÃ¨que
	 */
	public Livre [] getLivres(){
		return livres.clone();
	}
}
