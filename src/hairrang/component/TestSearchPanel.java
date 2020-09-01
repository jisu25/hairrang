package hairrang.component;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import hairrang.dto.Guest;
import hairrang.service.GuestService;

import javax.swing.JButton;

import java.awt.color.CMMException;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;

public class TestSearchPanel extends JPanel implements ActionListener {
	private JTextField tfSearch;
	private JButton btnSearch;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private JComboBox comboBox;
	private SearchFrame mainFrame;

	
	public TestSearchPanel() {
		
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();

		initComponents();

	}

	private void initComponents() {
		setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"고객명", "생년월일", "전화번호"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(187, 40, 99, 21);
		add(comboBox);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(298, 40, 116, 21);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(426, 39, 69, 23);
		add(btnSearch);
		btnSearch.addActionListener(this);
	}
	
	
	
	public SearchFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(SearchFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void actionPerformed(ActionEvent e) {
		if(comboBox.getSelectedIndex() == 0) {
			System.out.println("고객명");
			String text = tfSearch.getText().trim();
			mainFrame.searchResult(text);
			
		}
		if(comboBox.getSelectedIndex() == 1) {
			System.out.println("생년월일");
			String text = tfSearch.getText().trim();
			mainFrame.searchBirthday(text);
		}
		if(comboBox.getSelectedIndex() == 2) {
			System.out.println("전화번호");
			String text = tfSearch.getText().trim();
			mainFrame.searchPhone(text);
		}
	}
}
