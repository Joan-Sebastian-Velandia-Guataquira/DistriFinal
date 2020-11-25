package GUI;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.util.List;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Transaccion extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = -2143470674962955489L;

	/**
	 * Create the panel.
	 */
	public Transaccion(GUI gui, List<Integer> Vacunas) {
		setLayout(null);
		
		JSpinner Laboratorio1 = new JSpinner();
		Laboratorio1.setModel(new SpinnerNumberModel(Vacunas.get(0).intValue(),null,null,1));
		Laboratorio1.setBounds(283, 75, 61, 20);
		add(Laboratorio1);
		
		JLabel lblNewLabel_1 = new JLabel("Laboratorio 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(57, 71, 109, 25);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Laboratorio 2");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(57, 135, 109, 20);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Laboratorio 3");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(57, 202, 111, 20);
		add(lblNewLabel_3);
		
		JSpinner Laboratorio2 = new JSpinner();
		Laboratorio1.setModel(new SpinnerNumberModel(Vacunas.get(1).intValue(),null,null,1));
		Laboratorio2.setBounds(283, 137, 61, 20);
		add(Laboratorio2);
		
		JSpinner Laboratorio3 = new JSpinner();
		Laboratorio1.setModel(new SpinnerNumberModel(Vacunas.get(2).intValue(),null,null,1));
		Laboratorio3.setBounds(283, 204, 61, 20);
		add(Laboratorio3);
		
		JLabel lblNewLabel = new JLabel("VACUNAS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(187, 23, 98, 20);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Generar Transacci\u00F3n");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.datosTransaccion(Laboratorio1.getValue().toString(), Laboratorio2.getValue().toString(), Laboratorio3.getValue().toString(),Vacunas);
			}
		});
		btnNewButton.setBounds(263, 258, 165, 31);
		add(btnNewButton);

	}
}
