package hairrang.component;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hairrang.service.HairService;

public class BookingAddInputPanel extends JPanel {
	private JLabel lblGuestNo;
	private JLabel lblGuestName;
	private JTextField tfGuestNo;
	private JTextField tfGuestName;
	private JLabel lblBookDate;
	private JTextField tfBookDate;
	private JLabel lblHairName;
	private JTextField tfHairName;
	private JButton btnSearch;
	private JLabel lblPhone;
	private JTextField tfPhone;
	private JLabel lblNote;
	private JTextArea taNote;
	private JCheckBox chckbxGuest;
	
	private JComboBox<String> comboHair;
	
	private List<String> hairNames;

	public BookingAddInputPanel() {

		HairService hService = new HairService();
		hairNames = hService.getHairNames();
		initComponents();
	}
	private void initComponents() {
		setLayout(null);
		
		lblGuestNo = new JLabel("예약번호");
		lblGuestNo.setBounds(20, 12, 57, 15);
		add(lblGuestNo);
		
		lblGuestName = new JLabel("예약자");
		lblGuestName.setBounds(20, 120, 57, 15);
		add(lblGuestName);
		
		tfGuestNo = new JTextField();
		tfGuestNo.setBounds(100, 10, 116, 21);
		add(tfGuestNo);
		tfGuestNo.setColumns(10);
		
		tfGuestName = new JTextField();
		tfGuestName.setColumns(10);
		tfGuestName.setBounds(100, 118, 116, 21);
		add(tfGuestName);
		
		lblBookDate = new JLabel("예약일자");
		lblBookDate.setBounds(20, 47, 57, 15);
		add(lblBookDate);
		
		tfBookDate = new JTextField();
		tfBookDate.setColumns(10);
		tfBookDate.setBounds(100, 45, 116, 21);
		add(tfBookDate);
		
		lblHairName = new JLabel("헤어명");
		lblHairName.setBounds(20, 190, 57, 15);
		add(lblHairName);
		
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(230, 115, 62, 23);
		add(btnSearch);
		
		lblPhone = new JLabel("연락처");
		lblPhone.setBounds(20, 155, 57, 15);
		add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(100, 153, 116, 21);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		comboHair = new JComboBox<String>();
		comboHair.setBounds(100, 188, 116, 21);
		setHairModel(hairNames);
		add(comboHair);
		
		lblNote = new JLabel("비고");
		lblNote.setBounds(20, 225, 57, 15);
		add(lblNote);
		
		taNote = new JTextArea();
		taNote.setColumns(10);
		taNote.setBounds(100, 223, 116, 52);
		add(taNote);
		
		chckbxGuest = new JCheckBox("비회원");
		chckbxGuest.setBounds(100, 90, 72, 23);
		add(chckbxGuest);
		
	}
	
	private void setHairModel(List<String> hairNames) {
		String[] items = (String[]) hairNames.toArray(new String[hairNames.size()]);

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		comboHair.setModel(model);
	}
}
