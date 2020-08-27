package hairrang.component;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestSearchTable;

public class GuestSearch extends JPanel implements ActionListener{
	private GuestSearchTable table;
	private JPanel panel;
	private GuestSearchPanel pGuest;
	private JPanel pBtn;
	private JButton btnCancel;
	private JButton btnInfo;
	private JButton btnOrder;
	private JPanel pTable;
	private JScrollPane scrollPane;
	
	private GuestService gService;
	private ArrayList<Guest> guestList;
	
	private GuestOrderInfo orderInfo;

	/**
	 * Create the panel.
	 */
	public GuestSearch() {
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		initComponents();

	}

	private void initComponents() {
		setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 700, 540);
		add(panel);
		panel.setLayout(null);
		
		pGuest = new GuestSearchPanel();
		pGuest.setBounds(0, 0, 700, 190);
		//pGuest.setMainFrame(this);
		panel.add(pGuest);
		
		pBtn = new JPanel();
		pBtn.setBounds(0, 190, 700, 50);
		panel.add(pBtn);
		pBtn.setLayout(null);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(330, 11, 90, 25);
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
		
		btnInfo = new JButton("이용 내역");
		btnInfo.setBounds(434, 11, 90, 25);
		btnInfo.addActionListener(this);
		pBtn.add(btnInfo);
		
		btnOrder = new JButton("주문");
		btnOrder.setBounds(539, 11, 90, 25);
		btnOrder.addActionListener(this);
		pBtn.add(btnOrder);
		
		pTable = new JPanel();
		pTable.setBounds(0, 240, 700, 300);
		panel.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		pTable.add(scrollPane);
		
		table = new GuestSearchTable();
		scrollPane.setViewportView(table);
		table.setItems(guestList);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Guest selectedGuest = getSelectedGuest();
					pGuest.setGuest(selectedGuest);

				}
			}
		});

	}
	
	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		return guestList.get(selectedRow);
	}

	public void searchResult(String search) {
		guestList = (ArrayList<Guest>) gService.searchGuestByName(new Guest(search));
		guestList.stream().forEach(System.out::println);
		table.setItems(guestList);
	}
	
//버튼 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnCancel) {
				btnCancelActionPerformed(e);
			}
			if (e.getSource() == btnInfo) {
				btnInfoActionPerformed(e);
			}

			if (e.getSource() == btnOrder) {
				btnOrderActionPerformed(e);
			}
		}

		private void btnCancelActionPerformed(ActionEvent e) {
			pGuest.clearTf();

		}

		private void btnInfoActionPerformed(ActionEvent e) {
			System.out.println("내역");
			// 서비스추가하고 tableset
			infoDialog();
		}
		
		private void btnOrderActionPerformed(ActionEvent e) {
			System.out.println("해당 고객정보와 함께 주문창으로 넘어가기");

		}

		private void infoDialog() {
			orderInfo = new GuestOrderInfo();
			orderInfo.setBounds(150, 200, 600, 400);
			orderInfo.setTitle("이용내역");
			table = new GuestSearchTable();
			
			
//			JDialog info = new JDialog();
			// info.setSize(new Dimension(600,300));
//			info.setBounds(150, 200, 600, 400);
//			info.setTitle("이용내역");
//			info.setVisible(true);
			


		}
}
