package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registrarse extends JPanel {
	private JTextField user;
	private JTextField pass;

	/**
	 * Create the panel.
	 */
	public Registrarse(GUI gui) {
		setLayout(null);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.SetCredencialesRegis(user.getText(), pass.getText());
			}
		});
		btnRegistrarse.setBackground(Color.LIGHT_GRAY);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrarse.setForeground(new Color(0, 0, 0));
		btnRegistrarse.setBounds(266, 209, 127, 31);
		add(btnRegistrarse);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(87, 50, 77, 23);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(87, 138, 89, 14);
		add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBackground(Color.LIGHT_GRAY);
		user.setBounds(249, 53, 86, 20);
		add(user);
		user.setColumns(10);
		
		pass = new JTextField();
		pass.setBackground(Color.LIGHT_GRAY);
		pass.setBounds(249, 137, 86, 20);
		add(pass);
		pass.setColumns(10);

	}

}
