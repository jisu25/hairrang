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
import hairrang.table.BookingTable;

@SuppressWarnings("serial")
public class BookingDetail extends JPanel implements ActionListener {
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JRadioButton radioMonth;
	private JRadioButton radioWeek;
	private JRadioButton radioDay;
	
	private JButton btnSreach;
	
	private JDateChooser toDate;
	private JDateChooser fromDate;

	private BookingDetailTable table;
	private BookingService bService = new BookingService();
	private ArrayList<Booking> list;

	private Calendar fromCal;
	private Calendar toCal;

	public BookingDetail() {
		
		list = (ArrayList<Booking>) bService.getBookList();
		
		fromCal = Calendar.getInstance();
		toCal = Calendar.getInstance();
		
		initComponents();

	}


	private void initComponents() {
		setLayout(null);
		
		JPanel pTable = new JPanel();
		pTable.setBounds(0, 157, 697, 315);
		add(pTable);
		pTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pTable.add(scrollPane, BorderLayout.CENTER);
		
		table = new BookingDetailTable();
		table.setItems(list);
		scrollPane.setViewportView(table);
		
		JLabel lblDate = new JLabel("날  짜 :");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(120, 30, 62, 21);
		add(lblDate);
		
		fromDate = new JDateChooser();
		fromDate.setBounds(194, 30, 125, 21);
		add(fromDate);
		
		JLabel lblDate2 = new JLabel("~");
		lblDate2.setFont(new Font("굴림", Font.BOLD, 12));
		lblDate2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate2.setBounds(331, 33, 29, 15);
		add(lblDate2);
		
		toDate = new JDateChooser();
		toDate.setBounds(372, 30, 125, 21);
		add(toDate);
		
		radioMonth = new JRadioButton("한달");
		buttonGroup.add(radioMonth);
		radioMonth.setHorizontalAlignment(SwingConstants.CENTER);
		radioMonth.setBounds(416, 76, 81, 23);
		radioMonth.addActionListener(this);
		add(radioMonth);
		
		radioWeek = new JRadioButton("일주일");
		buttonGroup.add(radioWeek);
		radioWeek.setHorizontalAlignment(SwingConstants.CENTER);
		radioWeek.setBounds(302, 76, 81, 23);
		radioWeek.addActionListener(this);
		add(radioWeek);
		
		radioDay = new JRadioButton("하루");
		buttonGroup.add(radioDay);
		radioDay.setHorizontalAlignment(SwingConstants.CENTER);
		radioDay.setBounds(194, 76, 81, 23);
		radioDay.addActionListener(this);
		add(radioDay);
		
		btnSreach = new JButton("검색");
		btnSreach.addActionListener(this);
		btnSreach.setBounds(505, 76, 57, 23);
		add(btnSreach);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == radioMonth) {
			setPeriod(Calendar.MONTH, 1);
		}
		
		if(e.getSource() == radioWeek) {
			setPeriod(Calendar.DATE, 7);
		}
		
		if(e.getSource() == radioDay) {
			setPeriod(Calendar.DATE, 1);
		}
		
		if(e.getSource() == btnSreach) {
			changeTable();
		}
	}


	private void setPeriod(int field, int amount) {
		toCal.add(field, amount);
		toDate.setDate(toCal.getTime());
		
		fromDate.setDate(fromCal.getTime());
		
		fromCal = Calendar.getInstance();
		toCal = Calendar.getInstance();
	}
	
	
	
	
	private void changeTable() {
		try {
			list = (ArrayList<Booking>) bService.getBookListByDate(fromDate.getDate(), toDate.getDate());
			table.setItems(list);
		}catch (NullPointerException e) {
			table.setItems((ArrayList<Booking>)bService.getBookList());
		}
	}
	
	public void clearTf() {
		Calendar beforecal = Calendar.getInstance();
		Calendar aftercal = Calendar.getInstance();
		System.out.println("일주일 실행");
		
		beforecal.add(Calendar.DATE, 7);
		fromDate.setDate(beforecal.getTime());
		toDate.setDate(aftercal.getTime());
		beforecal = Calendar.getInstance();
		aftercal = Calendar.getInstance();
	};
	
	
}