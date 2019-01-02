package tp5;

public class CalculatriceAvecMemoire extends Calculatrice {
	private double memoire;
	
	public void save() {
		this.memoire = getValeurCourante();
	}
	
	public void load() {
		setValeurCourante(this.memoire);
	}
}
