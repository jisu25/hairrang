package hairrang.component;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import hairrang.dto.Guest;
import hairrang.service.GuestService;
import hairrang.table.GuestSearchResultTable;
import hairrang.table.GuestSearchTable;

@SuppressWarnings("serial")
public class FrameSearch2 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private GuestSearchTable table;
	private GuestService gService;
	private ArrayList<Guest> guestList;
	private JPanel pGuest;
	private JPanel pBtn;
	private JButton btnCancel;
	private JButton btnInfo;
	private JButton btnOrder;
	private JPanel pTable;
	private JScrollPane scrollPane;
	private GuestSearchResultTable rTable;

	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private JLabel lblMemo;
	private JRadioButton rBtnFemale;
	private JRadioButton rBtnMale;
	private JButton btnAdd;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSearch2 frame = new FrameSearch2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameSearch2() {

		gService = new GuestService();
		guestList = (ArrayList<Guest>) gService.getGuestList();

		initComponents();

	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pGuest = new JPanel();
		pGuest.setBounds(5, 5, 714, 186);
		contentPane.add(pGuest);
		pGuest.setLayout(null);

		lblNo = new JLabel("고객 번호 : ");
		lblNo.setBounds(110, 27, 70, 15);
		pGuest.add(lblNo);

		tfNo = new JTextField();
		tfNo.setEditable(false);
		tfNo.setColumns(10);
		tfNo.setBounds(195, 24, 116, 25);
		pGuest.add(tfNo);

		lblName = new JLabel("고  객  명 : ");
		lblName.setBounds(110, 64, 70, 15);
		pGuest.add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(195, 59, 116, 25);
		pGuest.add(tfName);

		lblBirthday = new JLabel("생년월일  : ");
		lblBirthday.setBounds(110, 99, 70, 15);
		pGuest.add(lblBirthday);

		lblJoinDay = new JLabel("가입일자  : ");
		lblJoinDay.setBounds(110, 132, 70, 15);
		pGuest.add(lblJoinDay);

		tfJoinDay = new JTextField();
		tfJoinDay.setEditable(false);
		tfJoinDay.setColumns(10);
		tfJoinDay.setBounds(195, 129, 116, 25);
		pGuest.add(tfJoinDay);

		lblGender = new JLabel("성       별 : ");
		lblGender.setBounds(410, 25, 70, 15);
		pGuest.add(lblGender);

		lblPhone = new JLabel("연  락  처 : ");
		lblPhone.setBounds(410, 62, 70, 15);
		pGuest.add(lblPhone);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(488, 59, 116, 25);
		pGuest.add(tfPhone);

		lblMemo = new JLabel("메      모  : ");
		lblMemo.setBounds(410, 99, 70, 15);
		pGuest.add(lblMemo);

		rBtnFemale = new JRadioButton("여성");
		rBtnFemale.setBounds(488, 24, 60, 23);
		pGuest.add(rBtnFemale);

		rBtnMale = new JRadioButton("남성");
		rBtnMale.setBounds(552, 24, 60, 23);
		pGuest.add(rBtnMale);

		tfMemo = new JTextField();
		tfMemo.setColumns(10);
		tfMemo.setBounds(488, 96, 116, 21);
		pGuest.add(tfMemo);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(195, 93, 116, 21);
		pGuest.add(dateChooser);

		btnAdd = new JButton("검색");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(322, 60, 76, 23);
		pGuest.add(btnAdd);

		pBtn = new JPanel();
		pBtn.setBounds(5, 190, 714, 38);
		contentPane.add(pBtn);
		pBtn.setLayout(null);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(327, 5, 85, 23);
		pBtn.add(btnCancel);

		btnInfo = new JButton("이용 내역");
		btnInfo.setBounds(426, 5, 90, 23);
		pBtn.add(btnInfo);

		btnOrder = new JButton("주문");
		btnOrder.setBounds(530, 5, 85, 23);
		pBtn.add(btnOrder);

		pTable = new JPanel();
		pTable.setBounds(5, 234, 714, 260);
		contentPane.add(pTable);
		pTable.setLayout(new GridLayout(1, 0, 0, 0));

		scrollPane = new JScrollPane();
		pTable.add(scrollPane);

		table = new GuestSearchTable();
		scrollPane.setViewportView(table);
		table.setItems(guestList);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Guest selectedGuest = getSelectedGuest();
					setGuest(selectedGuest);

				}
			}
		});

	}

//get set clear////////////////////////////////////////////////////////////////////////////////////////////////

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

//패널셋 date->string
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
		
		tfNo.setText("");
		tfName.setText("");

		Date date = new Date();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setDate(date);

		tfJoinDay.setText("");
		tfPhone.setText("");
		buttonGroup.clearSelection();
		tfMemo.setText("");

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Guest getSelectedGuest() {
		int selectedRow = table.getSelectedRow();
		return guestList.get(selectedRow);
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			btnSearchActionPerformed(e);
		}
		if (e.getSource() == btnCancel) {
			btnCancelActionPerformed(e);
		}
	}

	private void btnSearchActionPerformed(ActionEvent e) {
		String search = tfName.getText().trim();
		System.out.println(search);
		guestList = (ArrayList<Guest>) gService.searchGuestByName(new Guest(search));
		guestList.stream().forEach(System.out::println);
		table.setItems(guestList);

	}
	

	private void btnCancelActionPerformed(ActionEvent e) {
		System.out.println("취소");
		clearTf();
		guestList = (ArrayList<Guest>) gService.getGuestList();
		table.setItems(guestList);

	}
}