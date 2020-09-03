package hairrang.component;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import hairrang.dto.Booking;
import hairrang.service.BookingService;
import hairrang.table.BookingDetailTable;

@SuppressWarnings("serial")
public class BookingDetailView extends JPanel implements ActionListener {
	private JRadioButton radioWeek;
	private JRadioButton radioMonth;
	private JRadioButton radioDay;
	private JButton btnSreach;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JDateChooser toDate;
	private JDateChooser fromDate;
	
	private JPanel pTable;
	private BookingDetailTable table;
	private BookingService bService;
	private ArrayList<Booking> list;

	Calendar fromCal;
	Calendar toCal;
	private JRadioButton radioAll;
	
	public BookingDetailView() {
		bService = new BookingService();
		
		initComponents();
		listUpdate();
	}

	private void initComponents() {
		setLayout(null);
		
		pTable = new JPanel();
		pTable.setBounds(0, 157, 697, 315);
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new BookingDetailTable();
		scrollPane.setViewportView(table);
		
		JLabel lblDate = new JLabel("기 간 ");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(120, 30, 62, 21);
		add(lblDate);
		
		fromDate = new JDateChooser();
		fromDate.setBounds(194, 30, 125, 21);
//		fromDate.setDateFormatString("yyyy-MM-dd");
		add(fromDate);
		
		JLabel lblDate2 = new JLabel("~");
		lblDate2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate2.setBounds(331, 33, 29, 15);
		add(lblDate2);
		
		toDate = new JDateChooser();
		toDate.setBounds(372, 30, 125, 21);
//		toDate.setDateFormatString("yyyy-MM-dd");
		add(toDate);
		
		radioAll = new JRadioButton("전체");
		buttonGroup.add(radioAll);
		radioAll.setHorizontalAlignment(SwingConstants.CENTER);
		radioAll.setBounds(129, 76, 70, 23);
		radioAll.addActionListener(this);
		add(radioAll);
		
		radioMonth = new JRadioButton("1개월");
		buttonGroup.add(radioMonth);
		radioMonth.setHorizontalAlignment(SwingConstants.CENTER);
		radioMonth.setBounds(200, 76, 70, 23);
		radioMonth.addActionListener(this);
		add(radioMonth);
		
		radioWeek = new JRadioButton("일주일");
		buttonGroup.add(radioWeek);
		radioWeek.setHorizontalAlignment(SwingConstants.CENTER);
		radioWeek.setBounds(290, 76, 70, 23);
		radioWeek.addActionListener(this);
		add(radioWeek);
		
		radioDay = new JRadioButton("1일");
		buttonGroup.add(radioDay);
		radioDay.setHorizontalAlignment(SwingConstants.CENTER);
		radioDay.setBounds(364, 76, 70, 23);
		radioDay.addActionListener(this);
		add(radioDay);
		
		btnSreach = new JButton("검색");
		btnSreach.addActionListener(this);
		btnSreach.setBounds(441, 76, 57, 23);
		add(btnSreach);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == radioAll) {
			fromDate.setDate(null);
			toDate.setDate(null);
		} else if(e.getSource() == radioMonth) {
			setPeriod(Calendar.MONTH, 1);
		} else if (e.getSource() == radioWeek) {
			setPeriod(Calendar.DATE, 7);
		} else if (e.getSource() == radioDay) {
			setPeriod(Calendar.DATE, 1);
		}
		
		if(e.getSource() == btnSreach) {
			changeTable();
		}
	}

	// JDateChooser 날짜 셋팅
	private void setPeriod(int field, int amount) {
		fromCal = Calendar.getInstance();
		toCal = Calendar.getInstance();
		
		toCal.add(field, amount);
		toDate.setDate(toCal.getTime());
		
		fromDate.setDate(fromCal.getTime());
	}
	
	// 검색 버튼 눌렀을 때 테이블 바꾸는 메서드
	private void changeTable() {
		try {
			list = (ArrayList<Booking>) bService.getBookListByDate(fromDate.getDate(), toDate.getDate());
			table.setItems(list);
		} catch (NullPointerException e) {
			list = (ArrayList<Booking>) bService.getBookList();
			table.setItems(list);
		}
	}
	
	// == clearTf()랑 같음
	public void listUpdate() {
		radioWeek.setSelected(true);
		setPeriod(Calendar.DATE, 7);
		changeTable();
	}
}