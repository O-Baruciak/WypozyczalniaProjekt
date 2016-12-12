package projekt;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Wypo¿yczalnia");
		frame.setBounds(100, 100, 457, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOskarBaruciak = new JLabel("Oskar Baruciak");
		lblOskarBaruciak.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOskarBaruciak.setBounds(10, 36, 160, 30);
		frame.getContentPane().add(lblOskarBaruciak);
		
		JLabel lblProjektWypoyczalniWideo = new JLabel("Projekt Wypo\u017Cyczalni Wideo");
		lblProjektWypoyczalniWideo.setFont(new Font("Arial", Font.PLAIN, 18));
		lblProjektWypoyczalniWideo.setBounds(10, 132, 233, 30);
		frame.getContentPane().add(lblProjektWypoyczalniWideo);
		
		JLabel lblPgsSoftware = new JLabel("PGS Software grudzie\u0144 2016");
		lblPgsSoftware.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPgsSoftware.setBounds(10, 11, 153, 14);
		frame.getContentPane().add(lblPgsSoftware);
		
		JLabel lblPraktyki = new JLabel("Praktyki Java 2016");
		lblPraktyki.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPraktyki.setBounds(10, 94, 220, 30);
		frame.getContentPane().add(lblPraktyki);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Wypozyczalnia wypozyczalnia = new Wypozyczalnia();
				wypozyczalnia.setVisible(true);
			}
		});
		btnStart.setBounds(165, 203, 100, 50);
		frame.getContentPane().add(btnStart);
	}
}
