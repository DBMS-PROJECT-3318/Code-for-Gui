import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AptBasePage extends JFrame {

	private JPanel contentPane;
	Connection conn;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AptBasePage frame = new AptBasePage();
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
	public AptBasePage() {
		conn = SqliteConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel AccLabel = new JLabel("Account : ");
		AccLabel.setBounds(47, 12, 70, 15);
		contentPane.add(AccLabel);
		
		String name = "";
		try {
			String query="SELECT UserName FROM User WHERE Logins = 1";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				name = rs.getString(1);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		JLabel CurrentUser = new JLabel(name);
		CurrentUser.setBounds(119, 12, 144, 15);
		contentPane.add(CurrentUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(407, 68, 452, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}
}
