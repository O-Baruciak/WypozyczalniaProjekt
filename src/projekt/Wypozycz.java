package projekt;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Wypozycz extends JFrame {

	private JPanel contentPane;
	private JTextField textIdFilmu;
	
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:src/projekt/ListSQL.sqlite";

	Connection connection = null;
	private JLabel lblIdKlienta;
	private JTextField textIdKlienta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wypozycz frame = new Wypozycz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void rentMovie() {
		try{
			String query="insert into Wypozyczenia (ID_Klient, ID_Film) values (?,?)";
			PreparedStatement pst = connection.prepareStatement(query);				
			pst.setString(1, textIdKlienta.getText());
			pst.setString(2, textIdFilmu.getText());
			pst.execute();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Wypozycz() {
		setTitle("Wypo\u017Cycz");
		connection = sqlList.connSQL();
		setBounds(100, 100, 269, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdFilmu = new JLabel("ID Filmu");
		lblIdFilmu.setBounds(36, 44, 71, 14);
		contentPane.add(lblIdFilmu);
		
		JButton btnWypozycz = new JButton("Wypo\u017Cycz");
		btnWypozycz.setBounds(72, 89, 104, 23);
		btnWypozycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rentMovie();
			}
		});
		
		textIdFilmu = new JTextField();
		textIdFilmu.setBounds(117, 41, 86, 20);
		contentPane.add(textIdFilmu);
		textIdFilmu.setColumns(10);
		contentPane.add(btnWypozycz);
		
		lblIdKlienta = new JLabel("ID Klienta");
		lblIdKlienta.setBounds(36, 19, 71, 14);
		contentPane.add(lblIdKlienta);
		
		textIdKlienta = new JTextField();
		textIdKlienta.setBounds(117, 16, 86, 20);
		contentPane.add(textIdKlienta);
		textIdKlienta.setColumns(10);
	}

}
