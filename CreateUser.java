import java.awt.EventQueue;
import java.sql.*;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateUser extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	Connection conn1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
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
	public CreateUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		conn1 = SqliteConnect.dbConnector();
		
		
		JLabel lblNewLabel = new JLabel("Create a Username and Password");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(81, 12, 281, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Username");
		lblNewLabel_1.setBounds(12, 74, 131, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setBounds(12, 124, 131, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(163, 72, 163, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 122, 165, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCreateUser = new JButton("Create Account");
		btnCreateUser.setBounds(144, 185, 182, 25);
		contentPane.add(btnCreateUser);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(176, 222, 117, 25);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Going Back!");
				dispose();
				LoginGui LG2 = new LoginGui();
				LG2.main(null);
			}
		});
		
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM User WHERE UserName = ?";
					PreparedStatement pst=conn1.prepareStatement(query);
					pst.setString(1,textField.getText());
					ResultSet rs=pst.executeQuery();
					int count =0;
					while(rs.next()) {
						count = count + 1;
					}
					if(count == 1) {
						JOptionPane.showMessageDialog(null, "Username already exists!!");	
					}
					else {
						String query2 = "INSERT INTO User (UserName,UserPass) Values(?,?)";
						PreparedStatement pst2 = conn1.prepareStatement(query2);
						pst2.setString(1, textField.getText());
						pst2.setString(2, textField_1.getText());
						pst2.execute();
						JOptionPane.showMessageDialog(null, "Account Created!");
						pst2.close();
						dispose();
						LoginGui Lg = new LoginGui();
						Lg.main(null);
						
					}
					rs.close();
					pst.close();
				} catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
					
				}
			}
		});
		
		
	}
}
