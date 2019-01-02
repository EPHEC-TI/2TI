package tp8bis;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Swapper extends JFrame implements ActionListener {
	JTextField field1;
	JTextField field2;
	JButton swap;
	JButton exit;

	public Swapper() {
		Box fieldBox = Box.createHorizontalBox();
		field1 = new JTextField();
		field2 = new JTextField();
		fieldBox.add(field1);
		fieldBox.add(field2);

		Box buttonBox = Box.createHorizontalBox();
		swap = new JButton("SWAP");
		exit = new JButton("EXIT");
		buttonBox.add(swap);
		buttonBox.add(exit);
		swap.addActionListener(this);
		exit.addActionListener(this);

		Box panelBox = Box.createVerticalBox();
		setContentPane(panelBox);
		panelBox.add(fieldBox);
		panelBox.add(buttonBox);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				Swapper frame = new Swapper();
				frame.setVisible(true);
				frame.setTitle("Ma première JFrame"); // Titre de la fenêtre
				// frame.setResizable(false); -> Interdire le redimensionnement
				frame.pack();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "SWAP":
			String temp = field1.getText();
			field1.setText(field2.getText());
			field2.setText(temp);
			break;
		case "EXIT":
			this.dispose();
		}
	}
}
