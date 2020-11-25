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
	private Transaccion tran;

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
	
	public void datosTransaccion(String lab1, String lab2, String lab3, List<Integer> vacunas) {
		List<String> cantVacunas = new ArrayList<String>();
		cantVacunas.add(0, lab1);
		cantVacunas.add(1, lab2);
		cantVacunas.add(2, lab3);
		control.ValidarCambios(cantVacunas, vacunas);
	}
	
	public void Transaccion(List<Integer> vacunas) {
		List<Integer> vacunasStr = new ArrayList<>();
		vacunasStr.add(0,vacunas.get(0).intValue());
		vacunasStr.add(1,vacunas.get(1).intValue());
		vacunasStr.add(2,vacunas.get(2).intValue());
		tran = new Transaccion(this, vacunasStr);
		contentPane.removeAll();
		contentPane = tran;
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}

}
