package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import EPS.ControladorEPS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class GUI extends JFrame {
	
	private Login login;
	private String pass;
	private String user;
	private ControladorEPS control;
	private Registrarse regis;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GUI(ControladorEPS control) {
		this.control= control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		login = new Login(this);
		contentPane = login;
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void SetCredencialesInicio(String user, String pass) {
		this.pass = pass;
		this.user = user;
		control.ingresarSistemas(user, pass);
	}
	
	public void SetCredencialesRegis(String user, String pass) {
		this.pass = pass;
		this.user = user;
		System.out.println(pass);
		control.registrarse(user, pass);
	}
	
	public void registrar() {
		regis = new Registrarse(this);
		contentPane.removeAll();
		contentPane = regis;
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
