package tp8bis;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Swapper2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField field1;
	private JTextField field2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Swapper2 frame = new Swapper2();
					frame.setVisible(true);
					frame.setTitle("Ma deuxième JFrame"); // Première avec WindowBuilder
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Swapper2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 200); // position (x, y) puis largeur et longueur
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		field1 = new JTextField();
		GridBagConstraints gbc_field1 = new GridBagConstraints();
		gbc_field1.insets = new Insets(0, 0, 5, 0);
		gbc_field1.fill = GridBagConstraints.BOTH;
		gbc_field1.gridx = 0;
		gbc_field1.gridy = 0;
		contentPane.add(field1, gbc_field1);
		field1.setColumns(10);

		field2 = new JTextField();
		GridBagConstraints gbc_field2 = new GridBagConstraints();
		gbc_field2.insets = new Insets(0, 0, 5, 0);
		gbc_field2.fill = GridBagConstraints.BOTH;
		gbc_field2.gridx = 0;
		gbc_field2.gridy = 1;
		contentPane.add(field2, gbc_field2);
		field2.setColumns(10);

		JButton swap = new JButton("Swap");
		GridBagConstraints gbc_swap = new GridBagConstraints();
		gbc_swap.fill = GridBagConstraints.BOTH;
		gbc_swap.insets = new Insets(0, 0, 5, 0);
		gbc_swap.gridx = 0;
		gbc_swap.gridy = 2;
		contentPane.add(swap, gbc_swap);
		swap.addActionListener(this);

		JButton clear = new JButton("Clear");
		GridBagConstraints gbc_clear = new GridBagConstraints();
		gbc_clear.fill = GridBagConstraints.BOTH;
		gbc_clear.insets = new Insets(0, 0, 5, 0);
		gbc_clear.gridx = 0;
		gbc_clear.gridy = 3;
		contentPane.add(clear, gbc_clear);
		clear.addActionListener(this);

		JButton exit = new JButton("Exit");
		GridBagConstraints gbc_exit = new GridBagConstraints();
		gbc_exit.fill = GridBagConstraints.BOTH;
		gbc_exit.gridx = 0;
		gbc_exit.gridy = 4;
		contentPane.add(exit, gbc_exit);
		exit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "swap":
			String temp = field1.getText();
			field1.setText(field2.getText());
			field2.setText(temp);
			break;
		case "clear":
			field1.setText("");
			field2.setText("");
			break;
		case "exit":
			this.dispose();
		}
	}
}
