package hairrang.component;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.activation.MailcapCommandMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hairrang.Configuration;
import hairrang.HairshopManagementProgram;
import hairrang.dto.Guest;
import hairrang.dto.Sales;
import hairrang.service.GuestService;
import hairrang.service.SalesService;
import hairrang.table.GuestSearchTable;

public class GuestSearch extends JPanel implements ActionListener {
	private GuestSearchPanel pGuest;
	private JPanel pBtn;
	private JButton btnCancel;
	private JButton btnInfo;
	private JButton btnOrder;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private GuestSearchTable table;
	private GuestService gService;
	private SalesService sService;
	private ArrayList<Guest> guestList;
	private ArrayList<Sales> salesList;
	private GuestOrderInfo orderInfo;
	private HairshopManagementProgram program;
	

	public GuestSearch() {
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();

		sService = new SalesService();
		salesList = (ArrayList<Sales>) sService.selectSalesByAll();

		initComponents();
	}


	private void initComponents() {
		setLayout(null);

		pGuest = new GuestSearchPanel();
		pGuest.setBounds(0, 0, 700, 190);
		add(pGuest);
		pGuest.setGuestSearch(this);
		// pGuest(GuestSearchPanel)의 부모인 GuestSearch는 나야~~~!

		pBtn = new JPanel();
		pBtn.setBounds(0, 190, 700, 40);
		add(pBtn);
		pBtn.setLayout(null);

		btnCancel = new JButton("취소");
		btnCancel.setBounds(300, 0, Configuration.btnDim.width, Configuration.btnDim.height );
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

		btnInfo = new JButton("이용 내역");
		btnInfo.setBounds(410, 0, Configuration.btnDim.width, Configuration.btnDim.height);
		btnInfo.addActionListener(this);
		pBtn.add(btnInfo);

		btnOrder = new JButton("주문");
		btnOrder.setBounds(520, 0, Configuration.btnDim.width, Configuration.btnDim.height);
		btnOrder.addActionListener(this);
		pBtn.add(btnOrder);

		pTable = new JPanel();
		pTable.setBounds(0, 230, 700, 310);
		add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new GuestSearchTable();
		scrollPane.setViewportView(table);
		
		guestList = (ArrayList<Guest>) gService.getGuestList();
		table.setItems(guestList);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					getSelectedGuest();
				}
			}
		});

	}

	public GuestOrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(GuestOrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public HairshopManagementProgram getProgram() {
		return program;
	}

	public void setProgram(HairshopManagementProgram program) {
		this.program = program;
	}

	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		int no = (int) table.getValueAt(selectedRow, 0);
		Guest guest = gService.selectGuestByNo(new Guest(no));
		pGuest.setGuest(guest);
		return guest;
	}

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
		guestList = (ArrayList<Guest>) gService.getGuestList();
		table.setItems(guestList);

	}

	private void btnInfoActionPerformed(ActionEvent e) {
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "고객을 선택하세요");
			return;
		}
		
		orderInfo = new GuestOrderInfo();
		orderInfo.setBounds(200, 200, 550, 480);
		orderInfo.setTitle("이용내역");
		orderInfo.setVisible(true);

		// String guestName = pGuest.tfName().trim();
		// String guestNo = pGuest.tfNo().trim();

		Guest guest = getSelectedGuest();
		int guestNo = guest.getGuestNo();
		String guestName = guest.getGuestName();
		orderInfo.selectGuest(guestNo, guestName);

	}

	//메인프로그램에서 주문눌렀을때
	private void btnOrderActionPerformed(ActionEvent e) {
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "고객을 선택하세요");
			return;
		}
		
		Guest guest = getSelectedGuest();
		int no = guest.getGuestNo();
		String name = guest.getGuestName();
		program.getP3().getSalesPanel().setGuest(no, name);
		
		program.switchPanel(2);
		
	}

	public void searchResult(String search) {
		
		ArrayList<Guest> result = (ArrayList<Guest>) gService.searchGuestByName(new Guest(search));
		// result.stream().forEach(System.out::println);
		table.setItems(result);

	}
	
	public void listUpdate() { //외부호출
		guestList = (ArrayList<Guest>) gService.getGuestList();
		table.setItems(guestList);
	}
	
	
}
