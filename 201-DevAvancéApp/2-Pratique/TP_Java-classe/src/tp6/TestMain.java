package tp6;

public class TestMain {
	public static void main(String[] args) {
		Shape [] tab = new Shape[3];
		tab[0] = new Point(1, 2);
		tab[1] = new Carre(0, 0, 3);
		tab[2] = new Cercle(-1, -2, 4);

		for(int i=0; i<tab.length; i++){
			tab[i].affiche();
		}
		
		tab[1].agrandit(2);
		tab[1].deplace(-3, 6);
		
		tab[2].agrandit(3);
		tab[2].deplace(2, -4);
	}
}
