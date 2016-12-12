package projekt;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.io.IOException;

public class Wypozyczalnia extends JFrame{

	private JPanel contentPane;
	private JTextField textImie;
	private JTextField textNazwisko;
	private JTextField textPESEL;
	private JTextField textTytul;
	private JTextField textGatunek;
	private JTable tableKlienci;
	private JTable tableFilmy;
	private JTextField textIDFilm;
	
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_URL = "jdbc:sqlite:src/projekt/ListSQL.sqlite";
	
	Connection connection = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wypozyczalnia frame = new Wypozyczalnia();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addClient(){
			
		try{
			float isNumber = Float.parseFloat(textPESEL.getText());
			String peselLenght = textPESEL.getText();
			IOException e = new IOException();
			if (peselLenght.length()!=11){
				throw e;
			}
			String query="insert into KlienciLista (Imie, Nazwisko, PESEL) values (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, textImie.getText());
			pst.setString(2, textNazwisko.getText());
			pst.setString(3, textPESEL.getText());
			pst.execute();
			pst.close();
		
			JOptionPane.showMessageDialog(null, "Dodano!");
		
		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Osoba o takim PESEL-u ju¿ istnieje!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "PESEL musi zawieraæ same cyfry!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "PESEL powinien miec 11 cyfr!");
		}
	}
	
	public void updateClient(){
		try{
			String query="Update KlienciLista set Imie='"+textImie.getText()+"' , Nazwisko='"+textNazwisko.getText()+"' , PESEL='"+textPESEL.getText()+"' where PESEL='"+textPESEL.getText()+"' ";
			PreparedStatement pst = connection.prepareStatement(query);		
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "Zaktualizowano!");
		
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Problem z baza danych!");
		}
	}
	
	public void deleteClient(){
		try{
			float isNumber = Float.parseFloat(textPESEL.getText());
			String peselLenght = textPESEL.getText();
			IOException e = new IOException();
			if (peselLenght.length()!=11){
				throw e;
			}
			String query = "delete from KlienciLista where PESEL='"+textPESEL.getText()+"' "  ;
			PreparedStatement pst = connection.prepareStatement(query);			
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "Usuniêto!");
		} catch (SQLException ex){
			ex.printStackTrace();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "PESEL musi zawieraæ same cyfry!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "PESEL powinien miec 11 cyfr!");
		}
	}
	
	public void addMovie(){
		try{
			String str = textTytul.getText();
			IOException e = new IOException();
			if(str.isEmpty()){
				throw e;
			}
			String queryF = "insert into FilmyLista (Tytu³, Gatunek) values (?,?)";
			PreparedStatement pstF = connection.prepareStatement(queryF);				
			pstF.setString(1, textTytul.getText());
			pstF.setString(2, textGatunek.getText());	
			pstF.execute();
			pstF.close();
				
			JOptionPane.showMessageDialog(null, "Zapisano!");

		} catch (SQLException ee){
			JOptionPane.showMessageDialog(null, "Film o podanym tytule juz istenieje!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Niepoprawna iloœæ sztuk!");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Podaj tytu³!");
		}
	}
	
	public void updateMovie(){
		try{
			String queryF="Update FilmyLista set Tytu³='"+textTytul.getText()+"' , Gatunek='"+textGatunek.getText()+"' where ID='"+textIDFilm.getText()+"' ";
			PreparedStatement pstF = connection.prepareStatement(queryF);
			pstF.execute();
			pstF.close();
			
			JOptionPane.showMessageDialog(null, "Zaktualizowano!");		
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Film o podanym tytule juz istenieje!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Niepoprawna iloœæ sztuk!");
		}
	}
	
	public void deleteMovie(){
		try{
			float isNumber = Float.parseFloat(textIDFilm.getText());
			String query = "delete from FilmyLista where ID='"+textIDFilm.getText()+"' "  ;
			PreparedStatement pst = connection.prepareStatement(query);			
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "Usuniêto!");
		} catch (SQLException ex){
			JOptionPane.showMessageDialog(null, "Problem z baz¹ danych!");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Z³e ID!");
		}
	}
	
	public void refreshTab(){
		try{
			String query = "select * from KlienciLista";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableKlienci.setModel(DbUtils.resultSetToTableModel(rs));
			pst.execute();
			rs.close();	
			String queryF = "select * from FilmyLista";
			PreparedStatement pstF = connection.prepareStatement(queryF);
			ResultSet rsF = pstF.executeQuery();
			tableFilmy.setModel(DbUtils.resultSetToTableModel(rsF));;
			pstF.execute();
			rsF.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Wypozyczalnia() {
		setTitle("Wypo\u017Cyczalnia");
		connection = sqlList.connSQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 440);
		contentPane.add(tabbedPane);
		
		JPanel pKlienci = new JPanel();
		tabbedPane.addTab("Kilenci", null, pKlienci, null);
		pKlienci.setLayout(null);
		
		JLabel lblImie = new JLabel("Imi\u0119");
		lblImie.setBounds(10, 11, 46, 14);
		pKlienci.add(lblImie);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(10, 36, 58, 14);
		pKlienci.add(lblNazwisko);
		
		JLabel lblPesel = new JLabel("PESEL");
		lblPesel.setBounds(10, 61, 46, 14);
		pKlienci.add(lblPesel);
		
		textImie = new JTextField();
		textImie.setBounds(78, 8, 108, 20);
		pKlienci.add(textImie);
		textImie.setColumns(10);
		
		textNazwisko = new JTextField();
		textNazwisko.setBounds(78, 33, 108, 20);
		pKlienci.add(textNazwisko);
		textNazwisko.setColumns(10);
		
		textPESEL = new JTextField();
		textPESEL.setBounds(78, 58, 108, 20);
		pKlienci.add(textPESEL);
		textPESEL.setColumns(10);
		
		JScrollPane scrollPaneK = new JScrollPane();
		scrollPaneK.setBounds(248, 11, 501, 390);
		pKlienci.add(scrollPaneK);
		
		JPanel pFilmy = new JPanel();
		tabbedPane.addTab("Filmy", null, pFilmy, null);
		pFilmy.setLayout(null);
		
		JLabel lblTytu = new JLabel("Tytu\u0142");
		lblTytu.setBounds(10, 11, 46, 14);
		pFilmy.add(lblTytu);
		
		JLabel lblGatunek = new JLabel("Gatunek");
		lblGatunek.setBounds(10, 36, 63, 14);
		pFilmy.add(lblGatunek);
		
		textTytul = new JTextField();
		textTytul.setBounds(76, 8, 109, 20);
		pFilmy.add(textTytul);
		textTytul.setColumns(10);
		
		textGatunek = new JTextField();
		textGatunek.setBounds(76, 33, 109, 20);
		pFilmy.add(textGatunek);
		textGatunek.setColumns(10);
		
		tableKlienci = new JTable();		
		scrollPaneK.setViewportView(tableKlienci);
		
		JScrollPane scrollPaneF = new JScrollPane();
		scrollPaneF.setBounds(250, 11, 499, 390);
		pFilmy.add(scrollPaneF);
	        
		tableFilmy = new JTable();
		scrollPaneF.setViewportView(tableFilmy);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 115, 46, 14);
		pFilmy.add(lblId);
		
		textIDFilm = new JTextField();
		textIDFilm.setBounds(76, 112, 109, 20);
		pFilmy.add(textIDFilm);
		textIDFilm.setColumns(10);
		
		
		JButton btnZapiszK = new JButton("Zapisz");
		btnZapiszK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				addClient();
				refreshTab();
			}

		});	
	
		btnZapiszK.setBounds(10, 110, 89, 23);
		pKlienci.add(btnZapiszK);
		
		JButton btnAktualizujK = new JButton("Aktualizuj");
		btnAktualizujK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateClient();
				refreshTab();
			}
		});
		btnAktualizujK.setBounds(10, 144, 89, 23);
		pKlienci.add(btnAktualizujK);
		
		JButton btnUsunK = new JButton("Usu\u0144");
		btnUsunK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClient();
				refreshTab();
			}
		});
		btnUsunK.setBounds(10, 178, 89, 23);
		pKlienci.add(btnUsunK);
		
		JToggleButton btnWypozycz = new JToggleButton("Wypo\u017Cycz");
		btnWypozycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Wypozycz window = new Wypozycz();
					window.setVisible(true);
					
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnWypozycz.setBounds(128, 110, 95, 23);
		pKlienci.add(btnWypozycz);
		
		JButton btnWypozyczenia = new JButton("Wypo\u017Cyczone filmy");
		btnWypozyczenia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ListaWypozyczen window = new ListaWypozyczen();
					window.setVisible(true);
					
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		btnWypozyczenia.setBounds(8, 277, 230, 23);
		pKlienci.add(btnWypozyczenia);
		
		JButton btnZapiszF = new JButton("Zapisz");
		btnZapiszF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMovie();
				refreshTab();
			}
		});
		btnZapiszF.setBounds(10, 143, 89, 23);
		pFilmy.add(btnZapiszF);
		
		JButton btnAktualizujF = new JButton("Aktualizuj");
		btnAktualizujF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateMovie();
				refreshTab();
			}
		});
		btnAktualizujF.setBounds(10, 177, 89, 23);
		pFilmy.add(btnAktualizujF);
		
		JButton btnUsunF = new JButton("Usu\u0144");
		btnUsunF.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			deleteMovie();
			refreshTab();
		}
					
	});
		btnUsunF.setBounds(10, 211, 89, 23);
		pFilmy.add(btnUsunF);
		
		refreshTab();
	}
}
