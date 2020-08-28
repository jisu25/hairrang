package hairrang.component;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.peer.PopupMenuPeer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hairrang.dto.Event;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.dto.Sales;
import hairrang.service.HairService;
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
	SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
	HairService hairService = new HairService();
	private JComboBox<String> comboHair;
	private JComboBox<String> comboEvent;
	private HairItemTable htable = new HairItemTable();
	private JTextField tfSumPrice = new JTextField();
	private JTextField tfTotalPrice = new JTextField();
	private List<Hair> hairList = hairService.getHairList();

	public SalesOrderPanel() {
		setLayout(null);
		JLabel lblSalesNo = new JLabel("영업번호 :");
		lblSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesNo.setBounds(12, 47, 62, 25);
		add(lblSalesNo);

		tfSalesNo = new JTextField();
		tfSalesNo.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesNo.setBounds(86, 49, 115, 21);
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

		java.util.Date today = new java.util.Date();
		tfSalesDay = new JTextField();
		tfSalesDay.setHorizontalAlignment(SwingConstants.CENTER);
		tfSalesDay.setColumns(10);
		tfSalesDay.setBounds(86, 84, 115, 21);
		tfSalesDay.setText(format.format(today));
		add(tfSalesDay);

		JLabel lblHairPrice = new JLabel("단가 :");
		lblHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblHairPrice.setBounds(213, 82, 62, 25);
		add(lblHairPrice);

		tfHairPrice = new JTextField();
		tfHairPrice.setHorizontalAlignment(SwingConstants.CENTER);
		tfHairPrice.setColumns(10);
		tfHairPrice.setBounds(287, 84, 117, 21);
		add(tfHairPrice);

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
		setDateModel();
		comboHair.setBounds(287, 49, 117, 21);

		add(comboHair);

		comboEvent = new JComboBox<String>();
		comboEvent.setBounds(287, 156, 117, 21);
		add(comboEvent);
		
		
		comboHair.addActionListener(addActionlistener);
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
	// 수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
	public Sales getSales() {
		int no = Integer.parseInt(tfSalesNo.getText().trim());
		Date day = new Date(System.currentTimeMillis());
		Guest guest = new Guest();
		guest.setGuestName(tfGuestName.getText().trim());
		guest.setGuestNo(Integer.parseInt(tfGuestNo.getText().trim()));
		Event event = new Event();

		// event.setEventName();

		event.setSale(Double.parseDouble(tfSale.getText().trim()));
		Hair hair = new Hair();

		// hair.setHairName(tfHairName.getText().trim());

		hair.setPrice(Integer.parseInt(tfHairPrice.getText().trim()));
		return new Sales(no, day, guest, event, hair);
	}

	// 수정해야함!!!!!!!!!!!!!!!!!!!!!!!!!
	public void setSales(Sales sales) {
		// 영업번호는 지금 dbscript가 이상해서 회의해야함 그리고 impl에서 영업번호의 시퀀스 마지막을 가져와 +1을 시켜서 세팅
		tfSalesDay.setText(format.format(sales.getSalesDay()));

		// tfHairName.setText(sales.getHairNo().getHairName());

		tfHairPrice.setText(String.valueOf(sales.getHairNo().getPrice()));
		tfGuestName.setText(sales.getGuestNo().getGuestName());
		tfGuestNo.setText(String.valueOf(sales.getGuestNo().getGuestNo()));

		// tfEventName.setText(sales.getEventNo().getEventName());

		tfSale.setText(String.valueOf(sales.getEventNo().getSale()));

	}

	private void setDateModel() {
		String[] items = new String[hairService.getHairNames().size()];

		int size = 0;
		for (String HairName : hairService.getHairNames()) {
			items[size++] = HairName;
		}
		;
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		comboHair.setModel(model);
	}

	ActionListener addActionlistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comboHair) {
				addHairItemTable(e);
			}
		}

	};

	private void addHairItemTable(ActionEvent e) {
		int selectIndex = comboHair.getSelectedIndex();
//		System.out.println(selectIndex);
//		System.out.println(comboHair.getSelectedItem().toString());
		//System.out.println(comboHair);
		if (selectIndex == -1) {
			return; 
		}
		
		for (int i = 0; i < hairList.size(); i++) {
			if (hairList.get(i).getHairName().equals(comboHair.getSelectedItem().toString())) {
				htable.addRow(hairList.get(i));
				System.out.println(hairList.get(i));
				System.out.println(comboHair.getSelectedItem().toString());
				
			}
		}
		int sum = 0;
		for (int i = 0; i < htable.getHairList().size(); i++) {
			sum+=htable.getHairList().get(i).getPrice();
		}
		tfSumPrice.setText(sum+"");
		
	}

	ItemListener itemlistener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				tfGuestName.setEditable(false);
				tfGuestNo.setEditable(false);
			} else {
				tfGuestName.setEditable(true);
				tfGuestNo.setEditable(true);
			}

		}
	};

	public void clearTf() {
		tfGuestName.setText("");
		tfGuestNo.setText("");
		tfHairPrice.setText("");
		tfSale.setText("");
		checkMember.setSelected(false);
		comboHair.setSelectedIndex(-1);
		comboEvent.setSelectedIndex(-1);
		System.out.println("aaaaaaaaaaaaaaaaaa"+htable.getHairList().size());
//		for (int i = 0; i < htable.getHairList().size(); i++) {
//			htable.removeRow(i);
//		}
		for (int i =  htable.getHairList().size()-1; i >-1; i--) {
			htable.removeRow(i);
		}
		tfSumPrice.setText("");
		tfTotalPrice.setText("");
	}
	
	class CustomPopupMenu extends JPopupMenu{
		
	}
}
