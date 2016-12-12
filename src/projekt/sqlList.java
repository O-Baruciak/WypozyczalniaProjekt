package projekt;
import java.sql.*;
import javax.swing.*;

public class sqlList {

	Connection conn = null;
	public static Connection connSQL(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src/projekt/ListSQL.sqlite" );
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Brak po³¹czenia z baz¹");
			return null;
		}
	}
}
