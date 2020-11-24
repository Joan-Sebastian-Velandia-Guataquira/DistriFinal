package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class Login extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField Usuario;
	private JTextField Contrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton BotonIniciarSesion = new JButton("INICIAR SESION");
		BotonIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 12));
		BotonIniciarSesion.setBackground(Color.LIGHT_GRAY);
		BotonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		BotonIniciarSesion.setBounds(260, 196, 144, 34);
		contentPane.add(BotonIniciarSesion);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(104, 68, 86, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(104, 124, 86, 23);
		contentPane.add(lblNewLabel_1);

		Usuario = new JTextField();
		Usuario.setBackground(Color.LIGHT_GRAY);
		Usuario.setBounds(240, 67, 86, 20);
		contentPane.add(Usuario);
		Usuario.setColumns(10);

		Contrasena = new JTextField();
		Contrasena.setBackground(Color.LIGHT_GRAY);
		Contrasena.setBounds(240, 127, 86, 20);
		contentPane.add(Contrasena);
		Contrasena.setColumns(10);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getUsuario() {
		return Usuario;
	}

	public void setUsuario(JTextField usuario) {
		Usuario = usuario;
	}

	public JTextField getContrasena() {
		return Contrasena;
	}

	public void setContrasena(JTextField contrasena) {
		Contrasena = contrasena;
	}

}