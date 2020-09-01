package hairrang.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MailcapCommandMap;
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

public class GuestOrderInfo extends JDialog implements ActionListener {
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

	public GuestOrderInfo() {
		
		sService = new SalesService();
		salesList = (ArrayList<Sales>) sService.selectSalesByAll();
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 550, 450);
		getContentPane().add(panel);
		panel.setLayout(null);

		pGuest = new JPanel();
		pGuest.setBounds(0, 0, 550, 180);
		panel.add(pGuest);
		pGuest.setLayout(null);
		

		lblNo = new JLabel("고객 번호: ");
		lblNo.setBounds(184, 55, 70, 16);
		pGuest.add(lblNo);

		lblSetNo = new JLabel("");
		lblSetNo.setBounds(278, 55, 70, 16);
		pGuest.add(lblSetNo);

		lblName = new JLabel("고객명 : ");
		lblName.setBounds(184, 90, 70, 16);
		pGuest.add(lblName);

		lblSetName = new JLabel("");
		lblSetName.setBounds(278, 90, 70, 16);
		pGuest.add(lblSetName);
		
		btnClose = new JButton("닫기");
		btnClose.setBounds(415, 133, 101, 25);
		btnClose.addActionListener(this);
		pGuest.add(btnClose);

		pTable = new JPanel();
		pTable.setBounds(0, 180, 550, 270);
		panel.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
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
