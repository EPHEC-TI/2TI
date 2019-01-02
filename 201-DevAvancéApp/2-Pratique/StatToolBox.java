package debugging;

/**
 * Cette classe permet d'effectuer des statistiques sur un tableau de valeurs entières.
 * @author Virginie Van den Schrieck
 *
 */
public class StatToolBox {
	int [] values; 
	
	/**
	 * Initialise l'objet à partir d'une série de valeurs
	 * @param val Un tableau contenant les valeurs de travail
	 */
	public StatToolBox(int [] val){
		values = val;
	}
	/**
	 * Ajoute une valeur au tableau, à l'indice indiqué. 
	 * Si une valeur est déjà présente dans le tableau, elle est écrasée par la nouvelle valeur.
	 * @param v une valeur à ajouter à la liste
	 * @param index l'indice auquel la valeur est insérée
	 * @exception ArrayIndexOutOfBoundsException si l'indice est au delà des limites du tableau
	 */
	public void setValueAtIndex(int v, int index){
		values[index]=v;
	}
	/**
	 * Renvoie la valeur à l'indice spécifié.
	 * @param index l'indice de l'élément à renvoyer
	 * @return la valeur stockée à l'indice spécifié
	 * @exception ArrayIndexOutOfBoundsException si l'indice est au delà des limites du tableau
	 */
	public int getValueAtIndex(int index){
		return values[index];
	}
	/**
	 * Calcule la moyenne des valeurs contenues l'objet. 
	 * @return la moyenne 
	 */
	public double moyenne(){
		int sum=0;
		for(int v : values){
			sum += v;
		}
		int num_values = values.length;
		int moyenne = sum/num_values;
		return moyenne;
	}
	
}
