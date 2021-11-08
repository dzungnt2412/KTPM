import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private String header[] = {"ID", "Username", "Password"};
	private DefaultTableModel tblModel = new DefaultTableModel(header, 0);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table frame = new Table();
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
	public Table() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 60, 321, 144);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		PreparedStatement st;
		ResultSet rs;
		
		String query = "SELECT * FROM user;";
		
		try {
			st = MySQL.getConnection().prepareStatement(query);
			
			rs = st.executeQuery();
			
			 Vector data = null;
			 tblModel.setRowCount(0);
			 
			 if (rs.isBeforeFirst() == false) {
			   JOptionPane.showMessageDialog(null, "Record not found", "Error", 2);
			 }
			 
			table = new JTable();
			 while (rs.next()) {
				data = new Vector();
				data.add(rs.getInt("ID"));
				data.add(rs.getString("userName"));
				data.add(rs.getString("passwd"));
				tblModel.addRow(data);
			 }

			table.setModel(tblModel);
				
			
		} catch (SQLException e1) {
			Logger.getLogger("Table ->" + Table.class.getName()).log(Level.SEVERE, null, e1);
		}
		

		scrollPane.setViewportView(table);
	}
}
