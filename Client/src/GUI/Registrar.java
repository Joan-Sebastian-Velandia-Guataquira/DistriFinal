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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Registrar extends JFrame {

	private JPanel contentPane;
	private JTextField Usuario;
	private JTextField Pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrar frame = new Registrar();
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
	public Registrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BotonRegistrarse = new JButton("REGISTRARSE");
		BotonRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 12));
		BotonRegistrarse.setBackground(Color.LIGHT_GRAY);
		BotonRegistrarse.setForeground(Color.BLACK);
		BotonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		BotonRegistrarse.setBounds(264, 193, 132, 35);
		contentPane.add(BotonRegistrarse);
		
		Usuario = new JTextField();
		Usuario.setBackground(Color.LIGHT_GRAY);
		Usuario.setBounds(224, 65, 86, 20);
		contentPane.add(Usuario);
		Usuario.setColumns(10);
		
		Pass = new JTextField();
		Pass.setBackground(Color.LIGHT_GRAY);
		Pass.setBounds(224, 126, 86, 20);
		contentPane.add(Pass);
		Pass.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(81, 122, 101, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(81, 61, 101, 29);
		contentPane.add(lblUsuario);
	}
}
