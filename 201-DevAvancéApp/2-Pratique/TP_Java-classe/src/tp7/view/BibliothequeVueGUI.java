/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

package tp7.view;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.*;

import tp7.controller.BibliothequeController;
import tp7.model.Bibliotheque;
import tp7.model.Livre;

//* : all modifications are indicated with a star.

public class BibliothequeVueGUI extends BibliothequeVue implements ActionListener{

	private JFrame biblioJFrame;
	private JTextField numeroLivre = new JTextField(3);
	private JButton emprunteJButton = new JButton("Emprunter");
	private JButton rendreJButton = new JButton("Rendre");
	private JLabel message = new JLabel(" ");
	private JTable table;
	JPanel textContent = new JPanel();
	

	public BibliothequeVueGUI(Bibliotheque model,
			BibliothequeController controller, int posX, int posY) {
		
		super(model, controller);
		
		//Construction de la fenêtre
		biblioJFrame = new JFrame("Bibliothèque MVC");	
		textContent.setLayout(new BoxLayout(textContent, BoxLayout.Y_AXIS));
		updateTable();
		textContent.add(table.getTableHeader());
		textContent.add(table);
		textContent.add(message);
		
		
		biblioJFrame.add(textContent, BorderLayout.NORTH);
		
		JPanel fieldZone = new JPanel();
		fieldZone.setLayout(new BoxLayout(fieldZone, BoxLayout.X_AXIS));
		JLabel fieldLabel = new JLabel("Numéro du livre à emprunter/rendre : ");
		fieldZone.add(fieldLabel);
		fieldZone.add(numeroLivre);
		biblioJFrame.add(fieldZone, BorderLayout.CENTER);
		JPanel panelbuttons = new JPanel();
		panelbuttons.add(emprunteJButton);
		panelbuttons.add(rendreJButton);
		
		biblioJFrame.add(panelbuttons, BorderLayout.SOUTH);
		biblioJFrame.pack();
		biblioJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		biblioJFrame.setSize(500, 400);
		biblioJFrame.setLocation(300, 400);
		biblioJFrame.setVisible(true);
		
		//Définition des actions sur les éléments de la GUI
		emprunteJButton.addActionListener(this);
		rendreJButton.addActionListener(this);
		biblioJFrame.pack();
		

		
		
	}

	public void affiche(String msg){
		message.setText(msg);
	}
	public void updateTable(){
		Livre [] livres =  model.getLivres();
		Object [][] data = new Object[livres.length][3];

		for(int i=0; i<livres.length; i++){
			data[i][0] = i;
			data[i][1] = livres[i].getName();
			data[i][2] = livres[i].estEmprunte()?"Emprunté":"Disponible";
		}
		String[] head = {"N°", "Titre", "Disponibilité"};
		table = new JTable(data, head);
			
	}
	@Override
	public void update(Observable o, Object arg) {
		updateTable();
		textContent.remove(1);
		textContent.add(table, 1);	
		biblioJFrame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 Object  source=e.getSource();
		 int numLivre = getNumeroLivre();   
		 if(numLivre < 0 || numLivre > 9){
			 affiche("Erreur, ceci n'est pas un numéro de livre valide ");
			 return;
		 }
	     if  (source==emprunteJButton){
	    	 controller.emprunteLivre(numLivre);
	     }
	         
	     else if (source==rendreJButton)
	         controller.rendreLivre(numLivre);
		
	}
	
	public int getNumeroLivre() {
		int result = 0;
		try {
			result = Integer.valueOf(numeroLivre.getText()).intValue();
		}
		catch (NumberFormatException e){
			result = -1;
		}
		return result;
	}

}
