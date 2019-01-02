package tp4;

public class Date {
	private int jour;
	private int mois;
	private int annee;
	
	public Date(String date) {
		String tab [] = date.split("/");
		this.jour = Integer.parseInt(tab[0]);
		this.mois = Integer.parseInt(tab[1]);
		this.annee = Integer.parseInt(tab[2]);
	}
	
	@Override
	public String toString() {
		return (this.jour+"-"+this.mois+"-"+this.annee);
	}
	
	public static void main(String[] args) {
		Date ddn = new Date("1/1/2010");
		
		System.out.println(ddn);
	}
}
