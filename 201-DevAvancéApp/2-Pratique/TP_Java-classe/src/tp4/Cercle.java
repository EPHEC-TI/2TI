package tp4;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class Cercle {
	private double rayon;
	
	public Cercle(double rayon) {
		this.rayon = rayon;
	}
	
	public Cercle() {
		this.rayon = Double.parseDouble(JOptionPane.showInputDialog("Entrez le rayon du cercle"));
	}
	
	public double perim() {
		return(2*Math.PI*this.rayon);
	}
	
	public double aire() {
		//return (this.rayon*this.rayon*Math.PI);
		return (Math.pow(this.rayon, 2)*Math.PI);
	}
	
	public String toString() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return ("Cercle de rayon: "+this.rayon+"\nPérimètre: "+formatter.format(this.perim())+
				"\nAire: "+formatter.format(this.aire()));
	}
	
	public static void main(String[] args) {
		Cercle trigo = new Cercle();
		System.out.println(trigo);
	}
}
