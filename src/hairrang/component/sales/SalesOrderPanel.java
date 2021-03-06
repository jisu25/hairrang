package hairrang.component.sales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.TabableView;

import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;
import hairrang.service.EventService;
import hairrang.service.GuestService;
import hairrang.service.HairService;
import hairrang.service.SalesService;
import hairrang.table.HairItemTable;

@SuppressWarnings("serial")
public class SalesOrderPanel extends JPanel {
	private JTextField tfSalesNo;
	private JTextField tfSalesDay;
	private JTextField tfHairPrice;
	private JTextField tfGuestName;
	private JTextField tfGuestNo;
	private JTextField tfSale;

	private JCheckBox checkMember;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	HairService hairService = new HairService();
	EventService eventService = new EventService();
	SalesService salesService = new SalesService();
	GuestService guestService = new GuestService();
	private JComboBox<String> comboHair;
	private JComboBox<String> comboEvent;
	private HairItemTable htable = new HairItemTable();
	private JTextField tfSumPrice = new JTextField();
	private JTextField tfTotalPrice = new JTextField();
	private List<Hair> hairList = hairService.getHairList();
	private List<Event> eventList = eventService.getEventList();
	private List<Hair> itemList = new ArrayList<Hair>();
	

	public SalesOrderPanel() {
		
		setLayout(null);
		JLabel lblSalesNo = new JLabel("영업번호 :");
		lblSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesNo.setBounds(12, 47, 62, 25);
		add(lblSalesNo);

		tfSalesNo = new JTextField();
		tfSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesNo.setBounds(86, 49, 115, 21);
		tfSalesNo.setText(String.valueOf(salesService.getSalesNO()));
		add(tfSalesNo);
		tfSalesNo.setColumns(10);

		JLabel lblHairName = new JLabel("헤어명 :");
		lblHairName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHairName.setBounds(213, 47, 62, 25);
		add(lblHairName);

		JLabel lblSalesDay = new JLabel("영업일자 :");
		lblSalesDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesDay.setBounds(12, 84, 62, 25);
		add(lblSalesDay);

		Date today = new Date();
		tfSalesDay = new JTextField();
		tfSalesDay.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesDay.setColumns(10);
		tfSalesDay.setBounds(86, 84, 115, 21);
		tfSalesDay.setText(format.format(today));
		add(tfSalesDay);

		JLabel lblHairPrice = new JLabel("단가 :");
		lblHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblHairPrice.setBounds(213, 82, 62, 25);
//		add(lblHairPrice);

		tfHairPrice = new JTextField();
		tfHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		tfHairPrice.setColumns(10);
		tfHairPrice.setBounds(287, 84, 117, 21);
//		add(tfHairPrice);

		JLabel lblMemberCheck = new JLabel("회원체크 :");
		lblMemberCheck.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberCheck.setBounds(12, 119, 62, 25);
		add(lblMemberCheck);

		JLabel lblGuestName = new JLabel("고객명 :");
		lblGuestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestName.setBounds(12, 154, 62, 25);
		add(lblGuestName);

		tfGuestName = new JTextField();
		tfGuestName.setHorizontalAlignment(SwingConstants.CENTER);
		tfGuestName.setColumns(10);
		tfGuestName.setEditable(false);
		tfGuestName.setBounds(86, 156, 115, 21);

		add(tfGuestName);

		JLabel lblEventName = new JLabel("이벤트명 :");
		lblEventName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEventName.setBounds(213, 154, 62, 25);
		add(lblEventName);

		JLabel lblGuestNo = new JLabel("고객번호 :");
		lblGuestNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestNo.setBounds(12, 189, 62, 25);
		add(lblGuestNo);

		tfGuestNo = new JTextField();
		tfGuestNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfGuestNo.setColumns(10);
		tfGuestNo.setEditable(false);
		tfGuestNo.setBounds(86, 191, 115, 21);
		
		add(tfGuestNo);
		

		JLabel lblSale = new JLabel("할인율 :");
		lblSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblSale.setBounds(213, 189, 62, 25);
		add(lblSale);

		tfSale = new JTextField();
		tfSale.setHorizontalAlignment(SwingConstants.CENTER);
		tfSale.setColumns(10);
		tfSale.setBounds(287, 191, 117, 21);
		add(tfSale);

		checkMember = new JCheckBox("비회원");
		checkMember.addItemListener(itemlistener);
//		checkMember.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		checkMember.setBounds(96, 119, 120, 23);
		add(checkMember);

		comboHair = new JComboBox<String>();
		setHairDataModel();
		comboHair.setBounds(287, 49, 117, 21);
		comboHair.setSelectedIndex(-1);
		add(comboHair);

		comboEvent = new JComboBox<String>();
		setEventDateModel();
		comboEvent.setBounds(287, 156, 117, 21);
		comboEvent.setSelectedIndex(-1);
		add(comboEvent);

		comboHair.addActionListener(addActionlistener);
		comboEvent.addActionListener(addActionlistener);
		
		
	}

	public JCheckBox getCheckMember() {
		return checkMember;
	}


	public JComboBox<String> getComboHair() {
		return comboHair;
	}

	public void setHtable(HairItemTable htable) {
		this.htable = htable;
	}

	public void setTfSumPrice(JTextField tfSumPrice) {
		this.tfSumPrice = tfSumPrice;
	}

	public void setTfTotalPrice(JTextField tfTotalPrice) {
		this.tfTotalPrice = tfTotalPrice;
	}
	

	public JTextField getTfGuestNo() {
		return tfGuestNo;
	}

	public void setTfGuestNo(JTextField tfGuestNo) {
		this.tfGuestNo = tfGuestNo;
	}

	// 수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
	public List<Sales> getSales() {
		List<Sales> list = new ArrayList<Sales>();

		int no = salesService.getSalesNO();
		for (int j = 0; j < htable.getHairList().size(); j++) {
			Hair hair = null;
			int totalPrice;
			
			for (int i = 0; i < hairList.size(); i++) {
				if (hairList.get(i).getHairName().equals(comboHair.getSelectedItem().toString())) {
					hair = hairList.get(i);
				}
			}
			
			Event event = null;
			try{
			
			for (int i = 0; i < eventList.size(); i++) {
				if (eventList.get(i).getEventName().equals(comboEvent.getSelectedItem().toString())) {
					event = eventList.get(i);
				}
			}
			}catch (NullPointerException e) {
				event = new  Event(0, null, 0);
			}finally {

			Guest guest;
			if (tfGuestName.getText().trim().equals("")) {
				guest = new Guest("비회원");
			} else {
				int gNo = Integer.parseInt(tfGuestNo.getText().trim());
				guest = guestService.selectGuestByNo(new Guest(gNo));
			}

			Date day = new Date(System.currentTimeMillis());
			int res = (int) (hair.getPrice()*event.getSale());
			totalPrice = hair.getPrice() - res;
							
			list.add(new Sales(no++, day, guest, event, hair,totalPrice));
			System.out.println(no);
			}
		}
		return list;

	}

	// 수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
//	public void setSales(Guest guest) {
//		tfGuestName.setText(guest.getGuestName());
//		tfGuestNo.setText(String.valueOf(guest.getGuestNo()));
//
//	}
//	
//	// 고객검색에서 주문 눌렀을때 고객명, 고객번호 set
	public void setGuest(int no, String name) {
		
		tfGuestName.setText("");
		
		tfGuestNo.setEditable(true);
		tfGuestName.setEditable(true);
		
		Guest guest = guestService.selectGuestByNo(new Guest(no));
		tfGuestNo.setText(String.valueOf(guest.getGuestNo()));
		tfGuestName.setText(guest.getGuestName());
		System.out.println("메소드소환" + no + name);
		System.out.println("tf필드값 " + tfGuestNo.getText() + tfGuestName.getText());
		
		

	}

	private void setHairDataModel() {
		String[] items = new String[hairService.getHairNames().size()];
		
		int size = 0;
		for (String HairName : hairService.getHairNames()) {
			items[size++] = HairName;
		}
		;
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		comboHair.setModel(model);
		comboHair.setSelectedIndex(0);
	}

	ActionListener addActionlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comboHair) {
				addHairItemTable(e);
			}
			if (e.getSource() == comboEvent) {
				addEventItemResult(e);
			}
		}

	};

	private void setEventDateModel() {
		String[] items = new String[eventService.getEventNames().size()];

		int size = 0;
		for (String EventName : eventService.getEventNames()) {
			items[size++] = EventName;
		}
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		comboEvent.setModel(model);
		comboEvent.setSelectedIndex(0);

	}

	protected void addEventItemResult(ActionEvent e) {
		int selectIndex = comboEvent.getSelectedIndex();
		int selecth = comboHair.getSelectedIndex();
		System.out.println(selectIndex);
		if ( selecth == -1 || selecth == 0 || selectIndex == -1 || selectIndex == -1) {
//			JOptionPane.showMessageDialog(null, "상품을 선택해주세요");
			tfTotalPrice.setText(tfSumPrice.getText().trim());
			return;
		}
		System.out.println(selectIndex);
		eventList.get(comboEvent.getSelectedIndex());
		
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getEventName().equals(comboEvent.getSelectedItem().toString())) {
				String str = tfSumPrice.getText().trim();
				System.out.println(str);
				System.out.println(str.length());
				int sum = Integer.parseInt(str.substring(0, str.length() - 1));
				System.out.println(sum);
				int salePrice = (int) (sum * eventList.get(i).getSale());
				System.out.println(salePrice);
				int res = sum - salePrice;
				tfTotalPrice.setText(res + "원");
				tfSale.setText(String.format("%.0f", eventList.get(i).getSale() * 100) + "%");
				System.out.println(eventList.get(i).getSale() * 100 + "%");
			}
		}

	}

	private void addHairItemTable(ActionEvent e) {
		System.out.println(itemList);
		int selectIndex = comboHair.getSelectedIndex();
		
		System.out.println(e.getSource());
		System.out.println(e.getClass());
		if (selectIndex == -1 || selectIndex == 0) {
//			JOptionPane.showMessageDialog(null, "상품을 선택해주세요");
			return;
		}

		for (int i = 0; i < hairList.size(); i++) {
			if (hairList.get(i).getHairName().equals(comboHair.getSelectedItem().toString())) {
				itemList.add(hairList.get(i));
				
//				htable.addRow(hairList.get(i));
//				tfHairPrice.setText((hairList.get(i).getPrice()) + "원");
//				System.out.println(hairList.get(i));
//				System.out.println(comboHair.getSelectedItem().toString());
			}
		}
		htable.setCount(0);
		htable.setItems((ArrayList<Hair>) itemList);
		
		
		
		int sum = 0;
		for (int i = 0; i < htable.getHairList().size(); i++) {
			sum += htable.getHairList().get(i).getPrice();
		}
		tfSumPrice.setText(sum + "원");

		addEventItemResult(e);
		
	}

	ItemListener itemlistener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				tfGuestName.setText("");
				tfGuestNo.setText("");
				tfGuestName.setEditable(false);
				tfGuestNo.setEditable(false);
			} else {
				tfGuestName.setEditable(true);
				tfGuestNo.setEditable(true);
			}

		}
	};

	public void clearTf() {
		tfSalesNo.setText(String.valueOf(salesService.getSalesNO()));
		tfGuestName.setText("");
		tfGuestNo.setText("");
		tfHairPrice.setText("");
		tfSale.setText("");
		checkMember.setSelected(false);
		System.out.println("aaaaaaaaaaaaaaaaaa" + htable.getHairList().size());
//		for (int i = 0; i < htable.getHairList().size(); i++) {
//			htable.removeRow(i);
//		}
		for (int i = htable.getHairList().size() - 1; i > -1; i--) {
			htable.removeRow(i);
		}
		tfSumPrice.setText("");
		tfTotalPrice.setText("");
		
		setHairDataModel();	
		setEventDateModel();
		
	}

	public void subSumTotal(Hair hair) {
		if (comboEvent.getSelectedItem() == null) {
			String str = tfSumPrice.getText().trim();
			int sum = Integer.parseInt(str.substring(0, str.length() - 1));
			tfSumPrice.setText((sum - hair.getPrice()) + "원");
			tfTotalPrice.setText((sum - hair.getPrice()) + "원");
			return;
		}
		String str = tfSumPrice.getText().trim();
		int sum = Integer.parseInt(str.substring(0, str.length() - 1));
		tfSumPrice.setText((sum - hair.getPrice()) + "원");

		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getEventName().equals(comboEvent.getSelectedItem().toString())) {
				int salePrice = (int) ((sum - hair.getPrice()) * eventList.get(i).getSale());
				System.out.println(salePrice);
				int res = (sum - hair.getPrice()) - salePrice;
				tfTotalPrice.setText(res + "원");
				tfSale.setText(String.format("%.0f", eventList.get(i).getSale() * 100) + "%");
				System.out.println(eventList.get(i).getSale() * 100 + "%");
			}
		}

	}

	public List<Hair> getHairList() {
		return hairList;
	}

	public List<Hair> getItemList() {
		return itemList;
	}

	public void setItemList(List<Hair> itemList) {
		this.itemList = itemList;
	}
	
	
	

}
