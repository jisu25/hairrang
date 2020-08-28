package hairrang.component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

import hairrang.dto.Guest;
import hairrang.exception.InValidationException;
import hairrang.service.GuestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuestSearchPanel extends JPanel implements ActionListener {
	
	
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfJoinDay;
	private JTextField tfPhone;
	private JTextField tfMemo;
	private JLabel lblNo;
	private JLabel lblName;
	private JLabel lblBirthday;
	private JLabel lblJoinDay;
	private JLabel lblGender;
	private JLabel lblPhone;
	private JLabel lblNote;
	private JRadioButton rBtnFemale;
	private JRadioButton rBtnMale;
	private JDateChooser dateChooser;
	private JButton btnSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private GuestSearch mainFrame;
	
	

	/**
	 * Create the panel.
	 */
	public GuestSearchPanel() {
		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		
		initComponents();

	}


	private void initComponents() {
		setLayout(null);
	
		lblNo = new JLabel("고객 번호 : ");
		lblNo.setBounds(100, 70, 70, 15);
		add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setEditable(false);
		tfNo.setColumns(10);
		tfNo.setBounds(185, 61, 116, 25);
		add(tfNo);
		
		lblName = new JLabel("고  객  명 : ");
		lblName.setBounds(100, 34, 70, 15);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(185, 29, 116, 25);
		add(tfName);
		
		lblBirthday = new JLabel("생년월일  : ");
		lblBirthday.setBounds(100, 106, 70, 15);
		add(lblBirthday);
		
		lblJoinDay = new JLabel("가입일자  : ");
		lblJoinDay.setBounds(100, 139, 70, 15);
		add(lblJoinDay);
		
		tfJoinDay = new JTextField();
		tfJoinDay.setEditable(false);
		Date join = new Date();
		new SimpleDateFormat("yyyy-MM-dd").format(join);
		tfJoinDay.setText(new SimpleDateFormat("yyyy-MM-dd").format(join));
		tfJoinDay.setColumns(10);
		tfJoinDay.setBounds(185, 136, 116, 25);
		add(tfJoinDay);
		
		lblGender = new JLabel("성       별 : ");
		lblGender.setBounds(400, 32, 70, 15);
		add(lblGender);
		
		lblPhone = new JLabel("연  락  처 : ");
		lblPhone.setBounds(400, 69, 70, 15);
		add(lblPhone);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(478, 66, 116, 25);
		add(tfPhone);
		
		lblNote = new JLabel("메      모  : ");
		lblNote.setBounds(400, 106, 70, 15);
		add(lblNote);
		
		rBtnFemale = new JRadioButton("여성");
		buttonGroup.add(rBtnFemale);
		rBtnFemale.setBounds(478, 31, 60, 23);
		add(rBtnFemale);
		
		rBtnMale = new JRadioButton("남성");
		buttonGroup.add(rBtnMale);
		rBtnMale.setBounds(542, 31, 60, 23);
		add(rBtnMale);
		
		tfMemo = new JTextField();
		tfMemo.setColumns(10);
		tfMemo.setBounds(478, 103, 116, 25);
		add(tfMemo);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(185, 100, 116, 25);
		add(dateChooser);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		btnSearch.setBounds(313, 30, 71, 23);
		add(btnSearch);
		
		
	}
	
	
	public Guest getGuest() throws ParseException {
		
		Calendar c = Calendar.getInstance();
		Date join = new Date(c.getTimeInMillis());

		// string->date
		int guestNo = Integer.parseInt(tfNo.getText().trim());
		String guestName = tfName.getText().trim();
		Date birthday = dateChooser.getDate();
		Date joinDay = join;
		String phone = tfPhone.getText().trim();
		int gender = rBtnFemale.isSelected() ? 1 : 2;
		String guestNote = tfMemo.getText().trim();
		return new Guest(guestNo, guestName, birthday, joinDay, phone, gender, guestNote);

	}



	// 패널셋 date->string
	public void setGuest(Guest guest) {
		tfNo.setText(String.valueOf(guest.getGuestNo()));
		tfName.setText(guest.getGuestName());
		tfJoinDay.setText(new SimpleDateFormat("yyyy-MM-dd").format(guest.getJoinDay()));
		tfPhone.setText(guest.getPhone());
		dateChooser.setDate(guest.getBirthday());

		if (guest.getGender() == 1) {
			rBtnFemale.setSelected(true);
		} else {
			rBtnMale.setSelected(true);
		}
		tfMemo.setText(guest.getGuestNote());

	}

	public void clearTf() {
		Date join = new Date();
		new SimpleDateFormat("yyyy-MM-dd").format(join);
		
		tfNo.setText("");
		tfName.setText("");
		dateChooser.setCalendar(null);
		tfJoinDay.setText(new SimpleDateFormat("yyyy-MM-dd").format(join));
		tfPhone.setText("");
		buttonGroup.clearSelection();
		tfMemo.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSearch) {
			btnSearchActionPerformed(e);
		}
	}


	private void btnSearchActionPerformed(ActionEvent e) {
		String search = tfName.getText().trim();
		if(search.isEmpty()) {
			JOptionPane.showMessageDialog(null, "검색할 이름을 입력하세요.");
			return;
		}
		if(!isValidTf()) {
			JOptionPane.showMessageDialog(null, "한글과 영어만 입력가능");
			return;
		}
		mainFrame.searchResult(search);
		System.out.println(search);
	}
	
	public GuestSearch getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(GuestSearch mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
	private boolean isValidTf() {
		String name = tfName.getText().trim();
		boolean nameCheck = Pattern.matches("^[가-힣a-zA-Z]+$", name);
		return nameCheck;
	}


}
