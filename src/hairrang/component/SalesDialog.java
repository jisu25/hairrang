package hairrang.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hairrang.HairshopManagementProgram;
import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestSearchTable;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalesDialog extends JDialog {

	private final JPanel SalesGuestSreach = new JPanel();
	private GuestSearchTable table;
	private GuestService service = new GuestService();
	private JButton btnOk;
	private HairshopManagementProgram program;

	public static void main(String[] args) {
		try {
			SalesDialog dialog = new SalesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SalesDialog() {
		setBounds(100, 100, 783, 567);
		getContentPane().setLayout(new BorderLayout());
		SalesGuestSreach.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(SalesGuestSreach, BorderLayout.CENTER);
		SalesGuestSreach.setLayout(new BoxLayout(SalesGuestSreach, BoxLayout.Y_AXIS));

		GuestSearchPanel guestSreachPanel = new GuestSearchPanel();
		SalesGuestSreach.add(guestSreachPanel);

		JPanel guestSreachTable = new JPanel();
		SalesGuestSreach.add(guestSreachTable);
		guestSreachTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		guestSreachTable.add(scrollPane, BorderLayout.CENTER);

		ArrayList<Guest> list = (ArrayList<Guest>) service.getGuestList();
		table = new GuestSearchTable();
		table.setItems(list);
		scrollPane.setViewportView(table);
		
		JPanel btnPanel = new JPanel();
		FlowLayout fl_btnPanel = (FlowLayout) btnPanel.getLayout();
		fl_btnPanel.setAlignment(FlowLayout.RIGHT);
		SalesGuestSreach.add(btnPanel);
		
		btnOk = new JButton("선택");
		btnOk.addActionListener(actionlistener);
		btnPanel.add(btnOk);

	}
	
	ActionListener actionlistener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnOk) {
				btnGuestOk();
			}
			
		}

		private void btnGuestOk() {
			int index = table.getSelectedRow();
			if (index == -1) {
				JOptionPane.showMessageDialog(null, "고객을 선택하세요");
				return;
			}

			Guest guest = getSelectedGuest();

			int no = guest.getGuestNo();
			String name = guest.getGuestName();

			program.getP3().getSalesPanel().setGuest(no, name);
			
		}
	};
	
	public Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		int no = (int) table.getValueAt(selectedRow, 0);
		Guest guest = service.selectGuestByNo(new Guest(no));
		// pGuest.setGuest(guest);
		return guest;
	}


}
