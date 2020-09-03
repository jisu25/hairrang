package hairrang.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import hairrang.Configuration;
import hairrang.dto.Booking;
import hairrang.dto.Guest;
import hairrang.dto.Hair;
import hairrang.service.BookingService;
import hairrang.service.GuestService;
import hairrang.service.HairService;
import com.toedter.components.JSpinField;

public class BookingAddInputPanel extends JPanel implements ActionListener {
	private JTextField tfBookingNo;
	private JTextField tfGuestName;
	private JTextField tfBookDate;
	private JTextField tfHairName;
	private JButton btnSearch;
	private JTextField tfPhone;
	private JTextArea taNote;
	private JCheckBox chckbxGuest;
	
	private JComboBox<String> comboHair;
	
//	private List<Hair> hairList;
	private List<String> hairNames;
	private JDateChooser dcBookDate;
	
	
	private int guestNo; // 이름만으로 고객을 기억할 수 없어서 보이지 않는 곳에 고객번호 저장.
	private BookingService bService;
	private GuestService gService;
	private HairService hService;
	private JSpinField spinField;
	
	
	public BookingAddInputPanel() {

		hService = new HairService();
		bService = new BookingService();
		gService = new GuestService();
		
//		hairList = hService.getHairList();
		
		initComponents();
	}
	
	private void initComponents() {
		setLayout(null);
		
		JLabel lblBookingNo = new JLabel("예약번호");
		lblBookingNo.setBounds(20, 12, 57, 15);
		add(lblBookingNo);
		
		JLabel lblGuestName = new JLabel("예약자");
		lblGuestName.setBounds(20, 120, 57, 15);
		add(lblGuestName);
		
		tfBookingNo = new JTextField();
		tfBookingNo.setText(bService.getBookingCurrVal() + "");
		tfBookingNo.setBounds(100, 10, 116, 21);
		add(tfBookingNo);
		tfBookingNo.setColumns(10);
		
		tfGuestName = new JTextField();
		tfGuestName.setColumns(10);
		tfGuestName.setBounds(100, 118, 116, 21);
		add(tfGuestName);
		
		JLabel lblBookDate = new JLabel("예약일자");
		lblBookDate.setBounds(20, 47, 57, 15);
		add(lblBookDate);
		
		dcBookDate = new JDateChooser();
		dcBookDate.setDateFormatString("yyyy-MM-dd");
		Date date = new Date();
		dcBookDate.setDate(date);
		dcBookDate.setBounds(100, 45, 116, 24);
		add(dcBookDate);
		
		JLabel lblHairName = new JLabel("헤어명");
		lblHairName.setBounds(20, 190, 57, 15);
		add(lblHairName);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(230, 115, 62, 23);
		add(btnSearch);
		
		JLabel lblPhone = new JLabel("연락처");
		lblPhone.setBounds(20, 155, 57, 15);
		add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setBounds(100, 153, 116, 21);
		add(tfPhone);
		tfPhone.setColumns(10);
		
		comboHair = new JComboBox<String>();
		comboHair.setBounds(100, 188, 116, 21);
		setHairModel();
		add(comboHair);
		
		JLabel lblNote = new JLabel("비고");
		lblNote.setBounds(20, 225, 57, 15);
		add(lblNote);
		
		taNote = new JTextArea();
		taNote.setColumns(10);
		taNote.setBounds(100, 223, 116, 52);
		add(taNote);
		
		chckbxGuest = new JCheckBox("비회원");
		chckbxGuest.setBounds(100, 90, 72, 23);
		add(chckbxGuest);
		
		
		spinField = new JSpinField();
		spinField.setBounds(225, 48, 29, 21);
		add(spinField);
//		spinField.set
	}
	
	private void setHairModel() {
		hairNames = hService.getHairNames();
		String[] items = (String[]) hairNames.toArray(new String[hairNames.size()]);

		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(items);
		comboHair.setModel(model);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			btnSearchActionPerformed(e);
		}
	}
	
	protected void btnSearchActionPerformed(ActionEvent e) {
		BookingGuestSearch searchDig = new BookingGuestSearch();
		searchDig.setpBookingAddInput(this);
		searchDig.setTitle("고객 검색");
		searchDig.setVisible(true);
	}
	
	public void setSelectedGuestToPanel(Guest guest) {
		guestNo = guest.getGuestNo();
		tfGuestName.setText(guest.getGuestName());
		tfPhone.setText(guest.getPhone());
	}
	
	public Guest getGuest() {
		return gService.selectGuestByNo(new Guest(guestNo));
	}
	
	// 필요한가...?
	public void clearTf() {
		tfBookingNo.setText(bService.getBookingCurrVal() + "");
		Date date = new Date();
		dcBookDate.setDate(date);
		tfGuestName.setText("");
		tfPhone.setText("");
		setHairModel();
		taNote.setText("");
	}
	
	protected Booking getBook() {
		int no = Integer.parseInt(tfBookingNo.getText().trim());
		Guest guest = getGuest();		
		Date day = dcBookDate.getDate();
		Hair hair = hService.getHairByHairNo(new Hair(comboHair.getSelectedIndex()));
		String note = taNote.getText().trim();
		
		return new Booking(no, guest, day, hair, note); 
	}
}
