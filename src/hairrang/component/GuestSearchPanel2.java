package hairrang.component;

import java.util.ArrayList;

import javax.swing.JPanel;

import hairrang.dto.Guest;
import hairrang.service.GuestService;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuestSearchPanel2 extends JPanel implements ActionListener {
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private GuestSearch guestSearch;
	private JTextField tfSearch;
	private JComboBox<Object> comboBox;
	private JButton btnSearch;

	public GuestSearchPanel2() {
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		initComponents();
		
		
	}

	private void initComponents() {
		setLayout(null);
		
		comboBox = new JComboBox<Object>();
		comboBox.setBounds(140, 71, 74, 21);
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"고객명 ", "전화번호", "생년월일"}));
		add(comboBox);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(235, 71, 116, 21);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(372, 70, 97, 23);
		add(btnSearch);
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSearch) {
			btnSearchActionPerformed(e);
		}
	}
	
	private void btnSearchActionPerformed(ActionEvent e) {
		String search = tfSearch.getText().trim();
		if(search.isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색할 이름을 입력하세요.");
			return;
		}
		
		if(comboBox.equals("고객명")) {
			System.out.println(tfSearch.getText().trim());
		}
		
	}

	public void searchGuest(Guest guest) {
		
	}
	

	
}
