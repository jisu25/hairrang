package hairrang.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hairrang.dto.Guest;
import hairrang.exception.EmptyTfException;
import hairrang.service.GuestService;

public class GuestSearchPanel extends JPanel implements ActionListener {
	private JTextField tfSearch;
	private JButton btnSearch;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private JComboBox<Object> comboBox;
	private GuestSearch mainFrame;
	private JButton btnCancel;
	

	
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
		comboBox.setBounds(166, 29, 79, 21);
		add(comboBox);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(257, 29, 116, 21);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(385, 29, 70, 25);
		add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(462, 29, 70, 25);
		btnCancel.addActionListener(this);
		add(btnCancel);
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
		if(e.getSource() == btnSearch) {
			isEmpty();
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
		if(e.getSource() == btnCancel) {
			clearTf();
			mainFrame.listUpdate();
		}	
	}
	
	public void isEmpty() {
		if(tfSearch.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색어를 입력하세요");
			throw new EmptyTfException("공란존재");
		}
	}
}
