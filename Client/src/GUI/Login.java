package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JPanel {
	private JTextField Usuario;
	private JTextField pass;
	Registrarse regis;


	/**
	 * Create the panel.
	 */
	public Login(GUI gui) {
		setLayout(null);
		
		JButton btnIniciarSesion = new JButton("INICIAR SESION");
		btnIniciarSesion.setBackground(Color.LIGHT_GRAY);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIniciarSesion.setForeground(Color.BLACK);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.SetCredencialesInicio(Usuario.getText(), pass.getText());

			}
		});
		btnIniciarSesion.setBounds(258, 208, 145, 31);
		add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gui.registrar();
				//setVisible(false);
				
			}
		});
		btnRegistrarse.setBackground(Color.LIGHT_GRAY);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRegistrarse.setBounds(71, 208, 131, 31);
		add(btnRegistrarse);
		
		JLabel lblNewLabel = new JLabel("USUARIO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(93, 48, 79, 19);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CONTRASE\u00D1A");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(93, 117, 114, 19);
		add(lblNewLabel_1);
		
		Usuario = new JTextField();
		Usuario.setBackground(Color.LIGHT_GRAY);
		Usuario.setBounds(282, 49, 86, 20);
		add(Usuario);
		Usuario.setColumns(10);
		
		pass = new JTextField();
		pass.setBackground(Color.LIGHT_GRAY);
		pass.setBounds(282, 118, 86, 20);
		add(pass);
		pass.setColumns(10);

	}
	
	public JTextField getUsuario() {
		return Usuario;
	}
	
	public JTextField getPass() {
		return pass;
	}
	
}
