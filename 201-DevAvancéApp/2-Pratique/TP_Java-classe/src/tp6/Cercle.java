package tp6;

import javax.swing.JOptionPane;

public class Cercle extends Shape {
	private double rayon;

	public Cercle(int x, int y, double rayon) {
		super(x, y);
		this.rayon = rayon;
	}

	public Cercle() {
		super();
		this.rayon = Double.parseDouble(JOptionPane.showInputDialog("Entrez le coté du cercle"));
	}

	@Override
	public double surface() {
		return 2 * Math.PI * Math.pow(this.rayon, 2);
	}

	@Override
	public double perim() {
		return 2 * Math.PI * this.rayon;
	}

	@Override
	public void agrandit(int facteur) {
		this.rayon *= facteur;
		
	}
}
