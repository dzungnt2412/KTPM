import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp :");
		lblNewLabel.setBounds(57, 34, 104, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u :");
		lblMtKhu.setBounds(57, 77, 104, 14);
		contentPane.add(lblMtKhu);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(151, 31, 233, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogin = new JButton("\u0110\u0103ng nh\u1EADp");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement st;
				ResultSet rs;
				
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				
				String query = "SELECT * FROM user WHERE userName = ? AND passwd = ? ;";
				
				try {
					st = MySQL.getConnection().prepareStatement(query);
					st.setString(1, username);
					st.setString(2, password);
					
					rs = st.executeQuery();
					
					if(rs.next()) {
						Table tbl = new Table();
						tbl.setVisible(true);

						
						Login.this.dispose();
						
						
						
					
					}else {
						JOptionPane.showMessageDialog(null, "Invailid Username/ Password", "Login Error", 2);
					}
					
				} catch (SQLException e1) {
					Logger.getLogger("Login ->" + Login.class.getName()).log(Level.SEVERE, null, e1);
				}
				

			}
		});
		btnLogin.setBounds(174, 125, 89, 23);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(151, 74, 233, 20);
		contentPane.add(txtPassword);
	}
	
}
