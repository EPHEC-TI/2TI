package tp5;

public class Calculatrice {
	private double valeurCourante;

	public double getValeurCourante() {
		return valeurCourante;
	}

	public void setValeurCourante(double valeurCourante) {
		this.valeurCourante = valeurCourante;
	}
	
	public double carre(){
		return Math.pow(this.valeurCourante, 2);
	}
	
	@Override
	public String toString(){
		return("Calculatrice valeurCourante = "+this.valeurCourante);
	}
}
