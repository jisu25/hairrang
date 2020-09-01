package hairrang.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hairrang.dto.Guest;
import hairrang.service.GuestService;

public class GuestSearchPanel extends JPanel implements ActionListener {
	private JTextField tfSearch;
	private JButton btnSearch;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private JComboBox<Object> comboBox;
	private GuestSearch mainFrame;
	

	
	public GuestSearchPanel() {
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();

		initComponents();

	}

	private void initComponents() {
		setLayout(null);
		
		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"고객명", "생년월일", "전화번호"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(198, 30, 99, 21);
		add(comboBox);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(309, 30, 116, 21);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(437, 29, 69, 23);
		add(btnSearch);
		btnSearch.addActionListener(this);
	}
	
	public GuestSearch getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(GuestSearch mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void clearTf() {
		tfSearch.setText("");
		comboBox.setSelectedIndex(0);
	}
	

	public void actionPerformed(ActionEvent e) {
		if(comboBox.getSelectedIndex() == 0) {
			//System.out.println("고객명");
			String search = tfSearch.getText().trim();
			mainFrame.searchGuestName(search);
			
		}
		if(comboBox.getSelectedIndex() == 1) {
			//System.out.println("생년월일");
			String search = tfSearch.getText().trim();
			mainFrame.searchGuestBirthday(search);;
		}
		if(comboBox.getSelectedIndex() == 2) {
			//System.out.println("전화번호");
			String search = tfSearch.getText().trim();
			mainFrame.searchGuestPhone(search);
		}
	}
}
