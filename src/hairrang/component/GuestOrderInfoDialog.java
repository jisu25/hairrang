package hairrang.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.dto.Guest;
import hairrang.dto.Sales;
import hairrang.service.GuestService;
import hairrang.service.SalesService;
import hairrang.table.GuestOrderInfoTable;

public class GuestOrderInfoDialog extends JDialog implements ActionListener {
	private JLabel lblNo;
	private JLabel lblName;
	private JLabel lblSetName;
	private ArrayList<Sales> salesList;
	private SalesService sService;
	
	//테스트
	private GuestService gService;
	private ArrayList<Guest> guestList;
	
	//
	
	private JButton btnClose;
	private JPanel panel;
	private JPanel pGuest;
	private JPanel pTable;
	private JScrollPane scrollPane;
	
	private JLabel lblSetNo;
	private GuestOrderInfoTable table;

	public GuestOrderInfoDialog() {
		
		sService = new SalesService();
		salesList = (ArrayList<Sales>) sService.selectSalesByAll();
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 500, 450);
		getContentPane().add(panel);
		panel.setLayout(null);

		pGuest = new JPanel();
		pGuest.setBounds(0, 0, 500, 158);
		panel.add(pGuest);
		pGuest.setLayout(null);
		

		lblNo = new JLabel("고객 번호: ");
		lblNo.setBounds(159, 52, 70, 16);
		pGuest.add(lblNo);

		lblSetNo = new JLabel("");
		lblSetNo.setBounds(253, 52, 70, 16);
		pGuest.add(lblSetNo);

		lblName = new JLabel("고객명 : ");
		lblName.setBounds(159, 87, 70, 16);
		pGuest.add(lblName);

		lblSetName = new JLabel("");
		lblSetName.setBounds(253, 87, 70, 16);
		pGuest.add(lblSetName);
		
		btnClose = new JButton("닫기");
		btnClose.setBounds(385, 108, 101, 25);
		btnClose.addActionListener(this);
		pGuest.add(btnClose);

		pTable = new JPanel();
		pTable.setBounds(0, 180, 500, 270);
		panel.add(pTable);
		pTable.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 480, 248);
		pTable.add(scrollPane);
		
		table = new GuestOrderInfoTable();
		scrollPane.setViewportView(table);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			this.setVisible(false);
		}
	}

	public void selectGuest(int guestNo, String guestName) {
		salesList = (ArrayList<Sales>) sService.selectSalesByGuestNo(new Sales(guestNo));
		
		lblSetNo.setText(String.valueOf(guestNo));
		lblSetName.setText(guestName);
		if(salesList == null) {
			JOptionPane.showMessageDialog(null, "내역없음");
			this.dispose();
		}
		table.setItems(salesList);
		
		//salesList.stream().forEach(System.out::println);
		
	}
}
