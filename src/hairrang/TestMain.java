package hairrang;

import java.awt.EventQueue;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class TestMain {
	public static void main(String[] args) {
		
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HairshopManagementProgram frame = new HairshopManagementProgram();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
