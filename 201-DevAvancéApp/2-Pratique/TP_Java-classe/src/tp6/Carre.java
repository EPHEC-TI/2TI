package tp6;

import javax.swing.JOptionPane;

public class Carre extends Shape {
	private double cote;

	public Carre(int x, int y, double cote) {
		super(x, y);
		this.cote = cote;
	}

	public Carre() {
		super();
		this.cote = Double.parseDouble(JOptionPane.showInputDialog("Entrez le coté du carré"));
	}

	@Override
	public double surface() {
		return this.cote * this.cote;
	}

	@Override
	public double perim() {
		return 4 * this.cote;
	}

	@Override
	public void agrandit(int facteur) {
		this.cote *= facteur;
		
	}
}
