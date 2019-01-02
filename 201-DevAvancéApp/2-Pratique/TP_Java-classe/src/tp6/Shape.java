package tp6;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public abstract class Shape implements Affichable, Transformable {
	private int id;
	private static int cpt = 1;
	private int x;
	private int y;

	public Shape(int x, int y) {
		this.x = x;
		this.y = y;
		this.id = cpt;
		cpt++;
	}

	public Shape() {
		this.x = Integer.parseInt(JOptionPane.showInputDialog("Entrez x !"));
		this.y = Integer.parseInt(JOptionPane.showInputDialog("Entrez y !"));
		this.id = cpt;
		cpt++;
	}

	public abstract double surface();

	public abstract double perim();

	public String getNom() {
		return (getClass().getSimpleName() + "-" + this.id);
	}

	public String toString() {
		NumberFormat nf = new DecimalFormat("#0.00");
		String temp = "";
		temp += this.getNom();
		temp += "\nX: " + this.x + " Y: " + this.y;
		temp += "\nSurface: " + nf.format(this.surface());
		temp += "\nPérimètre: " + nf.format(this.perim());
		return temp;
	}
	
	public void affiche(){
		System.out.println(this);
	}
	
	public void transforme(int deltaX, int deltaY){
		this.x = deltaX;
		this.y = deltaY;
	}
	
	public void deplace(int deltaX, int deltaY){
		this.x = deltaX;
		this.y = deltaY;
	}
}
