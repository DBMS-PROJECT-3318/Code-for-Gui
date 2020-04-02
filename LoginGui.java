import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginGui {

	private JFrame frame;
	
	Connection conn = null;
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui window = new LoginGui();
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
	public LoginGui() {
		initialize();
		conn = SqliteConnect.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query="SELECT * FROM User WHERE UserName = ? and UserPass = ?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textField.getText());
					pst.setString(2,textField_1.getText());
					
					ResultSet rs=pst.executeQuery();
					int count =0;
					while(rs.next()) {
						count = count + 1;
					}
					if(count ==1) {
						JOptionPane.showMessageDialog(null, "Username and password are correct");	
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Username and/or password");
					}
					rs.close();
					pst.close();
				}catch(Exception e) {
					
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 325, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -76, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 80, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 167, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, textField);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setText("Username ");
		springLayout.putConstraint(SpringLayout.NORTH, txtrUsername, 0, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, txtrUsername, 68, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(txtrUsername);
		
		JTextArea txtrPassword = new JTextArea();
		springLayout.putConstraint(SpringLayout.NORTH, txtrPassword, 27, SpringLayout.SOUTH, txtrUsername);
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -2, SpringLayout.NORTH, txtrPassword);
		springLayout.putConstraint(SpringLayout.EAST, txtrPassword, 0, SpringLayout.EAST, txtrUsername);
		txtrPassword.setText("Password");
		frame.getContentPane().add(txtrPassword);
		
		JTextArea txtrLogin = new JTextArea();
		txtrLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, txtrLogin, 26, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtrLogin, 199, SpringLayout.WEST, frame.getContentPane());
		txtrLogin.setText("LOGIN");
		frame.getContentPane().add(txtrLogin);
	}
}
